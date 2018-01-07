'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:AssetsCtrl
 * @description
 * # AssetsCtrl
 * Controller of myAppApp
 */
angular.module('myAppApp')
  .controller('AssetsCtrl', function($scope, $location) {
    $scope.asset = {
      status : '-1'
    };

    $scope.submit = function() {

      if ($scope.basicForm.$valid) {
		  //alert('our form is amazing');
		    $location.path('/dashboard');
	     }
      // $location.path('/dashboard');

      return false;
    }

  });
