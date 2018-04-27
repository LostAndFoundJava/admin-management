(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.flowsrc')
        .controller('FlowSrcListCtrl', FlowSrcListCtrl);

    /** @ngInject */
    function FlowSrcListCtrl($http, $scope, $window, $timeout, FlowSrcService) {
        var kt = this;
        kt.flowsrcList = [];
        kt.flowsrc = {};
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            FlowSrcService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.flowsrcList = data.result.records;
                });

        };
        //删除
        kt.del = function (id) {
            FlowSrcService.del({id: id},
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

        $scope.isDisabledDate = function (currentDate, mode) {
            return mode === 'day' && (currentDate.getDay() === 0 || currentDate.getDay() === 6);
        };

        $scope.dateOptions = {
            minDate: null
        };



    }

})();
