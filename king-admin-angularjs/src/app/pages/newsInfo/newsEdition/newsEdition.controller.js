(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition')
        .controller('NewsEditCtrl', NewsEditCtrl)


    /** @ngInject */
    function NewsEditCtrl($scope, $filter, toastr, NewsEditService, $timeout) {
        var kt = this;
        kt.newsEdition = {}
        initQill();

        initDropzone();


        kt.save = function () {
            NewsEditService.save(kt.newsEdition, function (data) {
                    $state.go('newsInfo/newsEdition');
                }
            );
        }

        function initQill() {
            $scope.title = 'Quill works'
            kt.newsEdition.subtitle = 'abc'
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
        }

        function initDropzone() {
            //Set options for dropzone
            //Visit http://www.dropzonejs.com/#configuration-options for more options
            kt.newsEdition.request = [];
            $scope.dzOptions = {
                url: '/sys/upload',
                paramName: 'photo',
                maxFilesize: '10',
                acceptedFiles: 'image/jpeg, images/jpg, image/png',
                addRemoveLinks: true,
                uploadMultiple: true,
                parallelUploads: 20,
                autoProcessQueue: false,
                // dictDefaultMessage: 'Click to add or drop photos',
                dictRemoveFile: 'Remove photo',
                dictResponseError: 'Could not upload this photo'
            };

            $scope.showBtns = true;
            $scope.lastFile = null;
            $scope.getDropzone = function(){
                console.log($scope.dzMethods.getDropzone());
                alert('Check console log.');
            };
            $scope.getFiles = function(){
                console.log($scope.dzMethods.getAllFiles());
                alert('Check console log.');
            };

            // $scope.dzOptions = {
            //     url : '/upload_2.php',
            //     dictDefaultMessage : 'Add files to show dropzone methods (few)',
            //     acceptedFiles : 'image/jpeg',
            //     parallelUploads : 5,
            //     autoProcessQueue : false
            // };

            $scope.dzMethods = {};

            $scope.dzCallbacks = {
                'addedfile' : function(file){
                    $scope.showBtns = true;
                    $scope.lastFile = file;
                    kt.newsEdition.request.push(file);
                },
                'error' : function(file, xhr){
                    console.warn('File failed to upload from dropzone 2.', file, xhr);
                }
            };
        }
    }
})();
