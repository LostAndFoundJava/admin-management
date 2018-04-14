(function () {
    'use strict';

    angular.module('KingAdmin.pages.region', [
        'KingAdmin.pages.region.management',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('region', {
                url: '/mgr/grion',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '地域管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
