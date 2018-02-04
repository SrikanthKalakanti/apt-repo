'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:ClientDetailsCtrl
 * @description
 * # ClientDetailsCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
    .controller('ClientDetailsCtrl', function($scope, AssetService) {
        $scope.tab = 1;
        $scope.userData = JSON.parse(localStorage.getItem('userData'));
        $scope.clientData = JSON.parse(localStorage.getItem("clientData"));
        $scope.client = $scope.clientData;
        $scope.setTab = function(newTab) {
            if (newTab === 2) {
                $scope.getAssetDetails();
            }
            $scope.tab = newTab;
        };

        $scope.isSet = function(tabNum) {
            return $scope.tab === tabNum;
        };
        $scope.enableForm = function() {
            $scope.readOnly = true;
        };

        $scope.getAssetDetails = function() {
            AssetService.getAllAssets($scope.userData.customerId)
                .then(function success(response) {
                    $scope.assets = response.data.result;
                    //$scope.successMessage = response.data.errorMessage;
                    //$scope.successMessagebool = true;
                    localStorage.setItem('assetData', $scope.assets);
                    //$scope.gridOptions.data = $scope.assets;
                }, function error(response) {
                    $scope.errorMessage = response.data.errorMessage;
                    $scope.errorMessagebool = true;
                    //$window.scrollTo(0, 0);
                });
        };



    });