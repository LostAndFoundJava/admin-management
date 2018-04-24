(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.excelupload', [])
        .config(routeConfig);
    // angular.module('KingAdmin.pages.excelupload.excelupload', [
    //     'angularFileUpload'
    // ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('excelupload.excelupload', {
                url: '/management/excelupload',
                templateUrl: 'app/pages/excelupload/excelupload/excelupload.html',
                // controller: 'AppController',
                controller: 'ExcelUploadCtrl',
                controllerAs: 'kt',
                title: 'excel导入',
                sidebarMeta: {
                    order: 1,
                },
            });
    }

})();