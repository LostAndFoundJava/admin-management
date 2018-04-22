(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload.management')
        .controller('VisaUploadCtrl', VisaUploadCtrl);

    angular.module('KingAdmin.pages.visaupload.management').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function VisaUploadCtrl($scope, $timeout, toastr, $stateParams, $state, RegionService, VisaUploadService, $uibModal, Upload) {

        var kt = this;

        kt.visa = {};
        kt.files = []
        kt.visa.fileList = [];

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        kt.noData = true;

        //获取国家级类别
        $scope.selectContinent = function () {
            RegionService.getCountryByContinent({continentId: kt.visa.continent}, function (data) {
                kt.countryList = data.result;
            })
        }

        //获取洲
        RegionService.getContinentList({}, function (data) {
            kt.continentList = data.result;
        })

        //由id判断是新增还是修改/查看（回显数据）
        if ($stateParams.id) {
            VisaUploadService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.visa = data;
                })
        } else {
            kt.isAdd = true;
        }

        //保存新增／修改的数据
        kt.save = function () {
            VisaUploadService.save(kt.visa, function (data) {
                $state.go('visa.management');
            });
        }

        kt.basicConfig = {
            core: {
                check_callback: true,
                worker: true
            },
            'types': {
                'default': {
                    'icon': false
                }
            },
            "checkbox": {
                "keep_selected_style": false
            },
            'plugins': ['types', "wholerow", 'checkbox'],
            'version': 1
        };

        $scope.openModel = function () {
            $uibModal.open({
                templateUrl: 'app/pages/visaupload/management/management_M.html',
                controller: 'VisaUploadCtrl',
                controllerAs: 'kt',
                backdrop: 'static',
                size: 'lg'
            }).result.then(function () {

                // $state.go('visa.management.add', null, {reload: false});
            }, function () {
                // $state.go('^');
            })
        }

        $scope.uploadFiles = function (files) {
            if (files && files.length) {
                angular.forEach(files, function (file) {
                    var wjzl = {};
                    wjzl.name = file.name;
                    kt.files.push(wjzl);
                    kt.visa.fileList.push(wjzl);
                })
                Upload.upload({
                    url: '/api/mgr/image/upload',
                    data: {
                        files: files
                    }
                }).then(function (response) {

                    $timeout(function () {
                        $scope.result = response.data;
                        angular.forEach(response.data.result, function (fileUrl) {
                            var wjzl = {};
                            wjzl.fileUrl = fileUrl;
                            kt.visa.fileList.push(wjzl);
                        })
                    });
                }, function (response) {
                    if (response.status > 0) {
                        $scope.errorMsg = response.status + ': ' + response.data;
                    }
                }, function (evt) {
                    $scope.progress =
                        Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
                });
            }
        };

        $scope.removeFile = function (item) {
            var index = kt.files.indexOf(item);
            kt.files.splice(index, 1);
            kt.visa.fileList.splice(index, 1);
        }
    }
})();
