'use strict';

app.controller('VakantieCtrl', function ($scope, $location, Vakantie) {
/*var vakantie = {
	naam: "test",
	leeftijdscategorie: "16-17"
};*/
$scope.vakanties = Vakantie.all;
$scope.fotos = {foto1:'', foto2:'', foto3:''};
$scope.plaats = {plaatsnaam:'', contact:{telNr: '', website: '', adres:{straat:'', nr:'', postcode:'', gemeente:'', long:'', lat:''}}};
$scope.vakantiePeriode = {vakantieNaam: '', periode:{van:'', tot:''}};
$scope.vakantie = {naam:'', promoTekst:'', fotos: $scope.fotos , plaats: $scope.plaats, vakantiePeriode: $scope.vakantiePeriode, thema: '', leeftijdscategorie:{van:'', tot:'' }, maxAantalId: '', busVervoer: 'true', eigenVervoer: 'true', fiscaalAftrek: 'true'};


$scope.maakVakantie = function () {
    Vakantie.create($scope.vakantie);
    
  };

});
