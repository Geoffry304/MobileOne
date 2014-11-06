'use strict';

/*global app:true*/

app.controller('ToonVakantieCtrl', function ($scope, $routeParams, Vakantie, Auth) {

  $scope.vakantie = Vakantie.get($routeParams.vakantieId);
  $scope.signedIn = Auth.signedIn;
  $scope.comments = Vakantie.getComments($routeParams.vakantieId);
  $scope.comment = {};
  $scope.user = Auth.user;

    $scope.voegCommentToe = function() {
   	$scope.comment.creator = $scope.user.profile.username;
  	Vakantie.comment($scope.comment, $routeParams.vakantieId);
  };
});