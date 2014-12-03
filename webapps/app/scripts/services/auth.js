'use strict';
/*global app:true*/
app.factory('Auth', function ($firebaseSimpleLogin, FIREBASE_URL, $rootScope, $firebase, $location){

	var ref = new Firebase(FIREBASE_URL);
	var auth = $firebaseSimpleLogin(ref);
	var vakanties = $firebase(ref.child('vakantie')).$asArray();
	var usernm = '';
	


	var Auth = {
		register: function(user) {
			usernm = user.username;
			return auth.$createUser(user.email, user.password);
		},
		createProfile: function (user) {
			var profile = {
					username: usernm,
					email: user.email,
					md5_hash: 'http://www.gravatar.com/avatar/' + user.md5_hash,
					role_value: '10'
			};

			var profileRef = $firebase(ref.child('profile'));
			return profileRef.$set(user.uid, profile);
		},
    	createFbProfile: function (user) {
    		var fb = ref.child('profile').child(user.uid);
			var role = '10';
			if(fb !== undefined)
			{
				fb.child('role_value').once('value', function(snapshot){
    				role = snapshot.C.F;
    		});
			}
			else
			{
				role = '10';
			}
			var profile = {
					username: user.thirdPartyUserData.first_name,
					md5_hash: user.thirdPartyUserData.picture.data.url,
					email: user.thirdPartyUserData.email,
					role_value: role,
					naam: user.thirdPartyUserData.last_name,
					voornaam: user.thirdPartyUserData.first_name,
					//gemeente: user.thirdPartyUserData.location.name
			};

			var profileRef = $firebase(ref.child('profile'));
			return profileRef.$update(user.uid, profile);
		},
		login: function (user) {
      		return auth.$login('password', user);
    	},
    	facebookLogin: function(user) {
    		return auth.$login('facebook', {
    			rememberMe: true,
    			scope: 'email,user_likes,user_about_me, user_location'
    		});

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