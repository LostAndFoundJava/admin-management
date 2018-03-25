(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition')
        .factory('NewsEditService', NewsEditService);

    /** @ngInject */
    function NewsEditService($resource ,toastr,CommonService) {

        // var rest = $resource('api/mgr/exhibition/management/:id', {}, {
        var rest = $resource('sys/upload', {}, {

            'create': {method: 'POST'},
            'update': {method: 'PUT'},
        });


        function save(param,callback) {
            CommonService.info('确定保存?',function () {
                if(angular.isDefined(param.id)&&param.id!=null){
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

        return{
            save : save,
        }
    }
})();

