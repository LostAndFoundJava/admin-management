(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload.visaupload')
        .controller('VisaUploadCtrl', VisaUploadCtrl);
    /** @ngInject */
    function VisaUploadCtrl($scope, Upload, $timeout) {
        var kt = this;
        $scope.uploadFiles = function (files) {
            $scope.files = files;
            if (files && files.length) {
                Upload.upload({
                    url: '/api/mgr/visa/visaupload',
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
