(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.management')
        .controller('ExhibitionCtrl', ExhibitionCtrl);

    /** @ngInject */
    function ExhibitionCtrl($scope, $stateParams, $state, ExhibitionService) {

        var kt = this;
        kt.exhibition = {};
        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        if ($stateParams.id) {
            ExhibitionService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.exhibition = data;
                })
        } else {
            kt.isAdd = true;
        }

        kt.save = function () {
            kt.exhibition.exhibitionDetail = {};
            kt.exhibition.startTime = formatDate(kt.exhibition.startTime);
            kt.exhibition.endTime = formatDate(kt.exhibition.endTime);
            kt.exhibition.hot = kt.exhibition.hot ? 1 : 0;
            kt.exhibition.hasCarousel = kt.exhibition.hasCarousel ? 1 : 0;
            kt.exhibition.exhibitionDetail.description = kt.exhibition.description;
            kt.exhibition.exhibitionDetail.files = [];
            ExhibitionService.save(kt.exhibition, function (data) {
                $state.go('exhibition.management');
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

        $scope.isDisabledDate = function (currentDate, mode) {
            // $scope.today();
            return mode === 'day' && (currentDate.getDay() === 0 || currentDate.getDay() === 6);
        };

        $scope.dateOptions = {
            minDate: new Date()
        };


        $scope.today = function () {
            kt.exhibition.startTime = new Date();
            kt.exhibition.endTime = kt.exhibition.startTime;
        };
        $scope.today();


        kt.exhibition.title = "title";
        kt.exhibition.city = 1;
        kt.exhibition.category = "category";
        kt.exhibition.tag = "tag";
        kt.exhibition.location = "location";
        kt.exhibition.country = 2;
        kt.exhibition.categoryId = "categoryid";
    }

})();
