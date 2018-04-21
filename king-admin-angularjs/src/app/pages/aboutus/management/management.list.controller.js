(function () {
    'use strict';

    angular.module('KingAdmin.pages.aboutus.management')
        .controller('AboutUsListCtrl', AboutUsListCtrl);

    /** @ngInject */
    function AboutUsListCtrl($scope, AboutUsService) {
        var kt = this;
        kt.onlyOne = false;
        kt.aboutuslist = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            AboutUsService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    if(tableState.pagination.totalItemCount > 0){
                        kt.onlyOne = true;
                    }
                    kt.tableState = tableState;
                    kt.aboutuslist = data.result.records;
                });

        };
        //设置当前主页
        kt.del = function (id) {
            AboutUsService.del({id: id},
                function (data) {
                    kt.LoadPage();
                })
        };
        kt.checkboxes = {
            checked: false,
            items: {}
        };
        $scope.$watch('kt.checkboxes.checked', function (value) {
            angular.forEach(kt.homepagelist, function (item) {
                kt.checkboxes.items[item.id] = value;
            });
        });
    }

})();
