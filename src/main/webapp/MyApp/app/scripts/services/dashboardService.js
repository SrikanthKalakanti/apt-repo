angular.module('myAppApp').service('DashboadService', function($http){
    this.getAllClients = function (customerId) {
        console.log(customerId);
        var cusomerObj = {customerId : customerId};
        var urlBase = 'http://localhost:8080';
        return $http.get(urlBase+'/apt/client/getallclientsbycustomerids?customerId='+customerId);
        //return $http.get(urlBase+'/apt/client/getlistofclientdetails/'+customerId);
    }
});