(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('newsInfo.newsEdition', {
                url: '/newsEdition',
                templateUrl: 'app/pages/newsInfo/newsEdition/newsEditionlist.html',
                controller: 'newsEditonListCtrl',
                controllerAs: 'kt',
                title: '新闻上传',
                sidebarMeta: {
                    order: 1,
                },
            }).state('newsInfo.newsEditon.add', {
            url: '/add',
            title: '新增新闻',
            views: {
                '@': {
                    templateUrl: 'app/pages/newsInfo/newsEditon/newsEdition.html',
                    controller: 'newsEditionCtrl',
                    controllerAs: 'kt',
                }
            }
        })
            .state('newsInfo.newsEditon.edit', {
                url: '/edit/:id',
                title: '编辑新闻',
                views: {
                    '@': {
                        templateUrl: 'app/pages/newsInfo/newsEditon/newsEdition.html',
                        controller: 'newsEditionCtrl',
                        controllerAs: 'kt',
                    }
                }
            })
            .state('newsInfo.newsEditon.view', {
                url: '/view/:id?isView',
                title: '查看新闻',
                views: {
                    '@': {
                        templateUrl: 'app/pages/newsInfo/newsEditon/newsEdition.html',
                        controller: 'newsEditionCtrl',
                        controllerAs: 'kt',
                    }
                }
            });
    }

})();