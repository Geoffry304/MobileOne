'use strict';


/*angular.module('webappsApp')
	.controller('LoginCtrl', function($scope, $firebase, $firebaseSimpleLogin){
 var ref = new Firebase('https://mobileone.firebaseIO.com');

 $scope.auth = $firebaseSimpleLogin(ref);

 $scope.dummySignin= function() {
 	console.log('signing in...');

 	$scope.auth.$login('password', {
        email: 'geoffry304@live.be',
        password: 'azerty12'
      }).then(function(user) {
        console.log('user: ', user);
      }, function(error) {
        console.log('error: ', error);
      });
    };
});*/
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

	$scope.register = function() {
		Auth.register($scope.user).then(function() {
			return Auth.login($scope.user).then(function() {
				$location.path('/');
			});
		}, function(error) {
			$scope.error = error.toString();
			console.log(error.toString());
		});
	};
});