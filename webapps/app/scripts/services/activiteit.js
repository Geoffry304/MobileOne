'use strict';
/*global app:true*/
app.factory('Activiteit', function (FIREBASE_URL, $firebase,$location){

	var ref = new Firebase(FIREBASE_URL);
	var activiteiten = $firebase(ref.child('activiteiten')).$asArray();

	var Activiteit = {
    all: activiteiten,
    create: function (activiteit) {
      $location.path('/activiteiten');
      return activiteiten.$add(activiteit);
    },
    get: function (activiteitId) {
      return $firebase(ref.child('activiteiten').child(activiteitId)).$asObject();
    },
    delete: function (activiteit) {
      var activiteitRef = $firebase(ref.child('activiteiten'));
      return activiteitRef.$remove(activiteit.$id, activiteit);
    },
    update: function (activiteitId, activiteit) {
      var activiteitUpdate = {
        //$scope.activiteit = {naam:'', promoTekst:'',adres:{straat:'', nr:'', postcode:'', gemeente:'', long:'', lat:''}
        //,prijs:'', periode:{van:'', tot:''}, soort: '', maxAantal: ''};
              naam: activiteit.naam,
              promoTekst: activiteit.promoTekst,
              adres: {straat: activiteit.adres.straat, nr: activiteit.adres.nr,
                postcode: activiteit.adres.postcode, gemeente: activiteit.adres.gemeente,
                long: activiteit.adres.long, lat: activiteit.adres.lat},
              periode:{van: activiteit.periode.van, tot: activiteit.periode.tot},
              maxAantalId: activiteit.maxAantal,
              soort: activiteit.soort
        };
        var activiteitRef = $firebase(ref.child('activiteiten'));
        $location.path('/activiteiten');
      return activiteitRef.$update(activiteitId, activiteitUpdate);
    },
    Inschrijving: function(activiteitId, monitor)
    {
        var inschrijving = $firebase(ref.child('activiteiten').child(activiteitId).child('inschrijvingen')).$asArray();
        return inschrijving.$add(monitor);
    },
    getComments: function(activiteitId) {
      return $firebase(ref.child('activiteiten').child(activiteitId).child('comments')).$asObject();
    },
    comment: function(comment, activiteitId) {
      var comments = $firebase(ref.child('activiteiten').child(activiteitId).child('comments')).$asArray();
      return comments.$add(comment);
    }
  };
	return Activiteit;
});
