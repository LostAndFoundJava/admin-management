(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.management')
        .controller('ExhibitionCtrl', ExhibitionCtrl);

    angular.module('KingAdmin.pages.exhibition.management').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function ExhibitionCtrl($scope, $timeout, toastr, $stateParams, $state, ExhibitionService, RegionService, CategoryService) {

        var kt = this;
        kt.exhibition = {};
        kt.exhibition.exhibitionDetail = {};
        $scope.mockFiles = [];
        $scope.mockFiles1 = [];//缩略
        $scope.mockFiles2 = [];//轮播

        var imageSize = "!400-400";

        //初始化dropzone
        initDropzone();


        //用户存图片上传后的url
        var map = {};

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        //quill只读
        $scope.ifshow = !kt.isView
        $scope.readonly = kt.isView

        //获取行业列表
        CategoryService.getList({}, function (data) {
            kt.CategoryList = data.result;
        })

        //获取国家级类别
        RegionService.getCountryList({}, function (data) {
            kt.CountryList = data.result;
            kt.ProvinceList = [];
            kt.CityList = [];
        })

        //获取省份类别
        $scope.selectCountry = function () {
            RegionService.getRegionList({pid: kt.exhibition.country}, function (data) {
                kt.ProvinceList = data.result;
                kt.CityList = [];
            })
        }

        //获取城市类别
        $scope.selectProvince = function () {
            RegionService.getRegionList({pid: kt.exhibition.province}, function (data) {
                kt.CityList = data.result;
            })
        }

        //由id判断是新增还是修改/查看（回显数据）
        if ($stateParams.id) {
            ExhibitionService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.exhibition = data;

                    if (kt.exhibition.country) {
                        $scope.selectCountry();
                    }

                    if (kt.exhibition.province) {
                        $scope.selectProvince();
                    }

                    angular.forEach(kt.exhibition.exhibitionDetail.files, function (file) {
                        $scope.mockFiles.push({
                            name: file.name,
                            size: 5000,
                            isMock: true,
                            serverImgUrl: file.fileUrl
                        });
                    })

                    //缩略
                    $scope.mockFiles1.push({
                        name: "thumbnail.jpg",
                        size: 5000,
                        isMock: true,
                        serverImgUrl: kt.exhibition.thumbnail
                    });

                    //轮播
                    $scope.mockFiles2.push({
                        name: "carousel.jpg",
                        size: 5000,
                        isMock: true,
                        serverImgUrl: kt.exhibition.carousel
                    });

                    if (kt.exhibition.exhibitionDetail.briefInfo) {
                        kt.customBriefInfo = JSON.parse(kt.exhibition.exhibitionDetail.briefInfo);
                    }

                    $timeout(function () {

                        if (kt.exhibition.exhibitionDetail.description) {
                            // $scope.model = kt.exhibition.exhibitionDetail.description;
                            kt.customDetail = JSON.parse(kt.exhibition.exhibitionDetail.description);
                        }

                        // get dropzone instance to emit some events
                        // $scope.myDz = $scope.dzMethods.getDropzone();
                        $scope.myDz = $scope.dzMethods.getDropzone();
                        $scope.myDz1 = $scope.dzMethods1.getDropzone();
                        $scope.myDz2 = $scope.dzMethods2.getDropzone();

                        $scope.mockFiles.forEach(function (mockFile) {
                            $scope.myDz.emit('addedfile', mockFile);
                            $scope.myDz.emit('complete', mockFile);
                            $scope.myDz.options.maxFiles = $scope.dzOptions.maxFiles - $scope.mockFiles.length;
                            $scope.myDz.files.push(mockFile);
                        });

                        $scope.mockFiles1.forEach(function (mockFile) {
                            $scope.myDz1.emit('addedfile', mockFile);
                            $scope.myDz1.emit('complete', mockFile);
                            $scope.myDz1.options.maxFiles = $scope.dzOptions.maxFiles - $scope.mockFiles.length;
                            $scope.myDz1.files.push(mockFile);
                        });

                        $scope.mockFiles2.forEach(function (mockFile) {
                            $scope.myDz2.emit('addedfile', mockFile);
                            $scope.myDz2.emit('complete', mockFile);
                            $scope.myDz2.options.maxFiles = $scope.dzOptions.maxFiles - $scope.mockFiles.length;
                            $scope.myDz2.files.push(mockFile);
                        });
                    });
                })
        } else {
            kt.isAdd = true;

        }

        //保存新增／修改的数据
        kt.save = function () {
            kt.exhibition.exhibitionDetail.files = [];
            kt.exhibition.startTime = formatDate(kt.exhibition.startTime);
            kt.exhibition.endTime = formatDate(kt.exhibition.endTime);
            kt.exhibition.exhibitionDetail.description = JSON.stringify(kt.customDetail);
            kt.exhibition.exhibitionDetail.briefInfo = JSON.stringify(kt.customBriefInfo);
            /*if(!map[kt.thumbnailImg]){
                toastr.warning("缩略图设置不正确");
                return;
            }
            if(!map[kt.carouselImg]){
                toastr.warning("轮播图设置不正确");
                return;
            }
            kt.exhibition.thumbnail = map[kt.thumbnailImg];
            kt.exhibition.carousel = map[kt.carouselImg];*/
            if(!kt.exhibition.carousel) {
                toastr.warning("轮播图未设置");
                return;
            }
            if(!kt.exhibition.thumbnail) {
                toastr.warning("缩略图未设置");
                return;
            }

            for (var index in map) {
                var imageFile = {};
                imageFile.fileUrl = map[index];
                kt.exhibition.exhibitionDetail.files.push(imageFile);
            }

            ExhibitionService.save(kt.exhibition, function (data) {
                $state.go('exhibition.management');
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

        $scope.isDisabledDate = function (currentDate, mode) {
            return mode === 'day' && (currentDate.getDay() === 0 || currentDate.getDay() === 6);
        };

        $scope.dateOptions = {
            minDate: new Date()
        };


        $scope.today = function () {
            kt.exhibition.startTime = new Date();
            kt.exhibition.endTime = kt.exhibition.startTime;
        };
        $scope.today();


        kt.exhibition.title = "title";
        kt.exhibition.city = 1;
        kt.exhibition.category = "category";
        kt.exhibition.tag = "tag";
        kt.exhibition.location = "location";
        kt.exhibition.country = 2;
        kt.exhibition.categoryId = "categoryid";


        /*========================*/

        /**
         * dropzone使用
         * @type {string}
         */

        //初始化dropzone
        $scope.partialDownloadLink = 'http://localhost:8080/download?filename=';
        $scope.filename = '';

        $scope.uploadFile = function () {
            $scope.processQueue();
        };

        $scope.reset = function () {
            $scope.resetDropzone();
        };

        function initDropzone() {
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
                parallelUploads: 3,
                autoProcessQueue: false,
                dictRemoveFile: 'Remove photo',
                dictDefaultMessage : "选择图片"
                // uploadMultiple: true
            };


            //Handle events for dropzone
            //Visit http://www.dropzonejs.com/#events for more events
            var index_i = 1;
            $scope.dzCallbacks = {
                'addedfile': function (file) {
                    $scope.lastFile = file;
                    if (file.isMock) {
                        var index = (++index_i) + 5;
                        file.previewElement.querySelector("img")['id'] = index;
                        map[index] = file.serverImgUrl;
                        /*var text = "Remove(" + index + ")";
                        $scope.dzOptions.dictRemoveFile = text;
                        $scope.myDz.options.dictRemoveFile = text;*/
                        $scope.myDz.createThumbnailFromUrl(file, file.serverImgUrl + imageSize, null, true);
                    }
                },
                'success': function (file, xhr) {
                    if (!xhr && xhr.code != 0) {
                        toastr.error(xhr.msg);
                        return;
                    }
                    angular.forEach(xhr.result, function (fileUrl) {
                        var index = (++index_i) + 10;
                        file.previewElement.querySelector("img")['id'] = index;
                        file.previewElement.querySelector("img")['src'] = fileUrl + imageSize;
                      /*  var text = "Remove(" + index + ")";
                        $scope.dzOptions.dictRemoveFile = text;
                        if ($scope.dzMethods.getDropzone() != null) {
                            $scope.dzMethods.getDropzone().options.dictRemoveFile = text;
                        }*/
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

            $scope.dzCallbacks1 = {
                'addedfile': function (file) {
                    $scope.lastFile = file;
                    if (file.isMock) {
                        $scope.myDz1.createThumbnailFromUrl(file, file.serverImgUrl + imageSize, null, true);
                    }
                },
                'success': function (file, xhr) {
                    if (!xhr && xhr.code != 0) {
                        toastr.error(xhr.msg);
                        return;
                    }
                    angular.forEach(xhr.result, function (fileUrl) {
                        kt.exhibition.thumbnail = fileUrl;
                    })

                },
                'removedfile': function (file) {
                    if (file.previewElement) {
                        if ((file.previewElement) != null) {
                            kt.exhibition.thumbnail = "";
                        }
                    }
                },
            };

            $scope.dzCallbacks2 = {
                'addedfile': function (file) {
                    $scope.lastFile = file;
                    if (file.isMock) {
                        $scope.myDz2.createThumbnailFromUrl(file, file.serverImgUrl + imageSize, null, true);
                    }
                },
                'success': function (file, xhr) {
                    if (!xhr && xhr.code != 0) {
                        toastr.error(xhr.msg);
                        return;
                    }
                    angular.forEach(xhr.result, function (carouselUrl) {
                        kt.exhibition.carousel = carouselUrl;
                    })

                },
                'removedfile': function (file) {
                    if (file.previewElement) {
                        if ((file.previewElement) != null) {
                            kt.exhibition.carousel = "";
                        }
                    }
                },
            };


            $scope.dzMethods = {};
            $scope.dzMethods1 = {};
            $scope.dzMethods2 = {};
            $scope.removeNewFile = function () {
                $scope.dzMethods.removeFile($scope.newFile);
            }
        }

        /*========初始化quill==============*/

        $scope.editorCreated = function (editor) {
            $scope.readonly = kt.isView
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

            ExhibitionService.uploadFile(fd,
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


        //自定义详情部分
        kt.customDetail = [];
        var detail = {
            detailTitle: '',
            detailContent: ''
        };

        $scope.addDetail = function () {
            var copyDetail = angular.copy(detail);
            kt.customDetail.push(copyDetail);
        }

        $scope.removeDetail = function (item) {
            var index = kt.customDetail.indexOf(item);
            kt.customDetail.splice(index, 1);
        }

        //自定义展会简介
        kt.customBriefInfo = [];
        var briefInfo = {
            key: '',
            value: ''
        };

        $scope.addBriefInfo = function () {
            var copyDetail = angular.copy(briefInfo);
            kt.customBriefInfo.push(copyDetail);
        }

        $scope.removeBriefInfo = function (item) {
            var index = kt.customBriefInfo.indexOf(item);
            kt.customBriefInfo.splice(index, 1);
        }


    }
})();
