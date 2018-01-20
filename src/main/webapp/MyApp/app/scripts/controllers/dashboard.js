'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('DashboardCtrl', function($scope, $location, $http) {
    $scope.data = {};
    $scope.addClient = function(){
      $location.path('/addClient');
    };
    $scope.gridOptions = {
      data: [],
                urlSync: true,
                pagination: {
                    itemsPerPage: '10'
                }
      };
      function getServerData(params, callback) {
        $http.get('../scripts/mocks/data.json').then(function success(data){
          var data = data.data,
          totalItems = data.data;
          callback(data, totalItems);
        });
      };
    $scope.loadData = function() {
      $http.get('../scripts/mocks/data.json').then(function success(data){
        //var req = JSON.parse(data);
        console.log(data);
        $scope.gridOptions.data = data.data;
        //$scope.filtered = $scope.gridOptions.data;
      });
    };
    $scope.loadData();
  });
