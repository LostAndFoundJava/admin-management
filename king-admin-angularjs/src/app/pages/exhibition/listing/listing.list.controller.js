(function () {
    'use strict';

    angular.module('KingAdmin.pages.exhibition.listing')
        .controller('ListingListCtrl', ListingListCtrl);

    /** @ngInject */
    function ListingListCtrl($scope, ListingService) {



        var kt = this;
        kt.exhibitionlist = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            ListingService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.exhibitionlist = data.result.records;
                });

        };
        //删除
        kt.del = function (id) {
            ListingService.del({id: id},
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
