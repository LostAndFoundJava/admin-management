(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.management')
        .controller('ExhibitionCtrl', ExhibitionCtrl);

    angular.module('KingAdmin.pages.exhibition.management').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function ExhibitionCtrl($scope, $timeout,toastr, $stateParams, $state, ExhibitionService) {

        var kt = this;
        kt.exhibition = {};
        kt.exhibition.exhibitionDetail = {};
        kt.exhibition.exhibitionDetail.files = [];
        var imageFile={};
        var quillEditor;
        var delta;
        // var container = document.getElementById('description');
        // var editor = new Quill(container);

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        if ($stateParams.id) {
            ExhibitionService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.exhibition = data;
                    quillEditor.pasteHTML(kt.exhibition.exhibitionDetail.description);

                })
        } else {
            kt.isAdd = true;
        }

        kt.save = function () {


            kt.exhibition.startTime = formatDate(kt.exhibition.startTime);
            kt.exhibition.endTime = formatDate(kt.exhibition.endTime);
            kt.exhibition.hot = kt.exhibition.hot ? 1 : 0;
            kt.exhibition.hasCarousel = kt.exhibition.hasCarousel ? 1 : 0;
            // kt.exhibition.exhibitionDetail.description = quillEditor.getText();

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
            // $scope.today();
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
            //Set options for dropzone
            //Visit http://www.dropzonejs.com/#configuration-options for more options
            $scope.showBtns = false;
            $scope.lastFile = null;
            $scope.getDropzone = function(){
                console.log($scope.dzMethods.getDropzone());
                alert('Check console log.');
            };
            $scope.getFiles = function(){
                console.log($scope.dzMethods.getAllFiles());
                alert('Check console log.');
            };

            $scope.dzOptions = {
                url: '/api/mgr/image/upload',
                // url: '/',
                paramName: 'file',
                maxFilesize: '10',
                acceptedFiles: 'image/jpeg, images/jpg, image/png',
                addRemoveLinks: true,
                parallelUploads : 5,
                autoProcessQueue : false
            };


            //Handle events for dropzone
            //Visit http://www.dropzonejs.com/#events for more events
            $scope.dzCallbacks = {
                'addedfile': function (file) {
                    $scope.showBtns = true;
                    $scope.lastFile = file;
                    // const fd = new FormData();
                    // fd.append("file",file);

                    /*var file = file.file;
                    var reader = new FileReader();
                    var url = getObjectURL(file);

                    reader.readAsDataURL(file);
                    console.log(file);
                    $scope.newFile = file;*/
                },
                'success': function (file, xhr) {
                    console.log(file, xhr);
                    if(!xhr && xhr.code !=0){
                        toastr.error(xhr.msg);
                    }
                    imageFile.fileUrl = xhr.result.fileUrl;
                    var length = kt.exhibition.exhibitionDetail.files.length;
                    kt.exhibition.exhibitionDetail.files[length] = imageFile;

                },
                'error' : function(file, xhr){
                    console.warn('File failed to upload from dropzone 2.', file, xhr);
                }

            };


            $scope.dzMethods = {};
            $scope.removeNewFile = function () {
                $scope.dzMethods.removeFile($scope.newFile);
            }
        }

        initDropzone();

        /*======================*/

        /**
         * 富文本框
         */
        function initQuill() {
            $scope.model = ''
            $scope.readOnly = false

            $scope.changeDetected = false;


        }

        initQuill();

        $scope.editorCreated = function (editor) {
            console.log(editor);
            quillEditor = editor;
            if (editor) {
                var toolbar = quillEditor.getModule('toolbar');
                editor.getModule("toolbar").addHandler("image", function () {
                    selectImage(toolbar);
                });
            }

        }
        $scope.editorCreated();

        $scope.contentChanged = function (editor, html, text) {
            $scope.changeDetected = true;
            kt.exhibition.exhibitionDetail.description = html;
            /*console.log('editor: ', editor, 'html: ', html, 'text:', text)
            var toolbar = quillEditor.getModule('toolbar');
            var fileInput = quillEditor.getModule('toolbar').container.querySelector('input.ql-image[type=file]');
            if (fileInput.files != null && fileInput.files[0] != null) {
                saveToServer(fileInput.files[0]);
            }*/

        }

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

        // @param {File} file
        function saveToServer(file) {
            //upload on server
            const fd = new FormData();
            fd.append('image', file);

            ExhibitionService.uploadFile(fd,
             function (data) {
                if(data && data.code == 0){
                    insertToEditor(data.result.fileUrl);

                }
             })

        }

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
