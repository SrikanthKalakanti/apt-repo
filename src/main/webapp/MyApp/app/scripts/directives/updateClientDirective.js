"use strict";
angular.module('myAppApp').directive('updateClient', function() {
    return {
        templateUrl: '../../../views/updateClient.html',
        replace: false,
        restrict: 'E',
        scope: true,
        compile: function() {

        },
        controller: function($scope, CustomerService, $window, $timeout) {
            $scope.readOnly = false;
            $scope.userData = JSON.parse(localStorage.getItem('userData'));
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
            console.log(date);
            $scope.clientData.dateoffirstditributionoftermloan = new Date(date.getFullYear(), date.getMonth(), date.getDate());
            $scope.client = $scope.clientData;
            $scope.updateClient = function() {
                //$scope.userData = JSON.parse(localStorage.getItem('userData'));
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
        }
    };
});