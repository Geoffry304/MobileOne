'use strict';
/*global app:true*/
app.controller('ToonVakantieCtrl', function ($scope, $routeParams, Vakantie) {
  $scope.vakantie = Vakantie.get($routeParams.vakantieId);
});