	'use strict';

app.factory('Vakantie', function (FIREBASE_URL, $firebase){

	var ref = new Firebase(FIREBASE_URL);
	var vakanties = $firebase(ref.child('vakanties')).$asArray();

	var Vakantie = {
    all: vakanties,
    create: function (vakantie) {
      return vakanties.$add(vakantie);
    },
    get: function (vakantieId) {
      return $firebase(ref.child('vakanties').child(vakantieId)).$asObject();
    },
    delete: function (vakantie) {
      return vakanties.$remove(vakantie);
    },
    comments: function(vakantieId) {
      return $firebase(ref.child('comments').child(vakantieId)).$asArray();
    }
  };

	/*var Vakantie = {
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
	};*/

return Vakantie;
});
