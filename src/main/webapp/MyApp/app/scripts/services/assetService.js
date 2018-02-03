"use strict";
angular.module('myAppApp').service('AssetService', function($http) {
    this.saveAsset = function(assetObj) {
        console.log(assetObj);
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase + '/apt/apt/createassetinput', assetObj);
    };
});