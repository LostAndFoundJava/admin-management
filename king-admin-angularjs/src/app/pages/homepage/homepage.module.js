(function () {
    'use strict';

    angular.module('KingAdmin.pages.homepage', [
        'KingAdmin.pages.homepage.management',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('homepage', {
                url: '/mgr/homepage',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '首页管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
