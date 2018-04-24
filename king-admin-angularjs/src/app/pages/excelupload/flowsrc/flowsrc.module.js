(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.flowsrc', [])
        .config(routeConfig);

      /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('excelupload.flowsrc', {
                url: '/management',
                templateUrl: 'app/pages/excelupload/flowsrc/flowsrclist.html',
                // controller: 'AppController',
                controller: 'FlowSrcListCtrl',
                controllerAs: 'kt',
                title: 'excel导入',
                sidebarMeta: {
                    order: 1,
                },
            })
            .state('excelupload.flowsrc.add', {
                url: '/add',
                title: '新增友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/excelupload/flowsrc/flowsrc.html',
                        controller: 'FlowSrcCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('excelupload.flowsrc.edit', {
                url: '/edit/:id',
                title: '编辑友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/excelupload/flowsrc/flowsrc.html',
                        controller: 'FlowSrcCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('excelupload.flowsrc.view', {
                url: '/view/:id?isView',
                title: '查看友情链接',
                views: {
                    '@': {
                        templateUrl: 'app/pages/excelupload/flowsrc/flowsrc.html',
                        controller: 'FlowSrcCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();