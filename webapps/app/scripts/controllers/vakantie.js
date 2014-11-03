'use strict';

app.controller('VakantieCtrl', function ($scope, $location, Vakantie) {
/*var vakantie = {
	naam: "test",
	leeftijdscategorie: "16-17"
};*/
$scope.vakanties = Vakantie.all;
$scope.vakantie = {naam:'', leeftijdscategorie:{van:'',tot:'' }}


$scope.maakVakantie = function () {
    console.log($scope.vakantie.uid);
    Vakantie.create($scope.vakantie);
    //.then(function (ref) {
      //$location.path('/vakanties/' + ref.name());
    //});
  };

/*$scope.maakVakantie = function() {
	Vakantie.maakVakantie(vakantie);
};*/

});
