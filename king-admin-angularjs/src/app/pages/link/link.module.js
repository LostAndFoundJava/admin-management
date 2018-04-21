(function () {
    'use strict';

    angular.module('KingAdmin.pages.link', [
        'KingAdmin.pages.link.management',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('link', {
                url: '/mgr/link',
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
