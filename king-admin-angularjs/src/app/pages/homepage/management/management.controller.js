(function () {
    'use strict';

    angular.module('KingAdmin.pages.homepage.management')
        .controller('HomepageCtrl', HomepageCtrl);

    angular.module('KingAdmin.pages.homepage.management').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function HomepageCtrl($scope, $timeout, toastr, $stateParams, $state, HomepageService, CategoryService, RegionService, ExhibitionService) {

        var kt = this;
        //回显
        kt.homepage = {};

        //提交服务器
        kt.homepageConfig = {};
        kt.homepageConfig.extension = {};
        // kt.homepageConfig.homePageCategoryList = [];
        // kt.homepageConfig.categoryExhibitionList = [];

        kt.category = {};

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        CategoryService.getList({homepageId: $stateParams.id}, function (data) {
            kt.homepage.categoryList = data.result;
            $scope.getExhibitionByCategoryIds();
        })

        HomepageService.getExhibitions({homepageId: $stateParams.id}, function (data) {
            kt.homepage.exhibitionList = data.result;
            angular.forEach(kt.homepage.exhibitionList, function (exhibition) {
                if (exhibition.hot == 1) {
                    exhibition.hot = true;
                }

                if (exhibition.hasCarousel == 1) {
                    exhibition.hasCarousel = true;
                }
            })
            // kt.homepage.exhibitions = data.result;
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
            kt.homepageConfig.homePageCategoryList = [];
            kt.homepageConfig.categoryExhibitionList = [];

            //首页精选行业
            angular.forEach(kt.homepage.categoryList, function (category) {
                if (category.checked) {
                    var homePageCategory = {};
                    homePageCategory.categoryId = category.id;
                    homePageCategory.isChoice = 1;
                    kt.homepageConfig.homePageCategoryList.push(homePageCategory);

                    angular.forEach(kt.homepage.exhibitions, function (exhibition) {
                        if (exhibition.categoryId == category.id && exhibition.isChoice) {
                            var categoryExhibition = {};
                            categoryExhibition.categoryId = exhibition.categoryId;
                            categoryExhibition.exhibitionId = exhibition.id;
                            categoryExhibition.isChoice = 1;
                            kt.homepageConfig.categoryExhibitionList.push(categoryExhibition);
                        }
                    })
                }
            })

            var carouselNum = 0;
            var hotNum = 0;
            var overHot = false;
            var overCarousel = false;
            //首页设置
            angular.forEach(kt.homepage.exhibitionList, function (exhibition) {
                if (exhibition.hot || exhibition.hasCarousel) {
                    var categoryExhibition = {};
                    categoryExhibition.categoryId = exhibition.categoryId;
                    categoryExhibition.exhibitionId = exhibition.id;
                    if (exhibition.hot) {
                        categoryExhibition.isHot = 1;
                        hotNum++;
                        if(hotNum > kt.homepage.extension.hotSum){
                            overHot = true;
                        }
                    }
                    if (exhibition.hasCarousel) {
                        categoryExhibition.isCarousel = 1;
                        carouselNum++;
                        if(carouselNum > kt.homepage.extension.carouselSum){
                            overCarousel = true;
                        }
                    }
                    kt.homepageConfig.categoryExhibitionList.push(categoryExhibition);
                }

            })

            if(overHot){
                toastr.warning("已超出推荐数");
                return;
            }

            if(overCarousel){
                toastr.warning("已超出轮播数");
                return;
            }
            kt.homepage.extension.hotNum = hotNum;
            kt.homepage.extension.carouselNum = carouselNum;

            //测试
            // var categoryExhibition = {};
            // categoryExhibition.categoryId = "11111212121";
            // categoryExhibition.exhibitionId = "11111111313";
            // categoryExhibition.isChoice = 1;
            // kt.homepageConfig.categoryExhibitionList.push(categoryExhibition);

            // kt.homepageConfig.homePageCategoryList =  JSON.stringify(kt.homepageConfig.homePageCategoryList);
            // kt.homepageConfig.categoryExhibitionList =  JSON.stringify(kt.homepageConfig.categoryExhibitionList);
            kt.homepageConfig.title = kt.homepage.title;
            kt.homepageConfig.extension = JSON.stringify(kt.homepage.extension);
            if(kt.homepage.id) {
                kt.homepageConfig.id = kt.homepage.id;
            }
            HomepageService.save(kt.homepageConfig, function (data) {
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

        $scope.getExhibitionByCategoryIds = function () {
            var categoryIds = [];
            angular.forEach(kt.homepage.categoryList, function (category) {
                    if (category.checked) {
                        categoryIds.push(category.id)
                    }
                }
            )
            if (categoryIds.length > 0) {
                HomepageService.getSomeExhibitions({
                    homepageId: $stateParams.id,
                    categoryIds: categoryIds
                }, function (data) {
                    kt.homepage.exhibitions = data.result;

                    angular.forEach(kt.homepage.exhibitions, function (exhibition) {
                        if (exhibition.isChoice == 1) {
                            exhibition.isChoice = true;
                        }
                    })
                })
            }

        }

    }
})();
