(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.listing', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('exhibition.listing', {
                url: '/listing',
                templateUrl: 'app/pages/exhibition/listing/listinglist.html',
                controller: 'ListingListCtrl',
                controllerAs: 'kt',
                title: '展会发布',
                sidebarMeta: {
                    order: 2,
                },
            })
            .state('exhibition.listing.listing', {
                url: '/listing/:id',
                title: '展会上架',
                views: {
                    '@': {
                        templateUrl: 'app/pages/exhibition/listing/listing.html',
                        controller: 'ListingCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('exhibition.listing.delisting', {
                url: '/delisting/:id',
                title: '展会下架',
                views: {
                    '@': {
                        templateUrl: 'app/pages/exhibition/listing/listing.html',
                        controller: 'ListingCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();
