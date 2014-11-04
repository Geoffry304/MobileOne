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
    'firebase'
  ])
  .constant('FIREBASE_URL','https://mobileone.firebaseio.com');

  app.config(function ($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: 'views/posts.html',
      controller: 'PostsCtrl'
    })
      .when('/posts/:postId', {
      templateUrl: 'views/showpost.html',
      controller: 'PostViewCtrl'
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
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/vakanties', {
        templateUrl: 'views/vakanties.html',
        controller: 'VakantieCtrl'
      })
      .when('/vakantie', {
        templateUrl: 'views/vakantie.html',
        controller: 'VakantieCtrl'
      })
      .when('/vakantie/:vakantieId', {
        templateUrl: 'views/ToonVakantie.html',
        controller: 'ToonVakantieCtrl'
      })
      .when('/users/:userId', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });