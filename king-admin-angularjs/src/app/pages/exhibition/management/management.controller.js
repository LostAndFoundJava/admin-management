
(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.management')
        .controller('ExhibitionCtrl', ExhibitionCtrl);

    /** @ngInject */
    function ExhibitionCtrl($scope,$stateParams,$state,ExhibitionService) {

        var kt = this;
        kt.exhibition = {};
        if($stateParams.isView){
            kt.isView = true;
        }else{
            kt.isView = false;
        }

        if($stateParams.id){
            ExhibitionService.getInfo({id:$stateParams.id},
                function (data) {
                    kt.exhibition = data;
                })
        }else{
            kt.isAdd = true;
        }

        kt.save = function () {

            var checked = kt.basicTree.jstree().get_checked(true);
            console.log(checked);
            kt.role.menuTree = [];
            angular.forEach(checked,function (c) {
                kt.role.menuTree.push(c.original);
            })
            ExhibitionService.save(kt.exhibition,function (data) {
                $state.go('mgr.exhibition.management');
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
            "checkbox" : {
                "keep_selected_style" : false
            },
            'plugins': ['types',"wholerow",'checkbox'],
            'version': 1
        };

        $scope.isDisabledDate = function(currentDate, mode) {
            return mode === 'day' && (currentDate.getDay() === 0 || currentDate.getDay() === 6);
        };

        $scope.options = {
            minDate: new Date(),
            showWeeks: true
        };


        $scope.today = function() {
            kt.exhibition.startTime = new Date();
            kt.exhibition.endTime = kt.exhibition.startTime;
        };
        $scope.today();
    }

})();
