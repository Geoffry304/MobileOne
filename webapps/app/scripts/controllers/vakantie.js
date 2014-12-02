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
	$scope.vakantie = {naam:'', promoTekst:'', fotos: $scope.fotos , plaats: {plaatsnaam:'', contact:{telNr: '', website: '', adres:{straat:'', nr:'', postcode:'', gemeente:'', long:'', lat:''}}},prijs:'', vakantiePeriode: {vakantieNaam: '', periode:{van:'', tot:''}}, thema: '', leeftijdscategorie:{van:'', tot:'' }, maxAantal: '', busVervoer: false, eigenVervoer: false, fiscaalAftrek: false};
	$scope.Vakantie = function() {
    if ($scope.episodeImgData) {
      $scope.fotos.foto1 = $scope.episodeImgData;
    }
    Vakantie.create($scope.vakantie);
  };
}

$scope.go = function(vakantieId){
	if(vakantieId !== undefined)
	{
    $location.path('/vakantie/' + vakantieId);
  }
  else
  {
    console.log('got undefined');
  }
};
$scope.admin = function(role){
  return role === '99';
};

$scope.handleFileSelectAdd = function(evt) {
  var f = evt.target.files[1];
  var reader = new FileReader();
  reader.onload = (function(theFile) {
    return function(e) {
      var filePayload = e.target.result;
      $scope.episodeImgData = e.target.result; 
      document.getElementById('pano').src = $scope.episodeImgData; 
    };
  })(f);
  reader.readAsDataURL(f);
};
var el = document.getElementById('file-upload');
if(el){
  el.addEventListener('change',$scope.handleFileSelectAdd, false);
}
//document.getElementById('file-upload').addEventListener('change', $scope.handleFileSelectAdd, false);
});
