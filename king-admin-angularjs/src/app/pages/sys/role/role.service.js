(function () {
    'use strict';

    angular.module('KingAdmin.pages.sys.role')
        .factory('RoleService', RoleService);

    /** @ngInject */
    function RoleService($resource ,toastr,CommonService,$window) {

        var rest = $resource('sys/role/:id', {}, {
            'create': {method: 'POST'},
            'update': {method: 'PUT'},
        });
        function getSmartData(param,callback) {
            $resource('sys/role/getSmartData', {}, {
                'query': {method: 'POST'}
            }).query(param,
                function (data) {
                    console.log(data);
                    if(data.code == 401)
                        $window.open('auth.html', '_self');
                    else
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
            $resource('sys/role/getlist').get(param,
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
