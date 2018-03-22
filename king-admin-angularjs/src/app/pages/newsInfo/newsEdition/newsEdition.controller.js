(function () {
    'use strict';

    angular.module('KingAdmin.pages.newsInfo.newsEdition')
        .controller('NewsEditCtrl', NewsEditCtrl)


    /** @ngInject */
    function NewsEditCtrl($scope,$filter, toastr, NewsEditService,$timeout) {
        var kt = this;
        init();
        function init() {
            $scope.title = 'Quill works'
            $scope.model = ''
            $scope.readOnly = false
            $scope.test = ''
            $scope.customOptions = [{
                import: 'attributors/style/size',
                whitelist: ['14', '16', '18', 'small', 'large', 'huge']
            }]
            $scope.customModules = {
                toolbar: [
                    [{'size': [false, '14', '16', '18']}]
                ]
            }

            $timeout(function () {
                $scope.title += ' awsome!!!'
            }, 2000)

            $scope.editorCreated = function (editor) {
                console.log(editor)
            }
            $scope.contentChanged = function (editor, html, text, delta, oldDelta, source) {
                console.log('editor: ', editor, 'html: ', html, 'text:', text, 'delta: ', delta, 'oldDelta:', oldDelta, 'source:', source)
            }
            $scope.selectionChanged = function (editor, range, oldRange, source) {
                console.log('editor: ', editor, 'range: ', range, 'oldRange:', oldRange, 'source:', source)
            }
        }
    }
})();
