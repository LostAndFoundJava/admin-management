(function () {
    'use strict';

    angular.module('KingAdmin.pages.category.category')
        .controller('CategoryCtrl', CategoryCtrl)


    /** @ngInject */
    function CategoryCtrl($scope, $filter, toastr, CategoryService, $timeout,$stateParams, $state) {
        var kt = this;
        kt.category = {};
        if ($stateParams.isView) {
            kt.isView = true;
        } else {
            kt.isView = false;
        }

        if ($stateParams.id) {
            CategoryService.getInfo({id: $stateParams.id},
                function (data) {
                    kt.category = data;
                })
        } else {
            kt.isAdd = true;
        }

        kt.save = function () {
            CategoryService.save(kt.category, function (data) {
                $state.go('category.category');
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
