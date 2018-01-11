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
          .then(function success(response) {
              console.log(response);
              $location.path('/dashboard');
          }, function error(response) {
              $scope.status = error.message;          
          });		    
	    }
      return false;
    }

  });
