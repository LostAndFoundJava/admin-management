(function () {
    'use strict';

    angular.module('KingAdmin.pages.excelupload.excelupload')
        .factory('ExcelUploadService', ExcelUploadService);
    // angular.module('KingAdmin.pages.excelupload.excelupload', ['ngFileUpload'])
    //     .factory('ExcelUploadService', ExcelUploadService);

    //ng-js
    /** @ngInject */
    function ExcelUploadService($scope, Upload) {
        // $scope.uploadImg = '';
        // //提交`
        //
        // $scope.submit = function () {
        //     $scope.upload($scope.file);
        // };
        // $scope.upload = function (file) {
        //     $scope.fileInfo = file;
        //     Upload.upload({
        //         //服务端接收
        //         url: 'Ashx/UploadFile.ashx',
        //         //上传的同时带的参数
        //         data: {'username': $scope.username},
        //         file: file
        //     }).progress(function (evt) {
        //         //进度条
        //         var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
        //         console.log('progess:' + progressPercentage + '%' + evt.config.file.name);
        //     }).success(function (data, status, headers, config) {
        //         //上传成功
        //         console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
        //         $scope.uploadImg = data;
        //     }).error(function (data, status, headers, config) {
        //         //上传失败
        //         console.log('error status: ' + status);
        //     });
        // };


        // /** @ngInject */
        // function ExcelUploadService($resource ,toastr,CommonService) {
        //
        //     var rest = $resource('sys/dict/:id', {}, {
        //         'create': {method: 'POST'},
        //         'update': {method: 'PUT'},
        //     });
        //
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

