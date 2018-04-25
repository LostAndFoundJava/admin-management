(function () {
    'use strict';

    angular.module('KingAdmin.pages.aboutus.management', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('aboutus.management', {
                url: '/management',
                templateUrl: 'app/pages/aboutus/management/managementlist.html',
                controller: 'AboutUsListCtrl',
                controllerAs: 'kt',
                title: '友情链接列表',
                sidebarMeta: {
                    order: 1,
                },
            })
            .state('aboutus.management.add', {
                url: '/add',
                title: '新增友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/aboutus/management/management.html',
                        controller: 'AboutUsCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('aboutus.management.edit', {
                url: '/edit/:id',
                title: '编辑友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/aboutus/management/management.html',
                        controller: 'AboutUsCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('aboutus.management.view', {
                url: '/view/:id?isView',
                title: '查看友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/aboutus/management/management.html',
                        controller: 'AboutUsCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();
