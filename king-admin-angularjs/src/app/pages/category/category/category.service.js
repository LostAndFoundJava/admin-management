(function () {
    'use strict';

    angular.module('KingAdmin.pages.category.category')
        .factory('CategoryService', CategoryService);

    /** @ngInject */
    function CategoryService($resource ,toastr,CommonService) {

        var rest = $resource('mgr/category/management/:id', {}, {
            'create': {method: 'POST'},
            'update': {method: 'PUT'},
        });
        function getSmartData(param,callback) {
            $resource('mgr/category/management/getSmartData', {}, {
                'query': {method: 'POST'}
            }).query(param,
                function (data) {
                    console.log(data);
                    callback(data)
                }, function (error) {
                    toastr.error(error, "提示", {"progressBar": true,});
                });
        }
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
                            if(data.code == 0){
                                toastr.success("保存成功","提示",{"progressBar": true,});
                                callback(data);
                            }else{
                                toastr.warn(data.msg,"提示",{"progressBar": true,});
                            }
                        }, function (error) {
                            toastr.error(error,"提示",{"progressBar": true,});
                        })
                }else{
                    rest.create(param,
                        function (data) {
                            if(data.code == 0){
                                toastr.success("保存成功","提示",{"progressBar": true,});
                                callback(data);
                            }else{
                                toastr.warning(data.msg,"提示",{"progressBar": true,});
                            }
                        }, function (error) {
                            toastr.error(error,"提示",{"progressBar": true,});
                        })
                }
            });
        }
        function getInfo(param,callback) {
            rest.get(param,
                function (data) {
                    console.log(data);
                    callback(data);
                }, function (error) {
                    toastr.error(error,"提示",{"progressBar": true,});
                })
        }
        function getList(param,callback) {
            $resource('mgr/category/management/getList').get(param,
                function (data) {
                    console.log(data);
                    callback(data);
                }, function (error) {
                    toastr.error(error,"提示",{"progressBar": true,});
                })
        }
        return {
            getSmartData:getSmartData,
            del:del,
            save:save,
            getInfo:getInfo,
            getList:getList
        };

    }

})();
