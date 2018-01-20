'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('LoginCtrl', function($scope, $location, LoginService, $window) {

    $scope.user = {
      email : '',
      password : ''
    };

    $scope.submit = function() {

      if ($scope.loginForm.$valid) {
        //alert('our form is amazing');
        $scope.user.userName = $scope.user.email;
        //console.log($scope.user);
        // $scope.errorMessage = "xyz";//response.errorMessage;
        //         $scope.errorMessagebool = true;
        //         $window.scrollTo(0, 0);
        LoginService.login($scope.user)
          .then(function success(response) {
              console.log(response);
              if(response.data.status === "ok"){
                $location.path('/dashboard');
              } else {
                $scope.errorMessage = response.data.errorMessage;
                $scope.errorMessagebool = true;
                $window.scrollTo(0, 0);
              }              
          }, function error(response) {
              $scope.errorMessage = response.data.errorMessage;
              $scope.errorMessagebool = true;
              $window.scrollTo(0, 0);          
          });		    
	    }
      return false;
    }

  });
