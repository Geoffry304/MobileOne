'use strict';
/*global app:true*/
app.controller('InschrijvingCtrl', function ($scope, $location, $routeParams, Inschrijving, Profile, Auth, Vakantie) {
	$scope.inschrijving = {aantal: '', naam: '', voornaam: ''};
	$scope.alerts = [];
	$scope.alert = '';
	$scope.signedIn = Auth.signedIn;
	$scope.logout = Auth.logout;
	$scope.user = Auth.user;
	$scope.voegToe = 'Voeg toe';
	$scope.kinderen = Profile.getKinderen($scope.user.uid);
	$scope.Kind = '';

	$scope.mijnKinderen = [];
	var counter = 0;

	$scope.submitInschrijving = function ()
	{
		/*if($scope.Kind.vakantieId == undefined)
		{*/
			$scope.Inschrijving = Profile.getInschrijving($scope.user.uid, $scope.Kind.$id);
			$scope.Inschrijving.$loaded(function(vakanties)
			{
			if($scope.Inschrijving.inschrijving == undefined)
			{
				$scope.alert ='';
				Vakantie.Inschrijving($routeParams.vakantieId, $scope.Kind);
				return Profile.Inschrijving($scope.user.uid, $scope.Kind.$id, $scope.Kind);
			}
			else{
				$scope.alert = 'Dit kind is al ingeschreven voor een vakantie.';
			}
			});
			

		/*}
		else
		{
			$scope.alert = '';
		}*/
		

		/*if ($scope.voegToe == 'Voeg toe') {
			//if ($scope.inschrijving.aantal <= 0 || $scope.inschrijving.aantal >= 11){
				$scope.alerts = [];
				$scope.alerts.push({ type: 'error', msg: 'een verkeerd aantal toegevoegd.' });
				$scope.kinderen = Profile.getKinderen($scope.user.uid);
			//}*/
			/*else{
				for (var i = 0; i < $scope.inschrijving.aantal; i += 1){
					$scope.kinderen.push({naam: '', voornaam: ''});
				}
				$scope.voegToe = 'inschrijving doorsturen';
			}*/
		/*}
		else {
			$scope.inschrijving.creator = $scope.user.profile.username;
			$scope.inschrijving.creatorUID = $scope.user.uid;
			for (var teller = 0; teller < $scope.kinderen.length; teller += 1){
				$scope.schrijfKindIn(teller);
			}
			Inschrijving.create($scope.inschrijving).then(function (ref){
				$scope.alerts.push({ type: 'success', msg: 'Well done! You successfully read this important alert message.' });
			});
		}*/

	};
	$scope.schrijfKindIn = function(teller){
		Profile.createKind($scope.user.uid, $scope.kinderen[teller]).then(function (ref){
			$scope.alerts.push({ type: 'success', msg: 'Well done! You successfully read this important alert message.' });
		});
	};
});
