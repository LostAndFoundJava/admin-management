(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.management')
        .controller('ExhibitionListCtrl', ExhibitionListCtrl);

    /** @ngInject */
    function ExhibitionListCtrl($scope, ExhibitionService) {

        var kt = this;
        kt.exhibitionlist = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            ExhibitionService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.exhibitionlist = data.result.records;
                });

        };
        //删除
        kt.del = function (id) {
            ExhibitionService.del({id: id},
                function (data) {
                    kt.LoadPage();
                })
        };
        kt.checkboxes = {
            checked: false,
            items: {}
        };
        $scope.$watch('kt.checkboxes.checked', function (value) {
            angular.forEach(kt.exhibitionlist, function (item) {
                kt.checkboxes.items[item.id] = value;
            });
        });
    }

})();
