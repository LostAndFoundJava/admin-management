(function () {
    'use strict';

    angular.module('KingAdmin.pages.visaupload.visaupload')
        .factory('VisaUploadService', VisaUploadService);

    //ng-js
    /** @ngInject */
    function VisaUploadService($scope, Upload) {
        function del(param, callback) {
            CommonService.danger('确定删除?', function () {
                rest.delete(param,
                    function (data) {
                        if (data.code == 0) {
                            toastr.success("删除成功！", "提示", {"progressBar": true,});
                        } else {
                            toastr.warning("删除失败！", "提示", {"progressBar": true,});
                        }
                        callback(data);
                    }, function (error) {
                        toastr.error(error, "提示", {"progressBar": true,});
                    })
            })
        }
        function save(param, callback) {
            CommonService.info('确定保存?', function () {
                if (angular.isDefined(param.id) && param.id != null) {
                    rest.update(param,
                        function (data) {
                            console.log(data);
                            toastr.success("保存成功", "提示", {"progressBar": true,});
                            callback(data);
                        }, function (error) {
                            toastr.error(error, "提示", {"progressBar": true,});
                        })
                } else {
                    rest.create(param,
                        function (data) {
                            console.log(data);
                            toastr.success("保存成功", "提示", {"progressBar": true,});
                            callback(data);
                        }, function (error) {
                            toastr.error(error, "提示", {"progressBar": true,});
                        })
                }
            });
        }
        return {
            // getSmartData:getSmartData,
            del: del,
            save: save,
            // getList:getList,
        };

    }

})();

