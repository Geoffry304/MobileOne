'use strict';
/*global app:true*/
app.factory('Vakantie', function (FIREBASE_URL, $firebase,$location){

	var ref = new Firebase(FIREBASE_URL);
	var vakanties = $firebase(ref.child('vakanties')).$asArray();

	var Vakantie = {
    all: vakanties,
    create: function (vakantie) {
      $location.path('/vakanties');
      return vakanties.$add(vakantie);
    },
    get: function (vakantieId) {
      return $firebase(ref.child('vakanties').child(vakantieId)).$asObject();
    },
    delete: function (vakantie) {
      var vakantieRef = $firebase(ref.child('vakanties'));
      return vakantieRef.$remove(vakantie.$id, vakantie);
    },
    update: function (vakantieId, vakantie) {
      var vakantieUpdate = {
              naam: vakantie.naam,
              promoTekst: vakantie.promoTekst,
              plaats: {plaatsnaam: vakantie.plaats.plaatsnaam, contact: {telNr: vakantie.plaats.contact.telNr, website: vakantie.plaats.contact.website,
                adres: {straat: vakantie.plaats.contact.adres.straat, nr: vakantie.plaats.contact.adres.nr,
                postcode: vakantie.plaats.contact.adres.postcode, gemeente: vakantie.plaats.contact.adres.gemeente,
                long: vakantie.plaats.contact.adres.long, lat: vakantie.plaats.contact.adres.lat}}},
              vakantiePeriode: {vakantieNaam:vakantie.vakantiePeriode.vakantieNaam, periode:{van: vakantie.vakantiePeriode.periode.van, tot: vakantie.vakantiePeriode.periode.tot}},
              thema: vakantie.thema,
              leeftijdscategorie: {van: vakantie.leeftijdscategorie.van, tot: vakantie.leeftijdscategorie.tot},
              maxAantalId: vakantie.maxAantal,
              busVervoer: vakantie.busVervoer,
              eigenVervoer: vakantie.eigenVervoer,
              fiscaalAftrek: vakantie.fiscaalAftrek
        };
        var vakantieRef = $firebase(ref.child('vakanties'));
        $location.path('/vakanties');
      return vakantieRef.$update(vakantieId, vakantieUpdate);
    },
    getComments: function(vakantieId) {
      return $firebase(ref.child('vakanties').child(vakantieId).child('comments')).$asObject();
    },
    comment: function(comment, vakantieId) {
      var comments = $firebase(ref.child('vakanties').child(vakantieId).child('comments')).$asArray();
      return comments.$add(comment);
    }
  };
	return Vakantie;
});
