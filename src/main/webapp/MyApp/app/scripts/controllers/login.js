'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('LoginCtrl', function($scope, $location) {

    $scope.submit = function() {

      if ($scope.loginForm.$valid) {
		//alert('our form is amazing');
		$location.path('/dashboard');
	  }
      // $location.path('/dashboard');

      return false;
    }

  });
