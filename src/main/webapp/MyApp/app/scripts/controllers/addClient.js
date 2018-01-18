'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:AddClientCtrl
 * @description
 * # AddClientCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('AddClientCtrl', function($scope) {
      $scope.client = {
        namePrefix : 'Mr',
        status : 'Individual',
        state : "-1",
        LOA : 'Manufacturing',
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
      }
  });
