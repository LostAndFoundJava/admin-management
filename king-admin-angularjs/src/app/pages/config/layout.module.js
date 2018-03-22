(function () {
    'use strict';

    angular.module('KingAdmin.pages.config', ['ngQuill'])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($httpProvider,ngQuillConfigProvider) {

        $httpProvider.interceptors.push('authExpiredInterceptor');
        ngQuillConfigProvider.set(null, null, 'custom placeholder')

    }

})();
