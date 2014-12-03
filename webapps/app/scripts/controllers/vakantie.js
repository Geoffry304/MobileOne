'use strict';
/*global app:true*/
app.controller('VakantieCtrl', function ($scope, $routeParams, $location, Vakantie, Auth) {

  $scope.vakanties = Vakantie.all;
  $scope.user = Auth.user;
  $scope.episodeImgData = [];
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
      for(var i = 0; i <= $scope.episodeImgData.length; i++)
      {
        if(i === 0)
        {
          $scope.fotos.foto1 = $scope.episodeImgData[i];
        }
        if(i === 1)
        {
          $scope.fotos.foto2 = $scope.episodeImgData[i];
        }
        if(i === 2)
        {
          $scope.fotos.foto3 = $scope.episodeImgData[i];
        }
        if(i === 3)
        {
          $scope.fotos.foto4 = $scope.episodeImgData[i];
        }
        if(i === 4)
        {
          $scope.fotos.foto5 = $scope.episodeImgData[i];
        }

      }
      
    };
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
  for(var i = 0; i < evt.target.files.length; i++)
  {
      var f = evt.target.files[i];
  var reader = new FileReader();
  reader.onload = (function(theFile) {
    return function(e) {
      var filePayload = e.target.result;
      $scope.episodeImgData.push(e.target.result); 
      console.log($scope.episodeImgData[0]);
      document.getElementById('pano').src = $scope.episodeImgData[0];
      document.getElementById('pano2').src = $scope.episodeImgData[1];
      document.getElementById('pano3').src = $scope.episodeImgData[2];
      document.getElementById('pano4').src = $scope.episodeImgData[3];
      document.getElementById('pano5').src = $scope.episodeImgData[4];

    };
  })(f);
  reader.readAsDataURL(f);
};

};
var el = document.getElementById('file-upload');
if(el){
  el.addEventListener('change',$scope.handleFileSelectAdd, false);
}
//document.getElementById('file-upload').addEventListener('change', $scope.handleFileSelectAdd, false);
});
