'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:AddClientCtrl
 * @description
 * # AddClientCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('AddClientCtrl', function($scope, $location, CustomerService, $window, $timeout, LoginService) {
      $scope.client = {
        namePrefix : 'Mr',
        status : 'Propreitorship',
        state : "-1",
        lineofactivity : 'Manufacturing'
      };
      $scope.loginServiceData.customerId = LoginService.getLoginData();
      $scope.states = {
        "AP":"Andhra Pradesh",
        "AR":"Arunachal Pradesh",
        "AS":"Assam",
        "BR":"Bihar",
        "CG":"Chhattisgarh",
        "Chandigarh":"Chandigarh",
        "DN":"Dadra and Nagar Haveli",
        "DD":"Daman and Diu",
        "DL":"Delhi",
        "GA":"Goa",
        "GJ":"Gujarat",
        "HR":"Haryana",
        "HP":"Himachal Pradesh",
        "JK":"Jammu and Kashmir",
        "JH":"Jharkhand",
        "KA":"Karnataka",
        "KL":"Kerala",
        "MP":"Madhya Pradesh",
        "MH":"Maharashtra",
        "MN":"Manipur",
        "ML":"Meghalaya",
        "MZ":"Mizoram",
        "NL":"Nagaland",
        "OR":"Orissa",
        "PB":"Punjab",
        "PY":"Pondicherry",
        "RJ":"Rajasthan",
        "SK":"Sikkim",
        "TN":"Tamil Nadu",
        "TR":"Tripura",
        "UP":"Uttar Pradesh",
        "UK":"Uttarakhand",
        "WB":"West Bengal"
     };
      $scope.cancel = function(){
        $location.path('/dashboard');
      }
      $scope.submit = function(){
        var address = [];
        // var tempAddress = {};
        // tempAddress.doorNumber = $scope.client.doorNumber;
        // tempAddress.roadNumber = $scope.client.roadNumber;
        // tempAddress.locality = $scope.client.locality;
        // tempAddress.landmark = $scope.client.landmark;
        // tempAddress.town = $scope.client.town;
        // tempAddress.pincode = $scope.client.pincode;
        // tempAddress.state = $scope.client.state;
        // tempAddress.landPhone = $scope.client.landPhone;
        // tempAddress.mobile = $scope.client.mobile;
        // address.push(tempAddress);
        // $scope.client.address = address;
        $scope.client.customerId = $scope.loginServiceData.customerId;
        console.log($scope.client);
        if ($scope.clientForm.$valid) {
          CustomerService.addCustomer($scope.client)
            .then(function success(response) {
                console.log(response);
                // var a = {
                //   "status" : "ok",
                //   "errorCode" : "APT1000",
                //   "errorMessage" : "Customer Created Successfully",
                //   "exceptionType" : "null",
                //   "result" : "null",
                //   "customerId" : "null"
                // };
                $scope.successMessage = response.data.errorMessage;
                $scope.successMessagebool = true;
                $window.scrollTo(0, 0);
                $scope.safeApply($timeout(function () {
                    $scope.successMessagebool = false;
                    // $scope.$apply();
                    $location.path('/dashboard');
                }, 3000));
            }, function error(response) {
                $scope.errorMessage = response.data.errorMessage;
                $scope.errorMessagebool = true;
                $window.scrollTo(0, 0);        
            });
        }
      };
      $scope.safeApply = function(fn) {
        var phase = this.$root.$$phase;
        if(phase == '$apply' || phase == '$digest') {
          if(fn && (typeof(fn) === 'function')) {
            fn();
          }
        } else {
          this.$apply(fn);
        }
      };
  });
