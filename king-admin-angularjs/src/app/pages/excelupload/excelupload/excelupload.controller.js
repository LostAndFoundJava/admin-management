(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.excelupload')
        .controller('ExcelUploadCtrl', ExcelUploadCtrl);




    // angular.module('KingAdmin.pages.excelupload.excelupload', ['angularFileUpload'])
    //     .controller('AppController', ['$scope', 'FileUploader', function($scope, FileUploader) {
    //         var uploader = $scope.uploader = new FileUploader({
    //             url: 'upload.php'
    //         });
    //
    //         // FILTERS
    //
    //         uploader.filters.push({
    //             name: 'customFilter',
    //             fn: function(item /*{File|FileLikeObject}*/, options) {
    //                 return this.queue.length < 10;
    //             }
    //         });
    //
    //         // CALLBACKS
    //
    //         uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
    //             console.info('onWhenAddingFileFailed', item, filter, options);
    //         };
    //         uploader.onAfterAddingFile = function(fileItem) {
    //             console.info('onAfterAddingFile', fileItem);
    //         };
    //         uploader.onAfterAddingAll = function(addedFileItems) {
    //             console.info('onAfterAddingAll', addedFileItems);
    //         };
    //         uploader.onBeforeUploadItem = function(item) {
    //             console.info('onBeforeUploadItem', item);
    //         };
    //         uploader.onProgressItem = function(fileItem, progress) {
    //             console.info('onProgressItem', fileItem, progress);
    //         };
    //         uploader.onProgressAll = function(progress) {
    //             console.info('onProgressAll', progress);
    //         };
    //         uploader.onSuccessItem = function(fileItem, response, status, headers) {
    //             console.info('onSuccessItem', fileItem, response, status, headers);
    //         };
    //         uploader.onErrorItem = function(fileItem, response, status, headers) {
    //             console.info('onErrorItem', fileItem, response, status, headers);
    //         };
    //         uploader.onCancelItem = function(fileItem, response, status, headers) {
    //             console.info('onCancelItem', fileItem, response, status, headers);
    //         };
    //         uploader.onCompleteItem = function(fileItem, response, status, headers) {
    //             console.info('onCompleteItem', fileItem, response, status, headers);
    //         };
    //         uploader.onCompleteAll = function() {
    //             console.info('onCompleteAll');
    //         };
    //
    //         console.info('uploader', uploader);
    //     }]);


    /** @ngInject */
    function ExcelUploadCtrl($scope, Upload, $timeout) {
        var kt = this;
        $scope.uploadFiles = function (files) {
            $scope.files = files;
            if (files && files.length) {
                Upload.upload({
                    url: '/api/mgr/excel/excelupload',
                    data: {
                        files: files
                    }
                }).then(function (response) {
                    $timeout(function () {
                        $scope.result = response.data;
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

    }

})();
