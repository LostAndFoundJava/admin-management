(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload.visaupload', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('visaupload.visaupload', {
                url: '/visaupload',
                templateUrl: 'app/pages/visaupload/visaupload/visaupload.html',
                // controller: 'AppController',
                controller: 'VisaUploadCtrl',
                controllerAs: 'kt',
                title: '签证信息导入',
                sidebarMeta: {
                    order: 1,
                },
            });
    }

})();