(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload', [
        'KingAdmin.pages.visaupload.management',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('visa', {
                url: '/mgr/visa',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '签证管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
