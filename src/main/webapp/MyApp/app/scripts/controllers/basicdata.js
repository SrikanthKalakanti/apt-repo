'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:BasicDataCtrl
 * @description
 * # BasicDataCtrl
 * Controller of myAppApp
 */
angular.module('myAppApp')
  .controller('BasicDataCtrl', function($scope, $location) {
    $scope.basic = {
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
