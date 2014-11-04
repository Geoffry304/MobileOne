'use strict';

app.controller('ToonVakantieCtrl', function ($scope, $routeParams, Vakantie, Auth) {
  $scope.vakantie = Vakantie.get($routeParams.vakantieId);
  $scope.signedIn = Auth.signedIn;
});