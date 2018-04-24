(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload', [
        'KingAdmin.pages.excelupload.excelupload',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('excelupload', {
                url: '/mgr/flowsrc',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '用户报名管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
