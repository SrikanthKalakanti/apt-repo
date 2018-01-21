'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
  .controller('DashboardCtrl', function($scope, $location, DashboadService, LoginService) {
    $scope.data = {};
    $scope.clients = {};
    $scope.addClient = function(){
      $location.path('/addClient');
    };
    $scope.loginServiceData = LoginService.getLoginData();
    $scope.gridOptions = {
      enableSorting: true,
      enableFiltering: true,
      enablePagination: true,
      paginationPageSizes: [25, 50, 75],
      paginationPageSize: 10,
      columnDefs: [
          { name: 'clientId' },
          { name: 'name' },
          { name: 'status', enableSorting: false, enableFiltering: false },
          { name: 'address', enableSorting: false, enableFiltering: false },
          { name: 'mobile', enableSorting: false, enableFiltering: false },
          { name: 'email', enableSorting: false, enableFiltering: false },
          { name: 'lineofactivity', enableSorting: false, enableFiltering: false },
          { name: 'dateoffirstditributionoftermloan', enableSorting: false, enableFiltering: false }
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
      // $http.get('../scripts/mocks/data.json').then(function success(data){
      //   //var req = JSON.parse(data);
      //   console.log(data);
      //   $scope.clients = data.data;
      //   $scope.gridOptions.data = $scope.clients;
      //   //$scope.filtered = $scope.gridOptions.data;
      $scope.loginServiceData = localStorage.getItem("loginData");
      DashboadService.getAllClients($scope.loginServiceData.customerId)
            .then(function success(response) {
                console.log(response);
                if(response.data.status === "ok"){
                  $scope.clients = response.data.result;
                  $scope.gridOptions.data = $scope.clients;
                } else {
                  $scope.clients = {};
                  $scope.gridOptions.data = $scope.clients;
                }
            }, function error(response) {
                // $scope.errorMessage = response.data.errorMessage;
                // $scope.errorMessagebool = true;
                // $window.scrollTo(0, 0);        
            });
      // });
    };
    $scope.loadData();
  });
