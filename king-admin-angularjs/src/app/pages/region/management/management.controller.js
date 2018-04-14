(function () {
    'use strict';

    angular.module('KingAdmin.pages.region.management')
        .controller('RegionCtrl', RegionCtrl);


    /** @ngInject */
    function RegionCtrl($scope, $timeout, toastr, $stateParams, $state, RegionService) {

        var kt = this;

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
