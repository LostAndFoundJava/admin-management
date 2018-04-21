(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload', [
        'KingAdmin.pages.visaupload.visaupload',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('visaupload', {
                url: '/api/mgr/visa',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: 'visa文件导入',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
