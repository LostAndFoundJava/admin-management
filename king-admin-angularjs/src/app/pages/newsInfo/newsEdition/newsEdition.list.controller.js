(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition')
        .controller('newsEditonListCtrl', newsEditonListCtrl);

    /** @ngInject */
    function newsEditonListCtrl($scope, newsEditionService) {
        var kt = this;
        kt.newsInfolist = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            newsEditionService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.newsInfolist = data.result.records;
                });

        };
        //删除
        kt.del = function (id) {
            newsEditionService.del({id: id},
                function (data) {
                    kt.LoadPage();
                })
        };
        kt.checkboxes = {
            checked: false,
            items: {}
        };
        $scope.$watch('kt.checkboxes.checked', function (value) {
            angular.forEach(kt.newsInfolist, function (item) {
                kt.checkboxes.items[item.id] = value;
            });
        });
    }

})();
