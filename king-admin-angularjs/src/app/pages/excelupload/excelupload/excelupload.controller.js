(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.excelupload')
        .controller('ExcelUploadCtrl', ExcelUploadCtrl);
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
