(function() {
    'use strict';

    angular
        .module('KingAdmin.pages.config')
        .factory('authExpiredInterceptor', authExpiredInterceptor);

    /** @ngInject */
    function authExpiredInterceptor($q) {
        var service = {
            responseError: responseError,
            response: response,
        };

        return service;

        function responseError(response) {
            // if(response.status === 302){
            //     window.location.href = 'auth.html';
            // }
            // if (response.status === 404) {
            //     window.location.href = '404.html';
            // }
            // if (response.code === 401) {
            //     window.location.href = 'auth.html';
            // }
        }

        function response(response) {
            if (response.data.code === 401) {
                window.location.href = 'auth.html';
            }
            return response;
        }
    }
})();
