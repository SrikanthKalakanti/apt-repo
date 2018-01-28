'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:ClientDetailsCtrl
 * @description
 * # ClientDetailsCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('ClientDetailsCtrl', function($scope, $location, CustomerService, $window, $timeout, LoginService) {
    $scope.tab = 1;
    $scope.client = {
        state : "-1"
      };
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
    $scope.clientData = JSON.parse(localStorage.getItem("clientData"));
    var date = new Date($scope.clientData.dateoffirstditributionoftermloan);
    $scope.clientData.dateoffirstditributionoftermloan = new Date(date.getFullYear(), date.getMonth(), date.getDate());
    $scope.client = $scope.clientData;
    $scope.setTab = function(newTab){
      $scope.tab = newTab;
    };

    $scope.isSet = function(tabNum){
      return $scope.tab === tabNum;
    };
  });
