'use strict';
/*global app:true*/
app.factory('Profile', function($window, FIREBASE_URL, $firebase, Post, $q, $location){
	var ref = new $window.Firebase(FIREBASE_URL);

	var profile = {
		get: function(userId) {
			return $firebase(ref.child('profile').child(userId)).$asObject();
		},
		update: function (userId, profile) {
         	var profileUpdate = {
					username: profile.username,
					gemeente: profile.gemeente,
					naam: profile.naam,
					nummer: profile.nummer,
					postcode: profile.postcode,
					straat: profile.straat,
					voornaam: profile.voornaam
			};

			var profileRef = $firebase(ref.child('profile'));
			return profileRef.$update(userId, profileUpdate);

    	},
		getPosts: function(userId) {
			var defer = $q.defer();

			$firebase(ref.child('user_posts').child(userId))
			.$asArray()
			.$loaded()
			.then(function(data) {
				var posts = {};

				for (var i = 0; i<data.length; i++) {
					var value = data[i].$value;
					posts[value] = Post.get(value);
				}
				defer.resolve(posts);
			},
			function(err){
				$location.path('/');
			});

			return defer.promise;
		}
	};
	return profile;
});