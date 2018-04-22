(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition')
        .controller('newsEditionListCtrl', newsEditionListCtrl);

    /** @ngInject */
    function newsEditionListCtrl($scope, newsEditionService) {
        var kt = this;
        kt.newsInfolist = [];

        //显示所有的行业
        newsEditionService.getSelectCategoryInfo(function (data) {
            kt.newsEdition = []
            kt.newsEdition = data.result
        });


        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            newsEditionService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    angular.forEach(kt.newsEdition, function (category) {
                        angular.forEach(data.result.records, function (record) {
                            if(category.id == record.categoryId)
                                record.categoryId = category.name
                        })
                    })
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
