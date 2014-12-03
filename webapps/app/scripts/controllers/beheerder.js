'use strict';
/*global app:true*/
app.controller('BeheerderCtrl', function ($scope, $location, Auth, Vakantie, Profile) {
	$scope.user = Auth.user;
	$scope.signedIn = Auth.signedIn;
	$scope.vakanties = Vakantie.all;
  $scope.gebruikers = Profile.all;
	$scope.adminvak = 'views/adminVakanties.html';
  $scope.admingebruiker = 'views/adminGebruikers.html';
	$scope.admin = function(role){
		return role === '99';
	};

$scope.toggle_visibility = function(id, vakantieId) {
	$scope.vakantieId = vakantieId;
  var e = document.getElementById(id);
  if(e.style.display == 'block')
     e.style.display = 'none';
  else
     e.style.display = 'block';
  };
$scope.verwijderVakantie = function() {
    $location.path('/');
    return Vakantie.delete(Vakantie.get($scope.vakantieId));
  };

/* $scope.toggle_visibility = function(id, gebruikerId) {
  $scope.gebruikerId = gebruikerId;
  var e = document.getElementById(id);
  if(e.style.display == 'block')
     e.style.display = 'none';
  else
     e.style.display = 'block';
  };*/
/*$scope.verwijderVakantie = function() {
    $location.path('/');
    return Profile.delete(Profile.get($scope.gebruikerId));
  };*/

});
