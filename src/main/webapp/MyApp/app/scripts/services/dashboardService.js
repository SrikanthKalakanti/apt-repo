angular.module('myAppApp').service('DashboadService', function($http,$sce){
    this.getAllClients = function (customerId) {
        console.log(customerId);
        var cusomerObj = {customerId : customerId};
        var urlBase = 'http://localhost:8080';
        return $http({
            url : $sce.trustAsUrl(urlBase+'/apt/client/getallclientsbycustomerid?customerId='+customerId),
            method: 'GET',
            headers:{
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
            'Access-Control-Allow-Credentials' : true,
            "Content-Type": "application/json"
            }
        });
        //return $http.get(urlBase+'/apt/client/getlistofclientdetails/'+customerId);
    }
});