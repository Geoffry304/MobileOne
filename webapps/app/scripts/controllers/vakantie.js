'use strict';

app.controller('VakantieCtrl', function ($scope, $location, Vakantie, FIREBASE_URL) {
var vakantie = {
	naam: "test",
	leeftijdscategorie: "16-17"
};
$scope.vakantie = Vakantie.all;
/*$scope.vakantie = {
	naam: vakantie.naam,
	leeftijdscategorie: vakantie.leeftijdscategorie
};*/

$scope.maakVakantie = function() {
	Vakantie.maakVakantie(vakantie);
};

});
