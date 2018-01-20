angular.module('myAppApp').service('CustomerService', function($http){
    this.addCustomer = function (custObj) {
        console.log(custObj);
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase+'/apt/client/createclient', custObj);
    }
});