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

          // this function will be called when the request is success
          console.log(studs);
                $location.path('/dashboard');
          }, function error(response) {
            $scope.status = error.message;
          // this function will be called when the request returned error status
          
          });
            // .success(function (studs) {
               
            // })
            // .error(function (error) {
            //     $scope.status = error.message;
            // });		    
	    }
      // $location.path('/dashboard');

      return false;
    }

  });
