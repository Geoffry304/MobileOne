'use strict';
/*global app:true*/
app.controller('ProfileCtrl', function($scope, $routeParams, Profile) {
	var uid = $routeParams.userId;

	$scope.profile = Profile.get(uid);
	

	$scope.submitProfiel = function () {
	console.log($scope.profile.username); 
	Profile.update(uid,$scope.profile);
    /*$scope.post.creator = $scope.user.profile.username;
    $scope.post.creatorUID = $scope.user.uid;
    console.log($scope.user.uid);
    Post.create($scope.post).then(function (ref) {
      $location.path('/posts/' + ref.name());
      $scope.post = {url: 'http://', title: ''};
    });*/
  };
});