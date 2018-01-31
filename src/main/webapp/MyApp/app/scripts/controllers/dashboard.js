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
    $scope.gridOptions = {
      enableSorting: true,
      enableFiltering: true,
      enablePagination: true,
      paginationPageSizes: [25, 50, 75],
      paginationPageSize: 10,
      columnDefs: [
          { field: 'clientId', name: 'Id', enableHiding: false },
          { field: 'name', name: 'Name', enableHiding: false },
          { name: 'status', enableSorting: false, enableFiltering: false, enableHiding: false },
          // { name: 'address', enableSorting: false, enableFiltering: false },
          { name: 'mobile', enableSorting: false, enableFiltering: false, enableHiding: false },
          { name: 'email', enableSorting: false, enableFiltering: false, enableHiding: false },
          { field: 'lineofactivity', name: 'Line of Activity', enableSorting: false, enableFiltering: false, enableHiding: false },
          { field: 'dateoffirstditributionoftermloan', name: 'Date of Loan Ditribution', enableSorting: false, enableFiltering: false, enableHiding: false, cellFilter: 'date:\'dd-MM-yyyy\'' },
          { name: 'Add/Update Details', cellTemplate:'<div class="ui-grid-cell-contents"><a href="#/clientDetails" ng-click="grid.appScope.updateDetails(row.entity)">Click me</a></div>', enableSorting: false, enableFiltering: false, enableHiding: false }
        ],
        onRegisterApi: function (gridApi) {
        //   gridApi.pagination.on.paginationChanged($scope, function (pageNumber, pageSize) {
        //     console.log('pager get data: pageNumber, pageSize', pageNumber, pageSize);
        //     getPager(pageNumber, pageSize); 
        // });
          $scope.grid1Api = gridApi;
        }
      };
    $scope.updateDetails = function(row){
      // console.log(JSON.stringify(row));
      localStorage.setItem("clientData",JSON.stringify(row));
      $location.path('/clientDetails');
      // $location.path("/clientDetails");
    }; 
    $scope.loadData = function() {
      // $http.get('../scripts/mocks/data.json').then(function success(data){
      //   //var req = JSON.parse(data);
      //   console.log(data);
      //   $scope.clients = data.data;
      //   $scope.gridOptions.data = $scope.clients;
      //   //$scope.filtered = $scope.gridOptions.data;
      $scope.userData = JSON.parse(localStorage.getItem('userData'));
      DashboadService.getAllClients($scope.userData.customerId)
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
