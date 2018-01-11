var RegisterService = angular.module('RegisterService', [])
RegisterService.factory('RegisterDataOp', ['$http', function ($http) {

    var urlBase = 'http://localhost:8080';
    var RegisterDataOp = {};

    RegisterDataOp.register = function (registerObj) {
        console.log(registerObj);
        return $http.post(urlBase+'/apt/customer/register', registerObj);
    };
    return RegisterDataOp;

}]);