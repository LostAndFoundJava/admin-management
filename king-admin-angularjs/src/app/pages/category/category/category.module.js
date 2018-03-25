(function () {
    'use strict';

    angular.module('KingAdmin.pages.category.category', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('category.category', {
                url: '/management',
                templateUrl: 'app/pages/category/category/categorylist.html',
                controller: 'CategoryListCtrl',
                controllerAs: 'kt',
                title: '行业管理',
                sidebarMeta: {
                    order: 1,
                },
            })
            .state('category.category.add', {
                url: '/add',
                title: '新增展会',
                views: {
                    '@': {
                        templateUrl: 'app/pages/category/category/category.html',
                        controller: 'CategoryCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('category.category.edit', {
                url: '/edit/:id',
                title: '编辑展会',
                views: {
                    '@': {
                        templateUrl: 'app/pages/category/category/category.html',
                        controller: 'CategoryCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('category.category.view', {
                url: '/view/:id?isView',
                title: '查看展会',
                views: {
                    '@': {
                        templateUrl: 'app/pages/category/category/category.html',
                        controller: 'CategoryCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();
