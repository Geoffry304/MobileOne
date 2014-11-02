'use strict';
/* global app:true */

/**
 * @ngdoc overview
 * @name oefeningTutorialApp
 * @description
 * # oefeningTutorialApp
 *
 * Main module of the application.
 */
var app = angular.module('webappsApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute',
  'firebase'
])
.constant('FIREBASE_URL','https://mobileone.firebaseio.com/');

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
  .when('/users/:userId', {
    templateUrl: 'views/profile.html',
    controller: 'ProfileCtrl'
  })
  .otherwise({
    redirectTo: '/'
  });

});
