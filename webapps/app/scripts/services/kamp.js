'use strict';
/*global app:true*/
app.factory('Kamp', function ($firebase, FIREBASE_URL){
	var ref = new Firebase(FIREBASE_URL);
	var kampen = $firebase(ref.child('kampen')).$asArray();

  var Kamp = {
    all: kampen,
    create: function (kamp) {
      return kampen.$add(kamp);
    },
    get: function (kampId) {
      return $firebase(ref.child('kampen').child(kampId)).$asObject();
    },
    delete: function (kamp) {
      return kampen.$remove(kamp);
    },
    comments: function(kampId) {
      return $firebase(ref.child('comments').child(kampId)).$asArray();
    }
  };


	return Kamp;
});