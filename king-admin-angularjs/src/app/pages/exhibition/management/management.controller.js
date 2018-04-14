(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.management')
        .controller('ExhibitionCtrl', ExhibitionCtrl);

    angular.module('KingAdmin.pages.exhibition.management').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function ExhibitionCtrl($scope, $timeout, toastr, $stateParams, $state, ExhibitionService, RegionService,CategoryService) {

        var kt = this;
        kt.exhibition = {};
        kt.exhibition.exhibitionDetail = {};
        kt.exhibition.exhibitionDetail.files = [];
        // var imageFile = {};
        var quillEditor;
        var delta;
        $scope.mockFiles = [];


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
        CategoryService.getList({},function (data) {
          kt.CategoryList = data.result;
        })

        //获取国家级类别
        RegionService.getCountryList({}, function (data) {
            kt.CountryList = data.result;
        })

        //获取国家级类别
        $scope.selectCountry = function () {
            RegionService.getCityList({countryId:kt.exhibition.country}, function (data) {
                kt.CityList = data.result;
            })
        }


        //由id判断是新增还是修改/查看（回显数据）
        if ($stateParams.id) {
            ExhibitionService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.exhibition = data;

                    if (kt.exhibition.country) {
                        RegionService.getCityList({countryId:kt.exhibition.country}, function (data) {
                            kt.CityList = data.result;
                        })
                    }

                    angular.forEach(kt.exhibition.exhibitionDetail.files, function (file) {
                        $scope.mockFiles.push({
                            name: file.name,
                            size: 5000,
                            isMock: true,
                            serverImgUrl: file.fileUrl
                        });
                    })

                    $timeout(function () {

                        if (kt.exhibition.exhibitionDetail.description) {
                            // quillEditor.pasteHTML(kt.exhibition.exhibitionDetail.description);
                            $scope.model = kt.exhibition.exhibitionDetail.description;
                        }

                        // get dropzone instance to emit some events
                        // $scope.myDz = $scope.dzMethods.getDropzone();
                        $scope.myDz = $scope.dzMethods.getDropzone();

                        $scope.mockFiles.forEach(function (mockFile) {
                            $scope.myDz.emit('addedfile', mockFile);
                            $scope.myDz.emit('complete', mockFile);
                            $scope.myDz.options.maxFiles = $scope.dzOptions.maxFiles - $scope.mockFiles.length;
                            $scope.myDz.files.push(mockFile);
                        });
                    });
                })
        } else {
            kt.isAdd = true;
        }

        //保存新增／修改的数据
        kt.save = function () {
            kt.exhibition.startTime = formatDate(kt.exhibition.startTime);
            kt.exhibition.endTime = formatDate(kt.exhibition.endTime);
            kt.exhibition.hot = kt.exhibition.hot ? 1 : 0;
            kt.exhibition.hasCarousel = kt.exhibition.hasCarousel ? 1 : 0;
            // kt.exhibition.exhibitionDetail.description = quillEditor.getText();
            kt.exhibition.exhibitionDetail.files = [];
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
                parallelUploads: 5,
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
                        $scope.myDz.createThumbnailFromUrl(file, file.serverImgUrl, null, true);
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

        /*========初始化quill==============*/

        $scope.editorCreated = function (editor) {
            console.log(editor);
            $scope.readonly = kt.isView
            quillEditor = editor;
            if (editor) {
                var toolbar = quillEditor.getModule('toolbar');
                editor.getModule("toolbar").addHandler("image", function () {
                    selectImage(toolbar);
                });
            }

        }

        //
        $scope.contentChanged = function (editor, html, text) {
            $scope.changeDetected = true;
            kt.exhibition.exhibitionDetail.description = html;
        }

        //选择图片
        function selectImage(toolbar) {
            var fileInput = toolbar.container.querySelector('input.ql-image[type=file]');
            if (fileInput == null) {
                fileInput = document.createElement('input');
                fileInput.setAttribute('type', 'file');
                fileInput.setAttribute('accept', 'image/png, image/gif, image/jpeg, image/bmp, image/x-icon');
                fileInput.classList.add('ql-image');
                fileInput.addEventListener('change', function () {
                    if (fileInput.files != null && fileInput.files[0] != null) {
                        saveToServer(fileInput.files[0]);
                    }
                });
                toolbar.container.appendChild(fileInput);
            }
            fileInput.click();
        };

        // 上传到服务器@param {File} file
        function saveToServer(file) {
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
        function insertToEditor(url) {
            // push image url to rich editor.
            var range = quillEditor.getSelection();
            var imagePosition = 0;
            if (range) {
                imagePosition = range.index;
            }
            quillEditor.insertEmbed(imagePosition, 'image', url);
        }
    }
})();
