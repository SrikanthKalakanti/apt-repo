'use strict';

/**
 * @ngdoc function
 * @name myAppApp.controller:ClientDetailsCtrl
 * @description
 * # ClientDetailsCtrl
 * Controller of yapp
 */
angular.module('myAppApp')
    .controller('ClientDetailsCtrl', function($scope, $location, CustomerService, $window, $timeout, AssetService) {
        $scope.tab = 1;
        $scope.readOnly = false;
        $scope.client = {
            state: "-1"
        };
        $scope.states = {
            "AP": "Andhra Pradesh",
            "AR": "Arunachal Pradesh",
            "AS": "Assam",
            "BR": "Bihar",
            "CG": "Chhattisgarh",
            "Chandigarh": "Chandigarh",
            "DN": "Dadra and Nagar Haveli",
            "DD": "Daman and Diu",
            "DL": "Delhi",
            "GA": "Goa",
            "GJ": "Gujarat",
            "HR": "Haryana",
            "HP": "Himachal Pradesh",
            "JK": "Jammu and Kashmir",
            "JH": "Jharkhand",
            "KA": "Karnataka",
            "KL": "Kerala",
            "MP": "Madhya Pradesh",
            "MH": "Maharashtra",
            "MN": "Manipur",
            "ML": "Meghalaya",
            "MZ": "Mizoram",
            "NL": "Nagaland",
            "OR": "Orissa",
            "PB": "Punjab",
            "PY": "Pondicherry",
            "RJ": "Rajasthan",
            "SK": "Sikkim",
            "TN": "Tamil Nadu",
            "TR": "Tripura",
            "UP": "Uttar Pradesh",
            "UK": "Uttarakhand",
            "WB": "West Bengal"
        };
        $scope.clientData = JSON.parse(localStorage.getItem("clientData"));
        var date = new Date($scope.clientData.dateoffirstditributionoftermloan);
        $scope.clientData.dateoffirstditributionoftermloan = new Date(date.getFullYear(), date.getMonth(), date.getDate());
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
            $scope.assetData = {};
        };
        $scope.updateClient = function() {
            $scope.userData = JSON.parse(localStorage.getItem('userData'));
            $scope.client.customerId = $scope.userData.customerId;
            if ($scope.clientForm.$valid) {
                CustomerService.updateCustomer($scope.client)
                    .then(function success(response) {
                        $scope.successMessage = response.data.errorMessage;
                        $scope.successMessagebool = true;
                        $window.scrollTo(0, 0);
                        $scope.safeApply($timeout(function() {
                            $scope.successMessagebool = false;
                        }, 3000));
                    }, function error(response) {
                        $scope.errorMessage = response.data.errorMessage;
                        $scope.errorMessagebool = true;
                        $window.scrollTo(0, 0);
                    });
            }
        };
        $scope.addAsset = function() {
            $scope.showAssetForm = true;
        };
        $scope.saveAsset = function() {
            $scope.userData = JSON.parse(localStorage.getItem('userData'));
            $scope.asset.clientId = $scope.userData.customerId;
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
                            $scope.asset = {};
                        }
                    }, function error(response) {
                        $scope.errorMessage = response.data.errorMessage;
                        $scope.errorMessagebool = true;
                        $window.scrollTo(0, 0);
                    });
            }
        };
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
    });