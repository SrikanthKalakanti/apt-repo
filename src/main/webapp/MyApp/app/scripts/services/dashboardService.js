angular.module('myAppApp').service('DashboadService', function($http,$sce){
    this.getAllClients = function (customerId) {
        console.log(customerId);
        var cusomerObj = {customerId : customerId};
        var urlBase = 'http://localhost:8080';
        return $http({
            url : $sce.trustAsUrl(urlBase+'/apt/client/getallclientsbycustomerids?customerId='+customerId),
            method: 'GET',
            headers:{
            'Access-Control-Allow-Origin': 'http://localhost:9000',
            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Content-Type, X-Requested-With',
            "Content-Type": "application/json; charset=utf8",
            'X-Random-Shit':'123123123'
            }
        });
        //return $http.get(urlBase+'/apt/client/getlistofclientdetails/'+customerId);
    }
});