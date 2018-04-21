(function () {
    'use strict';

    angular.module('KingAdmin.pages.link.management')
        .controller('LinkListCtrl', LinkListCtrl);

    /** @ngInject */
    function LinkListCtrl($scope, LinkService) {
        var kt = this;
        kt.onlyOne = false;
        kt.linklist = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            LinkService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.linklist = data.result.records;


                });

        };
        //设置当前主页
        kt.del = function (id) {
            LinkService.del({id: id},
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
