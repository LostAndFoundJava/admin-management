(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload', [
        'KingAdmin.pages.excelupload.excelupload',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('excelupload', {
                url: '/excelupload',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: 'excel导入',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
