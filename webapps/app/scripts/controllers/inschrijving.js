'use strict';
/*global app:true*/
app.controller('InschrijvingCtrl', function ($scope, $location, $routeParams, Inschrijving, Profile, Auth, Vakantie) {
	$scope.inschrijving = {aantal: '', naam: '', voornaam: ''};
	$scope.alerts = [];
	$scope.alert = '';
	$scope.alert2= '';
	$scope.signedIn = Auth.signedIn;
	$scope.logout = Auth.logout;
	$scope.user = Auth.user;
	$scope.voegToe = 'Schrijf in';
	$scope.kinderen = Profile.getKinderen($scope.user.uid);
	$scope.Kind = '';
	var uid = $scope.user.uid;
	$scope.myKinderen = {};
	$scope.profile = Profile.get(uid);
	$scope.Toggle = "Voeg een kind toe";
	$scope.toevoegen = false;

	$scope.mijnKinderen = [];
	var counter = 0;

	$scope.ToggleToevoegen = function(){
		$scope.toevoegen = $scope.toevoegen != true;
		if($scope.toevoegen != true)
		{
			$scope.Toggle = "Voeg een kind toe";
			$scope.alert = '';
			$scope.alert2= '';
		}
		else{
			$scope.Toggle = "Terug naar inschrijving";
			$scope.alert = '';
			$scope.alert2= '';
		}
	}
	$scope.submitInschrijving = function ()
	{
		/*if($scope.Kind.vakantieId == undefined)
		{*/
			$scope.Inschrijving = Profile.getInschrijving($scope.user.uid, $scope.Kind.$id);
			$scope.Inschrijving.$loaded(function(vakanties)
			{
			if($scope.Inschrijving.inschrijving == undefined)
			{
				$scope.alert2 ='Inschrijving succesvol.';
				Vakantie.Inschrijving($routeParams.vakantieId, $scope.Kind);
				return Profile.Inschrijving($scope.user.uid, $scope.Kind.$id, $scope.Kind);
			}
			else{
				$scope.alert = 'Dit kind is al ingeschreven voor een vakantie.';
			}
			});
	};

	$scope.schrijfKindIn = function(teller){
		Profile.createKind($scope.user.uid, $scope.kinderen[teller]).then(function (ref){
			$scope.alerts.push({ type: 'success', msg: 'Well done! You successfully read this important alert message.' });
		});
	};

	$scope.submitKind = function () 
	{
		if (document.getElementById('anders').checked != true){
			$scope.myKinderen.straat = $scope.profile.straat;
			$scope.myKinderen.nummer = $scope.profile.nummer;
			$scope.myKinderen.postcode = $scope.profile.postcode;
			$scope.myKinderen.gemeente = $scope.profile.gemeente;
			Profile.createKind(uid,$scope.myKinderen);
			$scope.alert ="Kind is toegevoegd.";
			$scope.myKinderen = null;
		}else
		{
			Profile.createKind(uid,$scope.myKinderen);
			$scope.alert ="Kind is toegevoegd.";
			$scope.myKinderen = null;
		}
	};
});
