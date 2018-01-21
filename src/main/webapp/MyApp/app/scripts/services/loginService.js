var LoginService = angular.module('LoginService', []);
angular.module('myAppApp').service('LoginService', function($http){
    this.loginData = {};
    this.login = function (loginObj) {
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase+'/apt/customer/login', loginObj);
    }
    this.saveLoginData = function(data){        
        this.loginData = data;
        console.log(this.loginData);
        localStorage.setItem("loginData", this.loginData);
    };
    this.getLoginData = function(){
        console.log(this.loginData);        
        return this.loginData;
    };
});