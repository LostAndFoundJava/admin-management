(function () {
    'use strict';

    angular.module('KingAdmin.pages', [
        'ui.router',
        'KingAdmin.pages.config',
        'KingAdmin.pages.home',
        'KingAdmin.pages.common',
        'KingAdmin.pages.sys',
        'KingAdmin.pages.dict',
        'KingAdmin.pages.exhibition',
        'KingAdmin.pages.newsInfo',
        'KingAdmin.pages.homepage',
        'KingAdmin.pages.category',
        'KingAdmin.pages.region',
        'KingAdmin.pages.excelupload',
        'KingAdmin.pages.visaupload',
        'KingAdmin.pages.link',

    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise("/home");

    }

})();
