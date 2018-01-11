'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('LoginCtrl', function($scope, $location, LoginService) {

    $scope.user = {
      email : '',
      password : ''
    };

    $scope.submit = function() {

      if ($scope.loginForm.$valid) {
        //alert('our form is amazing');
        $scope.user.userName = $scope.user.email;
        //console.log($scope.user);
        LoginService.login($scope.user)
            .success(function (studs) {
               console.log(studs);
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
