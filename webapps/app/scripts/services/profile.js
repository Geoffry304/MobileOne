'use strict';
/*global app:true*/
app.factory('Profile', function($window, FIREBASE_URL, $firebase, $q, $location, Inschrijving){
	var ref = new $window.Firebase(FIREBASE_URL);
	var profiles = $firebase(ref.child('profile')).$asArray();

	var profile = {
		all: profiles,
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
		updateKind: function (kindId, kind, userId) {
			var profileUpdate = {
					geboortedatum: kind.geboortedatum,
					gemeente: kind.gemeente,
					naam: kind.naam,
					nummer: kind.nummer,
					postcode: kind.postcode,
					straat: kind.straat,
					voornaam: kind.voornaam,
					rijksregisternummer: kind.rijksregisternummer
				};

			var kindRef = $firebase(ref.child('profile').child(userId).child('kinderen'));
			return kindRef.$update(kindId, profileUpdate);
		},
		createKind: function(userId, kind){
			var kindRef = $firebase(ref.child('profile').child(userId).child('kinderen')).$asArray();
			return kindRef.$add(kind);
		},
		getKinderen: function(userId){
			//var defer = $q.defer();


			return $firebase(ref.child('profile').child(userId).child('kinderen'))
			.$asArray();
			/*.then(function(data) {
				var kinderen = {};

				for (var i = 0; i<data.length; i++) {
					var value = data[i].$value;
					kinderen[value] = 
				}
			});*/
		},
		getInschrijvingen: function(userId) {
	      var defer = $q.defer();

	      $firebase(ref.child('user_inschrijving').child(userId))
	        .$asArray()
	        .$loaded()
	        .then(function(data) {
	          var inschrijvingen = {};

	          for(var i = 0; i<data.length; i++) {
	            var value = data[i].$value;
	            inschrijvingen[value] = Inschrijving.get(value);
	          }
	          defer.resolve(inschrijvingen);
	        });

	      return defer.promise;
	    }
	};
	return profile;
});