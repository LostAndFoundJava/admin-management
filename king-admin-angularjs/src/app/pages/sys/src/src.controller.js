
(function () {
    'use strict';

    angular.module('KingAdmin.pages.sys.src')
        .controller('SrcCtrl', SrcCtrl);

    /** @ngInject */
    function SrcCtrl($scope,$stateParams,$state,SrcService) {

        var kt = this;
        kt.src = {};
        kt.statuses = [];

        if($stateParams.isView){
            kt.isView = true;
        }else{
            kt.isView = false;
        }
        if($stateParams.id){
            SrcService.getInfo({id:$stateParams.id},
                function (data) {
                kt.src = data;
            })
        }else{
            kt.isAdd = true;
        }

        kt.save = function () {
            SrcService.save(kt.src,function (data) {
                $state.go('sys.src');
            });
        }

    }

})();
