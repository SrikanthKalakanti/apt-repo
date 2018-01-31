angular.module('myAppApp').service('DashboadService', function($http,$sce){
    this.getAllClients = function (customerId) {
        console.log(customerId);
        var cusomerObj = {customerId : customerId};
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase+'/apt/client/getallclientsbycustomer', cusomerObj);
    }
});