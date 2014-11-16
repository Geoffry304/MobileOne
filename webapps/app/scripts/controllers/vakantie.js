'use strict';
/*global app:true*/
app.controller('VakantieCtrl', function ($scope, $routeParams, $location, Vakantie, Auth) {

$scope.vakanties = Vakantie.all;
$scope.user = Auth.user;
//$scope.vakantie = Vakantie.get($routeParams.vakantieId);


if($routeParams.vakantieId !== undefined)
{
	$scope.vakantie = Vakantie.get($routeParams.vakantieId);
	$scope.Vakantie = function() {
    return Vakantie.update($routeParams.vakantieId, $scope.vakantie);
  };
}
else
{
	$scope.fotos = {foto1:'', foto2:'', foto3:''};
	$scope.vakantie = {naam:'', promoTekst:'', fotos: $scope.fotos , plaats: {plaatsnaam:'', contact:{telNr: '', website: '', adres:{straat:'', nr:'', postcode:'', gemeente:'', long:'', lat:''}}}, vakantiePeriode: {vakantieNaam: '', periode:{van:'', tot:''}}, thema: '', leeftijdscategorie:{van:'', tot:'' }, maxAantalId: '', busVervoer: 'true', eigenVervoer: 'true', fiscaalAftrek: 'true'};
	$scope.Vakantie = function() {
    Vakantie.create($scope.vakantie);
  };
}
	$scope.admin = function(role){
    return role === '99';
    };
});
