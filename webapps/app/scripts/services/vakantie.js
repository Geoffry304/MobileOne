'use strict';
/*global app:true*/
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
	return Vakantie;
});
