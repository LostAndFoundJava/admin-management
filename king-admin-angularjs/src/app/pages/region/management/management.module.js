(function () {
    'use strict';

    angular.module('KingAdmin.pages.region.management', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('region.management', {
                url: '/management',
                templateUrl: 'app/pages/region/management/managementlist.html',
                controller: 'HomepageListCtrl',
                controllerAs: 'kt',
                title: '首页管理',
                sidebarMeta: {
                    order: 1,
                },
            })
            .state('region.management.add', {
                url: '/add',
                title: '新增首页配置',
                views: {
                    '@': {
                        templateUrl: 'app/pages/region/management/management.html',
                        controller: 'HomepageCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('region.management.edit', {
                url: '/edit/:id',
                title: '编辑首页配置',
                views: {
                    '@': {
                        templateUrl: 'app/pages/homepage/management/management.html',
                        controller: 'HomepageCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('region.management.view', {
                url: '/view/:id?isView',
                title: '查看首页配置',
                views: {
                    '@': {
                        templateUrl: 'app/pages/region/management/management.html',
                        controller: 'HomepageCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();
