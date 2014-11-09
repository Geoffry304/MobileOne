'use strict';
/*global app:true*/
app.factory('Inschrijving', function ($firebase, FIREBASE_URL) {
	var ref = new Firebase(FIREBASE_URL);
	var inschrijvingen = $firebase(ref.child('inschrijvingen')).$asArray();
	var Inschrijving = {
		all: inschrijvingen,
		create: function (inschrijving){
			return inschrijvingen.$add(inschrijving).then(function(inschrijvingRef) {
		        $firebase(ref.child('user_inschrijvingen').child(inschrijving.creatorUID)).$push(inschrijvingRef.name());
		        return inschrijvingRef;
					});
		}
	};
	return Inschrijving;

});


