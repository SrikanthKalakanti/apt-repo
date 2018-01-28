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
  .config(function ($routeProvider,$locationProvider,$httpProvider) {
    $locationProvider.hashPrefix('');
    // $httpProvider.defaults.useXDomain = true;
    // $httpProvider.defaults.headers.common['Content-Type'] = 'application/json;charset=UTF-8';
    // $httpProvider.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:9000';
    // $httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = 'Content-Type';
    // $httpProvider.defaults.headers.common['Access-Control-Allow-Methods'] = 'GET, POST, OPTIONS';
    // $httpProvider.defaults.headers.common['Access-Control-Allow-Credentials'] = 'true';
    // delete $httpProvider.defaults.headers.common['X-Requested-With'];
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
      }).when('/clientDetails', {
        templateUrl: 'views/clientDetails.html',
        controller: 'ClientDetailsCtrl',
        controllerAs: 'clientDetails'
      })
      .otherwise({
        redirectTo: '/home'
      });
  });
