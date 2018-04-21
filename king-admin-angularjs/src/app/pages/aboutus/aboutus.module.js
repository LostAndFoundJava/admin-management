(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload', [
        'KingAdmin.pages.excelupload.excelupload',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('aboutus', {
                url: '/mgr/aboutus',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '关于我们',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
