(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.advice')
        .controller('AdviceListCtrl', AdviceListCtrl);

    /** @ngInject */
    function AdviceListCtrl($scope, AdviceService, toastr) {
        var kt = this;
        kt.adviceList = [];
        kt.advice = [];
        kt.LoadPage = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            AdviceService.getSmartData(tableState,
                function (data) {
                    tableState.pagination.numberOfPages = data.result.pages;
                    tableState.pagination.totalItemCount = data.result.total;
                    kt.tableState = tableState;
                    kt.adviceList = data.result.records;
                    angular.forEach(kt.adviceList, function (advice) {
                        if (advice.status == 0) {
                            advice.stats = "未解决";
                            advice.isView = true;
                        } else {
                            advice.stats = "已解决";
                            advice.isView = false;
                        }

                    })
                });

        };
        //删除
        kt.del = function (id) {
            AdviceService.del({id: id},
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

        $scope.exportMaterialData = function (tableState) {
            tableState = tableState || kt.tableState;
            tableState.pagination.number = tableState.pagination.number || 5;
            AdviceService.exportData(tableState,
                function (data) {
                    var aForExcel = $("<a><span class='forExcel'>下载excel</span></a>").attr("href", data.result);
                    $("body").append(aForExcel);
                    $(".forExcel").click();
                    aForExcel.remove();
                });
        }

        kt.delete = function (id) {
            AdviceService.deal({id: id}, function (data) {
                toastr.success("修改成功", "提示", {"progressBar": true,});
                kt.LoadPage();
            })
        }


        kt.status = [
            {
                code: 0, text: "未解决"
            },
            {
                code: 1, text: "已解决"
            }]

        $scope.statusChange = function () {
            kt.LoadPage();
        }

    }

})();
