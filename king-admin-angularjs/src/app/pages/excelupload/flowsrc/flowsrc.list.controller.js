(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.flowsrc')
        .controller('FlowSrcListCtrl', FlowSrcListCtrl);

    /** @ngInject */
    function FlowSrcListCtrl($http, $scope, $window, $timeout, FlowSrcService) {
        var kt = this;
        kt.flowsrcList = [];
        var selectCondition = this;
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

        $scope.exportToExcel = function (tableId) { // ex: '#my-table'
            $scope.exportHref = FlowSrcService.excelExport($window).tableToExcel(tableId, 'sheet name');
            $timeout(function () {
                location.href = $scope.exportHref;
            }, 100); // trigger download

        };

        $scope.exportToTable = function () {
            $http({
                url: url + "export",
                responseType: 'arraybuffer'
            }).success(function (res) {
                var blob = new Blob([res], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"});
                //var blob = new Blob([res], {type: "application/octet-stream"});  这种类型的转换同样可行
                saveAs(blob, "考题信息.xlsx");
            });
        };
    }

})();
