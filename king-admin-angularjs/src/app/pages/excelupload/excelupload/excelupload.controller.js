(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.excelupload')
        .controller('ExcelUploadCtrl', ExcelUploadCtrl);
    /** @ngInject */
    function ExcelUploadCtrl($scope, Upload, $timeout, toastr, ExcelUploadService) {
        var kt = this;
        kt.files = [];
        $scope.uploadFiles = function (files) {
            if (files && files.length) {
                angular.forEach(files, function (file) {
                    var wjzl = {};
                    wjzl.fileName = file.name;
                    kt.files.push(wjzl);
                })

                Upload.upload({
                    url: '/mgr/flowsrc/management/excelupload',
                    data: {
                        files: files
                    }
                }).progress(function (evt) {
                    $scope.progress = parseInt(100.0 * evt.loaded / evt.total);
                    // toastr.warning('progress: ' + $scope.progress + '% ' );
                }).success(function (data, status, headers, config) {
                    if (data && data.code == 500) {
                        toastr.warning(data.msg);
                        $scope.progress = "0";
                    } else {
                        toastr.success('导入成功');
                    }
                }).error(function (data, status, headers, config) {
                    toastr.warning(data.msg);
                    $scope.progress = "0";
                })
            }
        };

        ExcelUploadService.getLogs({}, function (data) {
            if (data) {
                kt.files = data.result;
            }

        })


    }

})();
