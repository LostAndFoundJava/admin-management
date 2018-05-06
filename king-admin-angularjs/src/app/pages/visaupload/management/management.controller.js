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
        kt.countryName = "china";
        kt.fileName="";
        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        if ($stateParams.isDisable) {
            kt.isDisable = true;
        } else {
            kt.isDisable = false;
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

                    if (data && data.continent) {
                        $scope.selectContinent();
                    }
                    kt.visa = data;
                    kt.visa.continent = parseInt(kt.visa.continent);
                    kt.visa.country = parseInt(kt.visa.country);
                    angular.forEach(kt.visa.fileList, function (file) {
                        var wj = {};
                        var fileUrl = file.fileUrl;
                        if (fileUrl) {
                            var index = fileUrl.lastIndexOf("/") + 1;
                            wj.name = fileUrl.substring(index, file.fileUrl.length);
                            // wj.fileUrl = fileUrl;
                            kt.files.push(wj);
                        }
                    })

                    // kt.visa.continent = data.continent;
                    // kt.visa.country = data.country;
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
                    kt.fileName = file.name;
                    kt.files.push(wjzl);
                    // kt.visa.fileList.push(wjzl);
                })
                Upload.upload({
                    url: '/api/mgr/image/document',
                    data: {
                        files: files,
                        countryName: kt.countryName
                    }
                }).then(function (response) {

                    $timeout(function () {
                        $scope.result = response.data;
                        angular.forEach(response.data.result, function (fileUrl) {
                            var wjzl = {};
                            wjzl.fileUrl = fileUrl;
                            wjzl.fileName = kt.fileName;
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


        /*========初始化quill==============*/

        var imageSize = "!5000-5000";

        $scope.editorCreated = function (editor) {
            $scope.readonly = kt.isView;
            if (editor) {
                var toolbar = editor.getModule('toolbar');
                editor.getModule("toolbar").addHandler("image", function () {
                    selectImage(toolbar,editor);
                });
            }

        }

        //
        $scope.contentChanged = function (editor, html, text) {
            $scope.changeDetected = true;
            kt.exhibition.exhibitionDetail.description = html;
        }

        //选择图片
        function selectImage(toolbar,editor) {
            var fileInput = toolbar.container.querySelector('input.ql-image[type=file]');
            if (fileInput == null) {
                fileInput = document.createElement('input');
                fileInput.setAttribute('type', 'file');
                fileInput.setAttribute('accept', 'image/png, image/gif, image/jpeg, image/bmp, image/x-icon');
                fileInput.classList.add('ql-image');
                fileInput.addEventListener('change', function () {
                    if (fileInput.files != null && fileInput.files[0] != null) {
                        saveToServer(fileInput.files[0],editor);
                    }
                });
                toolbar.container.appendChild(fileInput);
            }
            fileInput.click();
        };

        // 上传到服务器@param {File} file
        function saveToServer(file,editor) {
            //upload on server
            const fd = new FormData();
            fd.append('image', file);

            VisaUploadService.uploadFile(fd,
                function (data) {
                    if (data && data.code == 0) {
                        insertToEditor(data.result[0]);
                    }
                })
        }

        //回显到quill 文本框中
        function insertToEditor(url,editor) {
            // push image url to rich editor.
            var range = editor.getSelection();
            var imagePosition = 0;
            if (range) {
                imagePosition = range.index;
            }
            editor.insertEmbed(imagePosition, 'image', url + imageSize);
        }

    }
})();
