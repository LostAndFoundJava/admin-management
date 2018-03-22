
(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.listing')
        .controller('ListingCtrl', ListingCtrl);

    /** @ngInject */
    function ListingCtrl($scope,$stateParams,$state,ListingService) {

        var kt = this;
        kt.exhibition = {};
        if($stateParams.isView){
            kt.isView = true;
        }else{
            kt.isView = false;
        }

        if($stateParams.id){
            ListingService.getInfo({id:$stateParams.id},
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
            ListingService.save(kt.exhibition,function (data) {
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

    }

})();
