'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('LoginCtrl', function($scope, $location, LoginDataOp) {

    $scope.user = {
      email : '',
      password : ''
    };

    $scope.submit = function() {

      if ($scope.loginForm.$valid) {
        //alert('our form is amazing');
        $scope.user.userName = $scope.user.email;
        //console.log($scope.user);
        LoginDataOp.login($scope.user)
            .success(function (studs) {
                $location.path('/dashboard');
            })
            .error(function (error) {
                $scope.status = error.message;
            });		    
	    }
      // $location.path('/dashboard');

      return false;
    }

  });
