(function () {
    'use strict';

    angular.module('KingAdmin.pages.sys.src', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('sys.src', {
                url: '/src',
                templateUrl: 'app/pages/sys/src/srclist.html',
                controller: 'SrcListCtrl',
                controllerAs: 'kt',
                title: '渠道来源管理',
                sidebarMeta: {
                    order: 0,
                    icon: 'ion-grid',
                },
            })
            .state('sys.src.add', {
                url: '/add',
                title: '新增渠道',
                views: {
                    '@': {
                        templateUrl: 'app/pages/sys/src/src.html',
                        controller: 'SrcCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('sys.src.edit', {
                url: '/edit/:id',
                title: '编辑渠道',
                views: {
                    '@': {
                        templateUrl: 'app/pages/sys/src/src.html',
                        controller: 'SrcCtrl',
                        controllerAs: 'kt',
                    }
                }
            }).state('sys.src.view', {
            url: '/view/:id?isView',
            title: '查看渠道',
            views: {
                '@': {
                    templateUrl: 'app/pages/sys/src/src.html',
                    controller: 'SrcCtrl',
                    controllerAs: 'kt',
                }
            }
        });
        $urlRouterProvider.when('/sys', '/sys/src');
    }

})();
