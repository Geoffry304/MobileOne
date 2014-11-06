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
		createKind: function(userId, kind){
			var kindRef = $firebase(ref.child('profile').child(userId).child('kinderen').child(kind.rijksregisternummer)).$asArray();
			return kindRef.$add(kind);
		}

};
	return profile;
});