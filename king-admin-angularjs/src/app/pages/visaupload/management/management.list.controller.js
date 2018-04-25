(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload.management')
        .controller('VisaUploadListCtrl', VisaUploadListCtrl);

    /** @ngInject */
    function VisaUploadListCtrl($scope, VisaUploadService) {
        var kt = this;
        kt.visalist = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            VisaUploadService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.visalist = data.result.records;
                });

        };
        //设置当前主页
        kt.del = function (id) {
            VisaUploadService.del({id: id},
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
