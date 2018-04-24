(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.flowsrc')
        .controller('FlowSrcCtrl', FlowSrcCtrl);
    /** @ngInject */
    function FlowSrcCtrl($scope, $timeout, $stateParams, FlowSrcService,$state) {
        var kt = this;
        kt.flowsrc = {};

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        //由id判断是新增还是修改/查看（回显数据）
        if ($stateParams.id) {
            FlowSrcService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.flowsrc = data;
                })
        } else {
            kt.isAdd = true;
        }

        //保存新增／修改的数据
        kt.save = function () {
            FlowSrcService.save(kt.flowsrc, function (data) {
                $state.go('excelupload.flowsrc.management');
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
