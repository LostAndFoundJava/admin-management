(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.advice', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('excelupload.advice', {
                url: '/management/advice',
                templateUrl: 'app/pages/excelupload/advice/advicelist.html',
                // controller: 'AppController',
                controller: 'AdviceListCtrl',
                controllerAs: 'kt',
                title: '用户咨询',
                sidebarMeta: {
                    order: 1,
                },
            });
    }

})();