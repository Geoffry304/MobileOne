'use strict';
/* global app:true */

/**
 * @ngdoc overview
 * @name webappsApp
 * @description
 * # webappsApp
 *
 * Main module of the application.
 */
var app = angular.module('webappsApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'firebase'
  ])
  .constant('FIREBASE_URL','https://mobileone.firebaseIO.com');

  app.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/register', {
    templateUrl: 'views/register.html',
    controller: 'AuthCtrl',
    resolve: {
      user: function(Auth) {
        return Auth.resolveUser();
      }
    }
  })
  .when('/login', {
    templateUrl: 'views/login.html',
    controller: 'AuthCtrl',
    resolve: {
      user: function(Auth){
        return Auth.resolveUser();
      }
    }
  })
      .otherwise({
        redirectTo: '/'
      });
  });
