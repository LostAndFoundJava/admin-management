(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition', [
        'KingAdmin.pages.exhibition.management',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('exhibition', {
                url: '/api/mgr/exhibition',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '展会发布管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
