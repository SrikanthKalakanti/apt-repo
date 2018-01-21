var LoginService = angular.module('LoginService', []);
angular.module('myAppApp').service('LoginService', function($http){
    var loginData = {};
    this.login = function (loginObj) {
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase+'/apt/customer/login', loginObj);
    }
    this.saveLoginData = function(data){        
        this.loginData = data;
        console.log(this.loginData);
    };
    this.getLoginData = function(){
        console.log(this.loginData);        
        return this.loginData;
    };
});