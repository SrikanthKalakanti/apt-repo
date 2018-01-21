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
    $scope.clients = {};
    $scope.addClient = function(){
      $location.path('/addClient');
    };
    $scope.gridOptions = {
      enableSorting: true,
      enableFiltering: true,
      enablePagination: true,
      paginationPageSizes: [25, 50, 75],
      paginationPageSize: 10,
      columnDefs: [
          { name: 'clientId' },
          { name: 'clientName' },
          { name: 'clientStatus', enableSorting: false, enableFiltering: false },
          { name: 'address', enableSorting: false, enableFiltering: false },
          { name: 'phone', enableSorting: false, enableFiltering: false },
          { name: 'email', enableSorting: false, enableFiltering: false },
          { name: 'loa', enableSorting: false, enableFiltering: false },
          { name: 'termLoadDate', enableSorting: false, enableFiltering: false }
        ],
        onRegisterApi: function (gridApi) {
        //   gridApi.pagination.on.paginationChanged($scope, function (pageNumber, pageSize) {
        //     console.log('pager get data: pageNumber, pageSize', pageNumber, pageSize);
        //     getPager(pageNumber, pageSize); 
        // });
          $scope.grid1Api = gridApi;
        }
      };
      
    $scope.loadData = function() {
      $http.get('../scripts/mocks/data.json').then(function success(data){
        //var req = JSON.parse(data);
        console.log(data);
        $scope.clients = data.data;
        $scope.gridOptions.data = $scope.clients;
        //$scope.filtered = $scope.gridOptions.data;
      });
    };
    $scope.loadData();
  });
