angular.module('myAppApp').service('RegisterService', function($http){
    this.login = function (registerObj) {
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase+'/apt/customer/register', registerObj);
    }
});