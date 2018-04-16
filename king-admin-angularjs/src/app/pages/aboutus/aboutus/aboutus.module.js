(function () {
    'use strict';

    angular.module('KingAdmin.pages.aboutus.aboutus', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('aboutus.aboutus', {
                url: '/aboutdetail',
                templateUrl: 'app/pages/aboutus/aboutus/aboutus.html',
                // controller: 'AppController',
                controller: 'ExcelUploadCtrl',
                controllerAs: 'kt',
                title: '首页aboutus',
                sidebarMeta: {
                    order: 1,
                },
            });
    }

})();