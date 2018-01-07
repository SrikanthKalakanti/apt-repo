'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('DashboardCtrl', function($scope) {
    $//scope.$state = $state;

    $scope.menuItems = [];
    // angular.forEach($state.get(), function (item) {
    //     if (item.data && item.data.visible) {
    //         $scope.menuItems.push({name: item.name, text: item.data.text});
    //     }
    // });
  });
