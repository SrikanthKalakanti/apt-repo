"use strict";
angular.module('myAppApp').service('AssetService', function($http) {
    this.saveAsset = function(assetObj) {
        console.log(assetObj);
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase + '/apt/apt/createassetinput', assetObj);
    };
    this.getAllAssets = function(customerId) {
        console.log(customerId);
        var cusomerObj = { clientId: customerId };
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase + '/apt/apt/getassetinputbyclient', cusomerObj);
    };
    this.updateAsset = function(assetObj) {
        console.log(assetObj);
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase + '/apt/apt/updateassetinput', assetObj);
    }
});