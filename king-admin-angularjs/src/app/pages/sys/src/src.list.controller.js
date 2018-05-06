(function () {
    'use strict';

    angular.module('KingAdmin.pages.sys.src')
        .controller('SrcListCtrl', SrcListCtrl);

    /** @ngInject */
    function SrcListCtrl($scope, $filter, SrcService) {

        var kt = this;
        kt.srclist = [];

        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            SrcService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.srclist = data.result.records;
                });

        };



        //删除
        kt.del = function (id) {
            SrcService.del({id: id},
                function (data) {
                    kt.LoadPage();
                })
        };
        kt.checkboxes = {
            checked: false,
            items: {}
        };
        $scope.$watch('kt.checkboxes.checked', function (value) {
            angular.forEach(kt.srclist, function (item) {
                kt.checkboxes.items[item.id] = value;
            });
        });
    }

})();
