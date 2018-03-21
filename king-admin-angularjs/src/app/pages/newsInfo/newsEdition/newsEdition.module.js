(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('newsInfo.newsEdition', {
                url: '/newsEdition',
                templateUrl: 'app/pages/newsInfo/newsEdition/newsEdition.html',
                controller: '',
                controllerAs: 'kt',
                title: '新闻上传',
                sidebarMeta: {
                    order: 1,
                },
            });
    }

})();