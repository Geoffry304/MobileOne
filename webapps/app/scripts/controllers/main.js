'use strict';

/**
 * @ngdoc function
 * @name webappsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the webappsApp
 */
angular.module('webappsApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
