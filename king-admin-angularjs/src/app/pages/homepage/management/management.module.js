(function () {
    'use strict';

    angular.module('KingAdmin.pages.homepage.management', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('homepage.management', {
                url: '/management',
                templateUrl: 'app/pages/homepage/management/management.html',
                controller: 'HomepageCtrl',
                controllerAs: 'kt',
                title: '首页管理',
                sidebarMeta: {
                    order: 1,
                },
            })
            .state('homepage.management.add', {
                url: '/add',
                title: '新增首页配置',
                views: {
                    '@': {
                        templateUrl: 'app/pages/homepage/management/management.html',
                        controller: 'HomepageCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('homepage.management.edit', {
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
            .state('homepage.management.view', {
                url: '/view/:id?isView',
                title: '查看首页配置',
                views: {
                    '@': {
                        templateUrl: 'app/pages/homepage/management/management.html',
                        controller: 'HomepageCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();
