'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:ClientDetailsCtrl
 * @description
 * # ClientDetailsCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('ClientDetailsCtrl', function($scope, $location, CustomerService, $window, $timeout, LoginService) {
    $scope.tab = 1;

    $scope.setTab = function(newTab){
      $scope.tab = newTab;
    };

    $scope.isSet = function(tabNum){
      return $scope.tab === tabNum;
    };
  });
