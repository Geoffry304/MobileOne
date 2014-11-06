'use strict';
/*global app:true*/
app.controller('AuthCtrl', function($scope, $location, Auth, user) {
	if(user){
		$location.path('/');
	}

	$scope.login = function() {
		Auth.login($scope.user).then(function() {
			$location.path('/');
		}, function (error) {
			$scope.error = error.toString();
			console.log(error.toString());
		});
	};

	/*$scope.loginWithFacebook = function() {
		/*Auth.login('facebook').then(function(user) {
			console.log('Logged in as: ' + user.uid);
		}, function(error) {
			console.error('Login failed: ' + error);
		});
		console.log('click');
		  Auth.authWithOAuthPopup('facebook', function(error, authData) {
			if (authData) {
				console.log(authData.facebook.accessToken);
			}
		}, {
  		scope: 'email,user_likes'
		});
	};*/

	$scope.register = function() {
		Auth.register($scope.user).then(function(user) {
			return Auth.login($scope.user).then(function() {
				user.username = $scope.user.username;
				return Auth.createProfile(user);
			}).then(function() {
				$location.path('/');
			});
		}, function(error) {
			$scope.error = error.toString();
			console.log(error.toString());
		});
	};
});