(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo', [
        'KingAdmin.pages.newsInfo.newsEdition',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('newsInfo', {
                url: '/newsInfo',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '新闻上传',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
