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
<<<<<<< HEAD
        'KingAdmin.pages.homepage',
=======
>>>>>>> bdaadb568b5c124bd820fdcd4b83afc8ce4ccfae
        'KingAdmin.pages.category',
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise("/home");

    }

})();
