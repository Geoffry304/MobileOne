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
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'firebase',
    'djds4rce.angular-socialshare'
  ])
  .constant('FIREBASE_URL','https://mobileone.firebaseio.com')
  .constant('uiCalendarConfig', {calendars: {}});

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
    .when('/', {
      templateUrl: 'views/homepagina.html',
      controller: 'HomeCtrl'
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
    .when('/monitor', {
      templateUrl: 'views/monitor.html',
      controller: 'MonitorCtrl',
      resolve: {
        user: function(Auth){
          return Auth.resolveUser();
        }
      }
    })
    .when('/about', {
      templateUrl: 'views/about.html',
      controller: 'AboutCtrl'
    })
    .when('/vakanties', {
      templateUrl: 'views/vakanties.html',
      controller: 'VakantieCtrl'
    })
    .when('/admin/vakantie', {
      templateUrl: 'views/vakantie.html',
      controller: 'VakantieCtrl'
    })
    .when('/vakanties', {
      templateUrl: 'views/vakanties.html',
      controller: 'VakantieCtrl'
    })
    .when('/vakantie/:vakantieId', {
      templateUrl: 'views/ToonVakantie.html',
      controller: 'ToonVakantieCtrl'
    })
    .when('/vakantie/change/:vakantieId', {
      templateUrl: 'views/vakantie.html',
      controller: 'VakantieCtrl'
    })
    .when('/admin/vakanties', {
      templateUrl:'views/adminVakanties.html',
      controller:'VakantieCtrl'
    })
    .when('/admin/gebruikers', {
      templateUrl:'views/adminGebruikers.html',
      controller:'ProfileCtrl'
    })
    .when('/users/:userId', {
      templateUrl: 'views/profile.html',
      controller: 'ProfileCtrl'
    })
    .when('/about', {
      templateUrl: 'views/about.html',
      controller: 'AboutCtrl'
    })
    .when('/inschrijven', {
      templateUrl: 'views/inschrijven.html',
      controller: 'InschrijvingCtrl',
      resolve: {
        user: function(Auth){
          return Auth.resolveUser();
        }
      }
    })
    .when('/beheerderspagina', {
      templateUrl: 'views/beheerderspagina.html',
      controller: 'BeheerderCtrl'
    })
    .otherwise({
      redirectTo: '/'
    });
    //$locationProvider.html5Mode(true);
  });