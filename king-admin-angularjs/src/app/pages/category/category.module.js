(function () {
    'use strict';

    angular.module('KingAdmin.pages.category', [
        'KingAdmin.pages.category.category',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('category', {
                url: '/mgr/category',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '行业管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }
})();
