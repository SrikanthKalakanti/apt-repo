'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of myAppApp
 */
angular.module('myAppApp')
  .controller('RegisterCtrl', function($scope, $location, RegisterService, $timeout, $window, safeApply) {
    $//scope.$state = $state;
    $scope.registerUser = {
    	namePrefix : 'Mr',
    	status : 'Individual',
    	state : "-1",
    	profession : "CA",
    	trial : "1",
    	states : {
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
}
    };
    $scope.menuItems = [];
    // angular.forEach($state.get(), function (item) {
    //     if (item.data && item.data.visible) {
    //         $scope.menuItems.push({name: item.name, text: item.data.text});
    //     }
    // });
    $scope.submit = function(){
    	var address = [];
    	var tempAddress = {};
    	tempAddress.doorNumber = $scope.registerUser.doorNumber;
    	tempAddress.roadNumber = $scope.registerUser.roadNumber;
    	tempAddress.locality = $scope.registerUser.locality;
    	tempAddress.landmark = $scope.registerUser.landmark;
    	tempAddress.town = $scope.registerUser.town;
    	tempAddress.pincode = $scope.registerUser.pincode;
    	tempAddress.state = $scope.registerUser.state;
    	tempAddress.landPhone = $scope.registerUser.landPhone;
    	tempAddress.mobile = $scope.registerUser.mobile;
    	address.push(tempAddress);
    	$scope.registerUser.address = address;
      console.log($scope.registerUser);
      if ($scope.registerForm.$valid) {
        //alert('our form is amazing');
            // var a = {
            //   "status" : "ok",
            //   "errorCode" : "APT1000",
            //   "errorMessage" : "Customer Created Successfully",
            //   "exceptionType" : "null",
            //   "result" : "null",
            //   "customerId" : "null"
            // };
            // $scope.successMessage = a.errorMessage;
            // $scope.successMessagebool = true;
            
            RegisterService.register($scope.registerUser)
            .then(function success(response) {
                console.log(response);
                if(response.data.status === "ok") {
                  $scope.successMessage = response.data.errorMessage;
                  $scope.successMessagebool = true;
                  $window.scrollTo(0, 0);
                  $scope.safeApply($timeout(function () {
                      $scope.successMessagebool = false;
                      // $scope.$apply();
                      $location.path('/login');
                  }, 3000));
                } else {
                  $scope.errorMessage = response.data.errorMessage;
                  $scope.errorMessagebool = true;
                  $window.scrollTo(0, 0);
                }                
            }, function error(response) {
                $scope.successMessage = response.data.errorMessage;
                $scope.successMessagebool = true;
                $window.scrollTo(0, 0);        
            });		    
	    }
    }
    $scope.cancel = function(){
      $location.path('/login');
    }
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
