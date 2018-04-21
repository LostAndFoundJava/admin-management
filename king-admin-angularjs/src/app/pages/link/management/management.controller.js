(function () {
    'use strict';

    angular.module('KingAdmin.pages.link.management')
        .controller('LinkCtrl', LinkCtrl);

    angular.module('KingAdmin.pages.link.management').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function LinkCtrl($scope, $timeout, toastr, $stateParams, $state, LinkService) {

        var kt = this;
        //回显
        kt.link = {};

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        //回显图片大小
        var imageSize = "!400-400";

        //用户存图片上传后的url
        var map = {};

        //由id判断是新增还是修改/查看（回显数据）
        if ($stateParams.id) {
            LinkService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.link = data;
                    $scope.mockFiles.push({
                        name: kt.link.picName,
                        size: 5000,
                        isMock: true,
                        serverImgUrl: kt.link.picUrl
                    });
                })
        } else {
            kt.isAdd = true;
        }

        //保存新增／修改的数据
        kt.save = function () {
            for (var index in map) {
                kt.link.picUrl  = map[index];
            }

            LinkService.save(kt.link, function (data) {
                $state.go('link.management');
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

        /*========================*/

        /**
         * dropzone使用
         * @type {string}
         */

        //初始化dropzone
        $scope.uploadFile = function () {
            $scope.processQueue();
        };

        $scope.reset = function () {
            $scope.resetDropzone();
        };

        function initDropzone() {
            $scope.showBtns = false;
            $scope.lastFile = null;


            // setting methods transport object
            // wrap methods in $timeout to call methods after current digest cycle
            $scope.myDz = null;
            var dropzone = null;

            $scope.dzOptions = {
                url: '/api/mgr/image/upload',
                paramName: 'file',
                maxFilesize: '10',
                acceptedFiles: 'image/jpeg, images/jpg, image/png',
                addRemoveLinks: true,
                parallelUploads: 1,
                autoProcessQueue: false,
                // uploadMultiple: true
            };


            //Handle events for dropzone
            //Visit http://www.dropzonejs.com/#events for more events
            var index_i = 1;
            $scope.dzCallbacks = {
                'addedfile': function (file) {
                    $scope.showBtns = true;
                    $scope.lastFile = file;
                    if (file.isMock) {
                        var index = Date.parse(new Date()) + (++index_i);
                        file.previewElement.querySelector("img")['id'] = index;
                        map[index] = file.serverImgUrl;
                        $scope.myDz.createThumbnailFromUrl(file, file.serverImgUrl + imageSize, null, true);
                    }
                },
                'success': function (file, xhr) {
                    console.log(file, xhr);
                    if (!xhr && xhr.code != 0) {
                        toastr.error(xhr.msg);
                        return;
                    }
                    angular.forEach(xhr.result, function (fileUrl) {
                        var index = Date.parse(new Date()) + (++index_i);
                        file.previewElement.querySelector("img")['id'] = index;
                        file.previewElement.querySelector("img")['src'] = fileUrl + imageSize;
                        map[index] = fileUrl;
                    })

                },
                'error': function (file, xhr) {
                    console.warn('File failed to upload from dropzone.', file, xhr);
                },

                'removedfile': function (file) {
                    if (file.previewElement) {
                        if ((file.previewElement) != null) {
                            var _key = file.previewElement.querySelector("img")['id'];
                            delete map[_key];
                        }
                    }
                },

            };


            $scope.dzMethods = {};
            $scope.removeNewFile = function () {
                $scope.dzMethods.removeFile($scope.newFile);
            }
        }

        initDropzone();

    }
})();
