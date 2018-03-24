(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition')
        .controller('NewsEditCtrl', NewsEditCtrl)


    /** @ngInject */
    function NewsEditCtrl($scope, $filter, toastr, NewsEditService, $timeout) {
        var kt = this;

        initQill();
        initDropzone();
        function initQill() {
            $scope.title = 'Quill works'
            $scope.model = ''
            $scope.readOnly = false
            $scope.test = ''
            $scope.customOptions = [{
                import: 'attributors/style/size',
                whitelist: ['14', '16', '18', 'small', 'large', 'huge']
            }]
            $scope.customModules = {
                toolbar: [
                    [{'size': [false, '14', '16', '18']}]
                ]
            }

            $timeout(function () {
                $scope.title += ' awsome!!!'
            }, 2000)

            $scope.editorCreated = function (editor) {
                console.log(editor)
            }
            $scope.contentChanged = function (editor, html, text, delta, oldDelta, source) {
                console.log('editor: ', editor, 'html: ', html, 'text:', text, 'delta: ', delta, 'oldDelta:', oldDelta, 'source:', source)
            }
            $scope.selectionChanged = function (editor, range, oldRange, source) {
                console.log('editor: ', editor, 'range: ', range, 'oldRange:', oldRange, 'source:', source)
            }


            //初始化dropzone
            $scope.partialDownloadLink = 'http://localhost:8080/download?filename=';
            $scope.filename = '';

            $scope.uploadFile = function () {
                $scope.processQueue();
            };

            $scope.reset = function () {
                $scope.resetDropzone();
            };
        }

        function initDropzone() {
            //Set options for dropzone
            //Visit http://www.dropzonejs.com/#configuration-options for more options
            $scope.dzOptions = {
                url: '/upload',
                paramName: 'photo',
                maxFilesize: '10',
                acceptedFiles: 'image/jpeg, images/jpg, image/png',
                addRemoveLinks: true,
            };


            //Handle events for dropzone
            //Visit http://www.dropzonejs.com/#events for more events
            $scope.dzCallbacks = {
                'addedfile': function (file) {
                    console.log(file);
                    $scope.newFile = file;
                },
                'success': function (file, xhr) {
                    console.log(file, xhr);
                },

            };


            $scope.dzMethods = {};
            $scope.removeNewFile = function () {
                $scope.dzMethods.removeFile($scope.newFile);
            }

        }
    }
})();
