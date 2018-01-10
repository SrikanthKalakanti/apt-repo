var LoginService = angular.module('LoginService', [])
LoginService.factory('LoginDataOp', ['$http', function ($http) {

    var urlBase = 'http://localhost:8080';
    var LoginDataOp = {};

    LoginDataOp.login = function (loginObj) {
        console.log(loginObj);
        return $http.post(urlBase+'/apt/customer/login', loginObj);
    };
    return LoginDataOp;

}]);