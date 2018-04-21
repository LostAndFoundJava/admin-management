(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload.management', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('visa.management', {
                url: '/management',
                templateUrl: 'app/pages/visaupload/management/managementlist.html',
                controller: 'VisaUploadListCtrl',
                controllerAs: 'kt',
                title: '签证列表',
                sidebarMeta: {
                    order: 1,
                },
            })
            .state('visa.management.add', {
                url: '/add',
                title: '新增签证',
                views: {
                    '@': {
                        templateUrl: 'app/pages/visaupload/management/management.html',
                        controller: 'VisaUploadCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('visa.management.edit', {
                url: '/edit/:id',
                title: '编辑签证',
                views: {
                    '@': {
                        templateUrl: 'app/pages/visaupload/management/management.html',
                        controller: 'VisaUploadCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('visa.management.view', {
                url: '/view/:id?isView',
                title: '查看签证',
                views: {
                    '@': {
                        templateUrl: 'app/pages/visaupload/management/management.html',
                        controller: 'VisaUploadCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();
