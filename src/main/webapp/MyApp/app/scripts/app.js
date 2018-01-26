'use strict';

/**
 * @ngdoc overview
 * @name myAppApp
 * @description
 * # myAppApp
 *
 * Main module of the application.
 */
angular
  .module('myAppApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'LoginService',
    'ui.grid'
  ])
  .config(function ($routeProvider,$locationProvider) {
    $locationProvider.hashPrefix('');
    // $http.defaults.headers.common['Accept'] = 'application/json;odata=verbose';
    $routeProvider
    .when('/home', {
        templateUrl: 'views/home.html'
        //controller: 'LoginCtrl',
        //controllerAs: 'login'
      }).when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl',
        controllerAs: 'login'
      }).when('/dashboard', {
        templateUrl: 'views/dashboard.html',
        controller: 'DashboardCtrl',
        controllerAs: 'dashboard'
      }).when('/addClient', {
        templateUrl: 'views/addClient.html',
        controller: 'AddClientCtrl',
        controllerAs: 'addClient'
      }).when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl',
        controllerAs: 'register'
      }).when('/basicdata', {
        templateUrl: 'views/basicdata.html',
        controller: 'BasicDataCtrl',
        controllerAs: 'basicdata'
      }).when('/assets', {
        templateUrl: 'views/assets.html',
        controller: 'AssetsCtrl',
        controllerAs: 'assetsData'
      })
      .otherwise({
        redirectTo: '/home'
      });
  });
