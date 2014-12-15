'use strict';

/*global app:true*/

app.controller('ToonActiviteitCtrl', function ($scope, $routeParams, $location, Activiteit, Auth) {

  $scope.activiteit = Activiteit.get($routeParams.activiteitId);
  
  $scope.signedIn = Auth.signedIn;
  $scope.user = Auth.user;


  $scope.change = function(){
    $location.path('/activiteit/change/' + $routeParams.activiteitId);
  };
    $scope.go = function(){

    $location.path('/inschrijven/' + $routeParams.vakantieId);

};

  $scope.verwijderActiviteit = function() {
    $location.path('/');
    return Activiteit.delete($scope.activiteit);
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
});