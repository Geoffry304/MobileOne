'use strict';
/*global app:true*/
app.factory('Auth', function ($firebaseSimpleLogin, FIREBASE_URL, $rootScope, $firebase, $location){

	var ref = new Firebase(FIREBASE_URL);
	var auth = $firebaseSimpleLogin(ref);
	var vakanties = $firebase(ref.child('vakantie')).$asArray();


	var Auth = {
		register: function(user) {
			return auth.$createUser(user.email, user.password);
		},
		createProfile: function (user) {
			var profile = {
					username: user.username,
					md5_hash: user.md5_hash,
					role_value: '10'
			};

			var profileRef = $firebase(ref.child('profile'));
			return profileRef.$set(user.uid, profile);
		},
		login: function (user) {
      		return auth.$login('password', user);
    	},
		logout: function() {
			$location.path('/');
			auth.$logout();
		},
		resolveUser: function() {
			return auth.$getCurrentUser();
		},
		signedIn: function() {
			return !!Auth.user.provider;
		},	
		user: {}
	};


	$rootScope.$on('$firebaseSimpleLogin:login', function(e, user) {
		console.log('logged in');
		angular.copy(user, Auth.user);
		Auth.user.profile = $firebase(ref.child('profile').child(Auth.user.uid)).$asObject();
		console.log(Auth.user);
	});
	$rootScope.$on('$firebaseSimpleLogin:logout', function(){
		console.log('logged out');

		if (Auth.use && Auth.user.profile) {
			$location.path('/');
			Auth.user.profile.$destroy();
		}

		angular.copy({}, Auth.user);
	});

	/*$rootScope.authWithOAuthPopup('facebook', function(error, authData) {
			if (authData) {
				console.log(authData.facebook.accessToken);
			}
		}, {
  		scope: 'email,user_likes'
		});*/


return Auth;
});