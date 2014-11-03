	'use strict';

app.factory('Vakantie', function ($firebaseSimpleLogin, FIREBASE_URL, $rootScope, $firebase){

	var ref = new Firebase(FIREBASE_URL);
	var vakanties = $firebase(ref).$asArray();

	var Vakantie = {
		all: vakanties,
		get: function (vakantieId) {
      return $firebase(ref.child(vakantieId)).$asObject();
    	},
		maakVakantie: function(vakantie) {
			return ref.set({
				vakantie: {
					naam: vakantie.naam,
					leeftijdscategorie: vakantie.leeftijdscategorie
				},
				vak: {
					naam: "Kreta",
					leeftijdscategorie: "17-18"
				}
			});
  }
	};

return Vakantie;
});
