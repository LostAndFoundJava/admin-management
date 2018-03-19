(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.management', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('exhibition.management', {
                url: '/management',
                templateUrl: 'app/pages/exhibition/management/managementlist.html',
                controller: 'ExhibitionListCtrl',
                controllerAs: 'kt',
                title: '展会管理',
                sidebarMeta: {
                    order: 1,
                },
            })
            .state('exhibition.management.add', {
                url: '/add',
                title: '新增展会',
                views: {
                    '@': {
                        templateUrl: 'app/pages/exhibition/management/management.html',
                        controller: 'ExhibitionCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('exhibition.management.edit', {
                url: '/edit/:id',
                title: '编辑展会',
                views: {
                    '@': {
                        templateUrl: 'app/pages/exhibition/management/management.html',
                        controller: 'ExhibitionCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('exhibition.management.view', {
                url: '/view/:id?isView',
                title: '查看展会',
                views: {
                    '@': {
                        templateUrl: 'app/pages/exhibition/management/management.html',
                        controller: 'ExhibitionCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();
