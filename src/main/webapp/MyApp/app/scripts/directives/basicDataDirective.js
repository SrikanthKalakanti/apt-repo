"use strict";
angular.module('myAppApp').directive('basicData', function() {
    return {
        templateUrl: '../../../views/basicdata.html',
        replace: false,
        restrict: 'E',
        scope: true,
        compile: function() {

        },
        controller: function($scope, BasicInfoService, $window, $timeout) {
            $scope.userData = JSON.parse(localStorage.getItem('userData'));
            $scope.basicInfo = {};
            $scope.basic = {
                status: "-1"
            };
            $scope.showBasicForm = false;
            $scope.addBasiInfo = function() {
                $scope.showBasicForm = true;
            };
            $scope.cancel = function() {
                $scope.showBasicForm = false;
            };
            $scope.addBasicInfo = function() {
                $scope.showBasicForm = true;
                $scope.basic.clientId = $scope.userData.customerId;
                var bussDate = new Date($scope.basic.businessCommencement);
                var termDate = new Date($scope.basic.termLoanDisbursement);
                $scope.basic.businessCommencement = bussDate.getDate() + "/" + (bussDate.getMonth() + 1) + "/" + bussDate.getFullYear();
                $scope.basic.termLoanDisbursement = termDate.getDate() + "/" + (termDate.getMonth() + 1) + "/" + termDate.getFullYear();
                console.log("buss : " + $scope.basic.businessCommencement);
                console.log("term : " + $scope.basic.termLoanDisbursement);
                // if ($scope.basicForm.$valid) {
                BasicInfoService.saveBasicInfo($scope.basic)
                    .then(function success(response) {
                        $scope.successMessage = response.data.errorMessage;
                        $scope.successMessagebool = true;
                        $window.scrollTo(0, 0);
                        $scope.safeApply($timeout(function() {
                            $scope.successMessagebool = false;
                        }, 3000));
                        $scope.getBasicInfoDetails();
                        $scope.showBasicForm = false;
                    }, function error(response) {
                        $scope.errorMessage = response.data.errorMessage;
                        $scope.errorMessagebool = true;
                        $window.scrollTo(0, 0);
                    });
                // }
            };
            $scope.getBasicInfoDetails = function() {
                console.log($scope.userData.customerId);
                BasicInfoService.getAllBasicInfo($scope.userData.customerId)
                    .then(function success(response) {
                        $scope.basicInfo = response.data.result;
                        console.log($scope.basicInfo);
                    }, function error(response) {
                        $scope.errorMessage = response.data.errorMessage;
                        $scope.errorMessagebool = true;
                        //$window.scrollTo(0, 0);
                    });
            };
            $scope.updateDetails = function(row) {
                console.log(row);
                $scope.showBasicForm = true;
                $scope.isUpdate = true;
                $scope.basic = row;
                $scope.basic.businessCommencement = new Date($scope.basic.businessCommencement);
                $scope.basic.termLoanDisbursement = new Date($scope.basic.termLoanDisbursement);
                $scope.basic.status = $scope.basic.status.toString();
            };
            $scope.updateBasicInfo = function() {
                console.log($scope.userData.customerId);
                var bussDate = new Date($scope.basic.businessCommencement);
                var termDate = new Date($scope.basic.termLoanDisbursement);
                $scope.basic.businessCommencement = bussDate.getDate() + "/" + (bussDate.getMonth() + 1) + "/" + bussDate.getFullYear();
                $scope.basic.termLoanDisbursement = termDate.getDate() + "/" + (termDate.getMonth() + 1) + "/" + termDate.getFullYear();
                BasicInfoService.updateBasicInfo($scope.basic)
                    .then(function success(response) {
                        //$scope.assets = response.data.result;
                        console.log(response);
                        $scope.successMessage = response.data.errorMessage;
                        $scope.successMessagebool = true;
                        $window.scrollTo(0, 0);
                        $scope.safeApply($timeout(function() {
                            $scope.successMessagebool = false;
                        }, 3000));
                        $scope.getBasicInfoDetails();
                        $scope.showAssetForm = false;
                    }, function error(response) {
                        $scope.errorMessage = response.data.errorMessage;
                        $scope.errorMessagebool = true;
                        $window.scrollTo(0, 0);
                    });
            };
            $scope.getBasicInfoDetails();
            $scope.safeApply = function(fn) {
                var phase = this.$root.$$phase;
                if (phase === '$apply' || phase === '$digest') {
                    if (fn && (typeof(fn) === 'function')) {
                        fn();
                    }
                } else {
                    this.$apply(fn);
                }
            };
        }
    };
});