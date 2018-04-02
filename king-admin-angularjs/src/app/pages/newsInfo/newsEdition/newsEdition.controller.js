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
                maxFilesize: '30',
                acceptedFiles: 'image/jpeg, images/jpg, image/png',
                addRemoveLinks: true,
                uploadMultiple: true,
                parallelUploads: 20,
                autoProcessQueue: false,
                // dictDefaultMessage: 'Click to add or drop photos',
                dictRemoveFile: 'Remove photo',
                dictResponseError: 'Could not upload this photo',
                init: function() {

                }
            };

            $scope.showBtns = true;
            $scope.lastFile = null;

            $scope.getFiles = function(){
                console.log($scope.dzMethods.getAllFiles());
                alert('Check console log.');
            };

            ////////////////////
             var demoThumbUrl = "http://47.97.201.63:8081/pic/2015/09/04/hello.jpg!300-300";

            $scope.mockFiles = [
                {name:'mock_file_1', size : 5000, isMock : true, serverImgUrl : demoThumbUrl},
                 {name:'mock_file_2', size : 5000, isMock : true, serverImgUrl : 'http://i.imgur.com/BwTuOlQ.jpg'},
                // {name:'mock_file_3', size : 5000, isMock : true, serverImgUrl : demoThumbUrl}
            ];
            $scope.dzMethods = {};
            $scope.getDropzone = function(){
                console.log($scope.dzMethods.getDropzone())
            };

            var dropzone

            $timeout(function(){
                dropzone = $scope.dzMethods.getDropzone();
                $scope.mockFiles.forEach(function(mockFile){
                    dropzone.emit('addedfile', mockFile);
                    dropzone.emit('complete', mockFile);
                    dropzone.options.maxFiles = $scope.dzOptions.maxFilesize - $scope.mockFiles.length;
                    dropzone.files.push(mockFile);
                });
            });
            // setting callbacks
            $scope.dzCallbacks = {
                'addedfile' : function(file){
                    if(file.isMock){
                       dropzone.createThumbnailFromUrl(file, file.serverImgUrl, null, true);
                    }

                    $scope.showBtns = true;
                    $scope.lastFile = file;
                },
                'error' : function(file, xhr){
                    console.warn('File failed to upload from dropzone 2.', file, xhr);
                }
            };

        }
    }
})();
