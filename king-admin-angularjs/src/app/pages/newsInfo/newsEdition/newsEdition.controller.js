(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition')
        .controller('newsEditionCtrl', newsEditionCtrl);


    angular.module('KingAdmin.pages.newsInfo.newsEdition').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function newsEditionCtrl($scope, $timeout, toastr, $stateParams, $state,newsEditionService) {

        var kt = this;
        kt.newsEdition = {};
        kt.newsEditionVO = {}

        var quillEditor;
        var delta;

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }
        //quill只读
        $scope.ifshow = !kt.isView
        $scope.readonly = kt.isView

        //显示所有的行业和新闻类别
        $timeout(function () {
            newsEditionService.getSelectCategoryInfo(function (data) {
                //行业
                kt.newsEdition.category=[]
                kt.newsEdition.categoryName=[]

                kt.newsEdition.category=data.result
                angular.forEach(data.result, function (category) {

                    kt.newsEdition.categoryName.push(category.name)
                })

                angular.forEach(data.result,function (category) {
                    if(category.id == kt.newsEdition.categoryId) {
                        kt.newsEdition.categoryId = category.name
                    }
                })
            });

            newsEditionService.getSelectNewscategoryInfo(function (data) {
                //新闻类别
                kt.newsEdition.newsCategorys = []
                angular.forEach(data.result,function (newsCategory) {
                    kt.newsEdition.newsCategorys.push(newsCategory.newsCategory)
                })
            });
        })

        //由id判断是新增还是修改/查看（回显数据）
        if ($stateParams.id) {
            newsEditionService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.newsEdition = data;
                    if(kt.newsEdition.hot==1)
                        kt.newsEdition.hot=true
                    else
                        kt.newsEdition.hot=false
                    $timeout(function () {
                        if (kt.newsEdition.content) {
                            $scope.model = kt.newsEdition.content;
                        }
                    });
                })
        } else {
            kt.isAdd = true;

        }

        //保存新增／修改的数据
        kt.save = function () {
            angular.forEach(kt.newsEdition.category, function (category) {
                if(kt.newsEdition.categoryId == category.name){
                    kt.newsEditionVO.categoryId = category.id
                }
            })


            kt.newsEditionVO.hot = kt.newsEdition.hot ? 1 : 0;
            kt.newsEditionVO.title = kt.newsEdition.title;
            kt.newsEditionVO.newsCategory = kt.newsEdition.newsCategory;

            newsEditionService.save(kt.newsEditionVO, function (data) {
                $state.go('newsInfo.newsEdition');
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
            kt.newsEditionVO.content = html;
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

            newsEditionService.uploadFile(fd,
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
