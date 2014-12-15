'use strict';
/*global app:true*/
app.controller('AuthCtrl', function($scope, $location, Auth, user) {
	if(user){
		$location.path('/');
	}
	$scope.errormessage = '';

	$scope.login = function() {
		console.log('Test');
		Auth.login($scope.user).then(function() {
		}, function (error) {
			$scope.error = error.toString();
			console.log(error.toString());
		});
	};

	$scope.loginWithFacebook = function() {
		Auth.facebookLogin().then(function(){
			
			console.log('tttttt ');

			
				//Auth.createFbProfile(Auth.user);


			//Auth.createFbProfile(Auth.user);
		});
	};

	$scope.resetPass = function(){
		Auth.resetPass();
	};

	$scope.register = function() {
		if($scope.user.confirm == $scope.user.password)
		{
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
		}
		else
		{
			$scope.errormessage = "het wachtwoord komt niet overeen.";
		}

	};

	$scope.admin = function(role){
    return role === '99';
    };
});