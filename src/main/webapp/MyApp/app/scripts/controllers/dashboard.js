'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('DashboardCtrl', function($scope,$location) {
    $scope.data = {};
    $scope.addClient = function(){
      $location.path('/addClient');
    }
  });
