'use strict';


app.controller('KampenCtrl', function ($scope, $location ,  Kamp, Auth) {
	$scope.kampen = Kamp.all;
	$scope.user = Auth.user;
	$scope.kamp = {promoTekst: '', adres:{straat: '', nr: '', postcode:'', gemeente:'', long:'', lat:''}, 
	fotos:{foto1: '', foto2:'', foto3: ''}, 
	vakantiePeriode:{vakantieNaam:'', periode: '', thema:'', leeftijdscategorie:{van:'', tot: ''},
	binnenland: 'true', maxAantalId: '', busVervoer: 'true', eigenVervoer: 'true', fiscaalAfrek: 'true' }};


  $scope.deletePost = function (kamp) {
    Kamp.delete(kamp);
  };

});