(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.excelupload')
        .factory('ExcelUploadService', ExcelUploadService);

    /** @ngInject */
    function ExcelUploadService($resource ,toastr,CommonService) {

        var rest = $resource('sys/dict/:id', {}, {
            'create': {method: 'POST'},
            'update': {method: 'PUT'},
        });
        // var myAppModule = angular.module('quillTest', ['ngQuill']);
        // function getSmartData(param,callback) {
        //     $resource('sys/dict/getSmartData', {}, {
        //         'query': {method: 'POST'}
        //     }).query(param,
        //         function (data) {
        //             console.log(data);
        //             callback(data)
        //         }, function (error) {
        //             toastr.error(error, "提示", {"progressBar": true,});
        //         });
        // }
        function del(param,callback) {
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
        function save(param,callback) {
            CommonService.info('确定保存?',function () {
                if(angular.isDefined(param.id)&&param.id!=null){
                    rest.update(param,
                        function (data) {
                            console.log(data);
                            toastr.success("保存成功","提示",{"progressBar": true,});
                            callback(data);
                        }, function (error) {
                            toastr.error(error,"提示",{"progressBar": true,});
                        })
                }else{
                    rest.create(param,
                        function (data) {
                            console.log(data);
                            toastr.success("保存成功","提示",{"progressBar": true,});
                            callback(data);
                        }, function (error) {
                            toastr.error(error,"提示",{"progressBar": true,});
                        })
                }
            });
            //
            // function getList(code,callback) {
            //     $resource('sys/dict/getlist/:code').get({code:code},
            //         function (data) {
            //             console.log(data);
            //             callback(data);
            //         }, function (error) {
            //             toastr.error(error,"提示",{"progressBar": true,});
            //         })
            // }
        }
        return {
            // getSmartData:getSmartData,
            del:del,
            save:save,
            // getList:getList,
        };

    }

})();

