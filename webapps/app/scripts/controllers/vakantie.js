'use strict';
/*global app:true*/
app.controller('VakantieCtrl', function ($scope, $location, Vakantie, Auth) {

$scope.vakanties = Vakantie.all;
$scope.user = Auth.user;
$scope.fotos = {foto1:'', foto2:'', foto3:''};
$scope.plaats = {plaatsnaam:'', contact:{telNr: '', website: '', adres:{straat:'', nr:'', postcode:'', gemeente:'', long:'', lat:''}}};
$scope.vakantiePeriode = {vakantieNaam: '', periode:{van:'', tot:''}};
$scope.vakantie = {naam:'', promoTekst:'', fotos: $scope.fotos , plaats: $scope.plaats, vakantiePeriode: $scope.vakantiePeriode, thema: '', leeftijdscategorie:{van:'', tot:'' }, maxAantalId: '', busVervoer: 'true', eigenVervoer: 'true', fiscaalAftrek: 'true'};

	$scope.admin = function(role){
    return role === '99';
    };
$scope.maakVakantie = function () {
    Vakantie.create($scope.vakantie);
    
  };
    $scope.wijzigVakantie = function() {
    return Vakantie.update($routeParams.vakantieId, $scope.vakantie);
  };

});
