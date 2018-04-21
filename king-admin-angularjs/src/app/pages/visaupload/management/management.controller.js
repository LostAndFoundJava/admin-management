(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload.management')
        .controller('VisaUploadCtrl', VisaUploadCtrl);

    angular.module('KingAdmin.pages.visaupload.management').config(['ngQuillConfigProvider', function (ngQuillConfigProvider) {
        ngQuillConfigProvider.set();
    }]);

    /** @ngInject */
    function VisaUploadCtrl($scope, $timeout, toastr, $stateParams, $state, RegionService,VisaUploadService) {

        var kt = this;

        kt.visa = {};

        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }



        //由id判断是新增还是修改/查看（回显数据）
        if ($stateParams.id) {
            VisaUploadService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.aboutus = data;

                    if (kt.aboutus.detail) {
                        kt.customDetail = JSON.parse(kt.aboutus.detail);
                    }

                })
        } else {
            kt.isAdd = true;
        }

        //保存新增／修改的数据
        kt.save = function () {
            kt.aboutus.detail = JSON.stringify(kt.customDetail);
            VisaUploadService.save(kt.link, function (data) {
                $state.go('aboutus.management');
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
