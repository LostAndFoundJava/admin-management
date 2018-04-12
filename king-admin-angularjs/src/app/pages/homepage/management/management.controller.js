(function () {
    'use strict';

    angular.module('KingAdmin.pages.homepage.management')
        .controller('HomepageCtrl', HomepageCtrl);

    angular.module('KingAdmin.pages.homepage.management').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function HomepageCtrl($scope, $timeout, toastr, $stateParams, $state, HomepageService,CategoryService) {

        var kt = this;
        kt.homepage = {};
        kt.homepageConfig = {};

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        CategoryService.getList({homepageId:$stateParams.id},function (data) {
            kt.homepage.categoryList = data.result;
        })

        HomepageService.getExhibitions({homepageId:$stateParams.id},function (data) {
            kt.homepage.exhibitionList = data.result;
        })

        //由id判断是新增还是修改/查看（回显数据）
        if ($stateParams.id) {
            HomepageService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.homepage = data;
                    if (data.extension) {
                        var extension = JSON.parse(data.extension);
                        kt.homepage.extension = extension;
                    }
                })
        } else {
            kt.isAdd = true;
        }

        //保存新增／修改的数据
        kt.save = function () {
            kt.homepage.extension = JSON.stringify(kt.homepage.extension);
            kt.homepage.homePageCategoryList = [];
            kt.homepage.categoryExhibitionList = [];

            //首页精选行业
            angular.forEach(kt.homepage.categoryList, function(category) {
                if(category.checked){
                    var homePageCategory = {}
                    homePageCategory.categoryId = category.id;
                    homePageCategory.isChoice = 1;
                    kt.homepage.homePageCategoryList.push(homePageCategory);
                }
            })

            //首页设置
            angular.forEach(kt.homepage.exhibitionList,function(exhibition){
                if(exhibition.hot || exhibition.isCarousel || exhibition.isChoice){
                    var categoryExhibition = {};
                    categoryExhibition.categoryId = exhibition.categoryId;
                    categoryExhibition.exhibitionId = exhibition.id;
                    if(exhibition.hot){
                        categoryExhibition.isHot = 1;
                    }
                    if(exhibition.isCarousel){
                        categoryExhibition.isCarousel = 1;
                    }
                    if(exhibition.isChoice){
                        categoryExhibition.isChoice = 1;
                    }
                    kt.homepage.categoryExhibitionList.push(categoryExhibition);
                }

            })

            HomepageService.save(kt.homepage, function (data) {
                $state.go('homepage.management');
            });
        }
        kt.basicConfig = {
            core: {
                check_callback: true,
                worker: true
            },
            'types': {
                'default': {
                    'icon': false
                }
            },
            "checkbox": {
                "keep_selected_style": false
            },
            'plugins': ['types', "wholerow", 'checkbox'],
            'version': 1
        };


    }
})();
