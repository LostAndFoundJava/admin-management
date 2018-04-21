(function () {
    'use strict';

    angular.module('KingAdmin.pages.link.management', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('link.management', {
                url: '/management',
                templateUrl: 'app/pages/link/management/managementlist.html',
                controller: 'LinkListCtrl',
                controllerAs: 'kt',
                title: '首页友情链接',
                sidebarMeta: {
                    order: 1,
                },
            })
            .state('link.management.add', {
                url: '/add',
                title: '新增友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/link/management/management.html',
                        controller: 'LinkCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('link.management.edit', {
                url: '/edit/:id',
                title: '编辑友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/link/management/management.html',
                        controller: 'LinkCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('link.management.view', {
                url: '/view/:id?isView',
                title: '查看友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/homepage/management/management.html',
                        controller: 'LinkCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();
