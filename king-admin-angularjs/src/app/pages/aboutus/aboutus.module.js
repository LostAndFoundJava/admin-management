(function () {
    'use strict';

    angular.module('KingAdmin.pages.aboutus', [
        'KingAdmin.pages.aboutus.aboutus',
    ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('aboutus', {
                url: '/mgr/aboutus',
                template: '<ui-view></ui-view>',
                abstract: true,
                title: '首页aboutus',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            });
    }

})();
