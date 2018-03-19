(function() {
    'use strict';

    angular
        .module('KingAdmin.pages.config')
        .factory('authExpiredInterceptor', authExpiredInterceptor);

    /** @ngInject */
    function authExpiredInterceptor($q) {
        var service = {
            responseError: responseError
        };

        return service;

        function responseError(response) {
            console.log("响应："+response);
            console.log("响应状态："+response.status);
            // if(response.status === 302){
            //     window.location.href = 'auth.html';
            // }
            // if (response.status === 404) {
            //     window.location.href = '404.html';
            // }
            // if (response.status === 401) {
            //     window.location.href = 'auth.html';
            // }


            // return $q.reject(response);
        }
    }
})();
