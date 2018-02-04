"use strict";
angular.module('myAppApp').service('BasicInfoService', function($http) {
    this.saveBasicInfo = function(basicObj) {
        console.log(basicObj);
        var basicObjNew = { "basicInput": basicObj };
        console.log(basicObjNew);
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase + '/apt/apt/createbasicinput', basicObjNew);
    };
    this.getAllBasicInfo = function(customerId) {
        console.log(customerId);
        var cusomerObj = { clientId: customerId };
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase + '/apt/apt/getbasicinputbyclient', cusomerObj);
    };
    this.updateBasicInfo = function(basicObj) {
        console.log(basicObj);
        var basicObjNew = { "basicInput": basicObj };
        console.log(basicObjNew);
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase + '/apt/apt/updatebasicinput', basicObjNew);
    }
});