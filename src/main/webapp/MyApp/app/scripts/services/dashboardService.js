angular.module('myAppApp').service('DashboadService', function($http){
    this.getAllClients = function (customerId) {
        console.log(customerId);
        var urlBase = 'http://localhost:8080';
        return $http.get(urlBase+'/apt/client/getallclientsbycustomerid?customerId='+customerId);
    }
});