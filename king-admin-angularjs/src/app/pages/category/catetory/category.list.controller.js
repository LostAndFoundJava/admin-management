(function () {
    'use strict';

    angular.module('KingAdmin.pages.category.category')
        .controller('CategoryListCtrl', CategoryListCtrl);

    /** @ngInject */
    function CategoryListCtrl($scope, CategoryService) {

        var kt = this;
        kt.categorylist = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            CategoryService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.categorylist = data.result.records;
                });

        };
        //删除
        kt.del = function (id) {
            CategoryService.del({id: id},
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
