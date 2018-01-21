var LoginService = angular.module('LoginService', []);
angular.module('myAppApp').service('LoginService', function($http){
    this.login = function (loginObj) {
        var urlBase = 'http://localhost:8080';
        return $http.post(urlBase+'/apt/customer/login', loginObj);
    }
    this.saveLoginData = function(data){        
        this.loginData=data;
    };
    this.getLoginData = function(){        
        return loginData;
    };
});