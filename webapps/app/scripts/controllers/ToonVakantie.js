'use strict';

/*global app:true*/



app.controller('ToonVakantieCtrl', function ($scope, $routeParams, $location, Vakantie, Auth) {




  $scope.vakantie = Vakantie.get($routeParams.vakantieId);
  $scope.vakantie.$loaded().then(function(){
    $scope.fotos = $scope.vakantie.fotos;
    console.log($scope.fotos);
  
  $scope.signedIn = Auth.signedIn;
  $scope.comments = Vakantie.getComments($routeParams.vakantieId);
  $scope.comment = {};
  $scope.creator = {username:'',photo:''};
  $scope.user = Auth.user;
  $scope.filter = false;  
  $scope.error = 'Gebruik gepaste taal!';
  $scope.beschikbaarheid = '';

  $scope.currentImage = $scope.fotos[0];
    
  $scope.setCurrentImage = function(foto) {
    $scope.currentImage = foto;
  };

  $scope.change = function(){
    $location.path('/vakantie/change/' + $routeParams.vakantieId);
  };
    $scope.go = function(){

    $location.path('/inschrijven/' + $routeParams.vakantieId);

};
  $scope.openLightboxModal = function (index) {
    Lightbox.openModal($scope.fotos, index);
  };

$scope.calculateAge = function calculateAge(birthday) { // birthday is a date
    var datum = new Date(birthday); 
    var ageDifMs = Date.now() - datum.getTime();
    var ageDate = new Date(ageDifMs); // miliseconds from epoch
    return Math.abs(ageDate.getUTCFullYear() - 1970);
};
var beschikbaar = function(){
  var ingeschreven = Vakantie.getInschrijvingen($routeParams.vakantieId);
  ingeschreven.$loaded(function(aantal)
  {
    $scope.vakantie.$loaded(function(vak)
    {
      if(aantal.length < vak.maxAantal)
    {
      $scope.beschikbaarheid = true;
    }
    else
    {
      $scope.beschikbaarheid = false;
    }
  });
    
  });
}
beschikbaar();

  console.log($scope.fotos);
  //var test = $scope.vakantie.fotos[0];
  //console.log(test);


  $scope.verwijderVakantie = function() {
    $location.path('/');
    return Vakantie.delete($scope.vakantie);
  };
  $scope.share = function(){

    console.dir($scope.vakantie.fotos);
    FB.ui(
    {
        method: 'feed',
        name: 'Joetz West-Vlaanderen',
        link: 'localhost:9000/#/vakantie/' + $scope.vakantie.$id,
        picture: 'http://i.imgur.com/zXMUwu8.png',
        //picture: $scope.vakantie.fotos + '.0',
        caption: $scope.vakantie.naam,
        description: $scope.vakantie.promoTekst,
        message: ''
    });
  }
  

  $scope.voegCommentToe = function() {
    $scope.creator.username = $scope.user.profile.username;
    $scope.creator.photo = $scope.user.profile.md5_hash;
   	$scope.comment.creator = $scope.creator;
   	if(!filter($scope.comment))
   	{
  		Vakantie.comment($scope.comment, $routeParams.vakantieId);
  		$scope.filter=false;
      $scope.comment = null;
  	}	
  	else
  	{
  		$scope.filter=true;
  	}
  };

  $scope.toggle_visibility = function(id) {
  var e = document.getElementById(id);
  if(e.style.display == 'block')
     e.style.display = 'none';
  else
     e.style.display = 'block';
  };

  $scope.admin = function(role){
    return role === '99';
    };

  function filter(comment)
  {
  	var subcomment = comment.tekst.split(' ');
  	var notAllowedWords = ['faggot','anal','anus','arse','ass','ballsack','balls','bastard','bitch','biatch','bloody','blowjob','bollock','bollok','boner','boob','bugger','bum','butt','buttplug','clitoris','cock','coon','crap','cunt','damn','dick','dildo','dyke','fag','feck','fellate','fellatio','felching','fuck','fudgepacker','fudge packer','flange','Goddamn','hell','homo','jerk','jizz','knobend','labia','lmao','lmfao','muff','nigger','nigga','omg','penis','piss','poop','prick','pube','pussy','queer','scrotum','sex','shit','sh1t','slut','smegma','spunk','tit','tosser','turd','twat','vagina','wank','whore','wtf'];
  	var index;
  	var bool;
  	for(var i = 0; i < subcomment.length; i++)
  	{
  		for(var j = 0; j < notAllowedWords.length; j++)
  		{
  		index = subcomment[i].localeCompare(notAllowedWords[j]);
  		if(index === 0)
  		{
  			 return true;
  		}
  		else{ 
  			bool = false;
  		}
  	}	
  	}
  	return bool;
  };
  });
});