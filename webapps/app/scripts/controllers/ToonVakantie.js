'use strict';
<<<<<<< HEAD

app.controller('ToonVakantieCtrl', function ($scope, $routeParams, Vakantie, Auth) {
=======
/*global app:true*/
app.controller('ToonVakantieCtrl', function ($scope, $routeParams, Vakantie) {
>>>>>>> origin/master
  $scope.vakantie = Vakantie.get($routeParams.vakantieId);
  $scope.signedIn = Auth.signedIn;
});