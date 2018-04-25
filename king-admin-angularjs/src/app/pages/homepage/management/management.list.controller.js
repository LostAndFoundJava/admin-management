(function () {
    'use strict';

    angular.module('KingAdmin.pages.homepage.management')
        .controller('HomepageListCtrl', HomepageListCtrl);

    /** @ngInject */
    function HomepageListCtrl($scope, HomepageService) {
        var kt = this;
        kt.onlyOne = false;
        kt.homepagelist = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            HomepageService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    if(tableState.pagination.totalItemCount > 0){
                        kt.onlyOne = true;
                    }
                    kt.tableState = tableState;
                    kt.homepagelist = [];
                    angular.forEach(data.result.records,function (record) {
                        kt.homepage = record;
                        if(record.extension) {
                            var extension = JSON.parse(record.extension);
                            kt.homepage.extension = extension;
                        }
                        kt.homepagelist.push(kt.homepage);
                    })

                });

        };
        //设置当前主页
        kt.del = function (id) {
            HomepageService.del({id: id},
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
