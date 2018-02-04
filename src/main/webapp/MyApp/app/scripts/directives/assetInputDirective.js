"use strict";
angular.module('myAppApp').directive('assetInput', function() {
    return {
        templateUrl: '../../../views/assets.html',
        replace: false,
        restrict: 'E',
        scope: true,
        compile: function() {

        },
        controller: function($scope, AssetService, $window, $timeout) {
            // $scope.assets = {};
            $scope.userData = JSON.parse(localStorage.getItem('userData'));
            $scope.assets = JSON.parse(localStorage.getItem('assetData'));
            $scope.gridOptions = {
                enableSorting: true,
                enableFiltering: true,
                enablePagination: true,
                paginationPageSizes: [25, 50, 75],
                paginationPageSize: 10,
                columnDefs: [
                    { field: 'assetId', name: 'Id', enableHiding: false, enableFiltering: false },
                    { field: 'name', name: 'Name', enableHiding: false, enableSorting: false, enableFiltering: false },
                    { name: 'value', enableSorting: false, enableFiltering: false, enableHiding: false },
                    // { name: 'address', enableSorting: false, enableFiltering: false },
                    { name: 'depreciationRate', enableSorting: false, enableFiltering: false, enableHiding: false },
                    { name: 'promoterMargin', enableSorting: false, enableFiltering: false, enableHiding: false },
                    { name: 'Add/Update Details', cellTemplate: '<div class="ui-grid-cell-contents"><a href="#/clientDetails" ng-click="grid.appScope.updateDetails(row.entity)">Click me</a></div>', enableSorting: false, enableFiltering: false, enableHiding: false }
                ],
                onRegisterApi: function(gridApi) {
                    //   gridApi.pagination.on.paginationChanged($scope, function (pageNumber, pageSize) {
                    //     console.log('pager get data: pageNumber, pageSize', pageNumber, pageSize);
                    //     getPager(pageNumber, pageSize); 
                    // });
                    $scope.grid1Api = gridApi;
                }
            };
            $scope.refresh = function() {
                $scope.refresh = true;
                $timeout(function() {
                    $scope.refresh = false;
                }, 0);
            };
            $scope.getAssetDetails = function() {
                console.log($scope.assets.clientId);
                AssetService.getAllAssets($scope.assets.clientId)
                    .then(function success(response) {
                        $scope.assets = response.data.result;
                        console.log($scope.assets);
                        //$scope.successMessage = response.data.errorMessage;
                        //$scope.successMessagebool = true;
                        $scope.gridOptions.data = $scope.assets;
                    }, function error(response) {
                        $scope.errorMessage = response.data.errorMessage;
                        $scope.errorMessagebool = true;
                        //$window.scrollTo(0, 0);
                    });
            };
            $scope.updateDetails = function(row) {
                // console.log(JSON.stringify(row));
                // localStorage.setItem("clientData",JSON.stringify(row));
                // $location.path('/clientDetails');
                console.log(row);
                $scope.showAssetForm = true;
                $scope.isUpdate = true;
                $scope.asset = row;
                // $scope.asset = 
                // $location.path("/clientDetails");
            };
            $scope.addAsset = function() {
                $scope.showAssetForm = true;
            };
            $scope.updateAsset = function() {
                console.log($scope.assets.clientId);
                AssetService.updateAsset($scope.asset)
                    .then(function success(response) {
                        //$scope.assets = response.data.result;
                        console.log(response);
                        $scope.successMessage = response.data.errorMessage;
                        $scope.successMessagebool = true;
                        $window.scrollTo(0, 0);
                        $scope.safeApply($timeout(function() {
                            $scope.successMessagebool = false;
                        }, 3000));
                        if ($scope.asset.aaa === "1") {
                            $scope.asset = {};
                        } else {
                            $scope.showAssetForm = false;
                            $scope.getAssetDetails();
                            $scope.asset = {};
                        }
                    }, function error(response) {
                        $scope.errorMessage = response.data.errorMessage;
                        $scope.errorMessagebool = true;
                        $window.scrollTo(0, 0);
                    });
            };
            $scope.cancel = function() {
                $scope.showAssetForm = false;
            };
            $scope.getAssetDetails();
            $scope.safeApply = function(fn) {
                var phase = this.$root.$$phase;
                if (phase == '$apply' || phase === '$digest') {
                    if (fn && (typeof(fn) === 'function')) {
                        fn();
                    }
                } else {
                    this.$apply(fn);
                }
            };
            $scope.saveAsset = function() {
                //$scope.userData = JSON.parse(localStorage.getItem('userData'));
                $scope.asset.clientId = $scope.assets.clientId;
                if ($scope.assetForm.$valid) {
                    AssetService.saveAsset({ "assetInput": [$scope.asset] })
                        .then(function success(response) {
                            $scope.successMessage = response.data.errorMessage;
                            $scope.successMessagebool = true;
                            $window.scrollTo(0, 0);
                            $scope.safeApply($timeout(function() {
                                $scope.successMessagebool = false;
                            }, 3000));
                            if ($scope.asset.aaa === "1") {
                                $scope.asset = {};
                            } else {
                                $scope.showAssetForm = false;
                                $scope.getAssetDetails();
                                $scope.asset = {};
                            }
                        }, function error(response) {
                            $scope.errorMessage = response.data.errorMessage;
                            $scope.errorMessagebool = true;
                            $window.scrollTo(0, 0);
                        });
                }
            };
        }
    };
});