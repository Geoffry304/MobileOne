'use strict';
/*global app:true*/
app.controller('InschrijvingCtrl', function ($scope, $location, Inschrijving, Profile, Auth) {
	$scope.inschrijving = {aantal: '', naam: '', voornaam: ''};
	$scope.alerts = [];
	$scope.signedIn = Auth.signedIn;
	$scope.logout = Auth.logout;
	$scope.user = Auth.user;
	$scope.voegToe = 'Voeg toe';
	$scope.kinderen = [];
	$scope.mijnKinderen = [];
	$scope.submitInschrijving = function ()
	{
		if ($scope.voegToe == 'Voeg toe') {
			if ($scope.inschrijving.aantal <= 0 || $scope.inschrijving.aantal >= 11){
				$scope.alerts = [];
				$scope.alerts.push({ type: 'error', msg: 'een verkeerd aantal toegevoegd.' });
				$scope.mijnKinderen = Auth.user.profile.getKinderen(Auth.user.uid);
			}
			else{
				for (var i = 0; i < $scope.inschrijving.aantal; i += 1){
					$scope.kinderen.push({naam: '', voornaam: ''});
				}
				$scope.voegToe = 'inschrijving doorsturen';
			}
		}
		else {
			$scope.inschrijving.creator = $scope.user.profile.username;
			$scope.inschrijving.creatorUID = $scope.user.uid;
			for (var teller = 0; teller < $scope.kinderen.length; teller += 1){
				$scope.schrijfKindIn(teller);
			}
			Inschrijving.create($scope.inschrijving).then(function (ref){
				$scope.alerts.push({ type: 'success', msg: 'Well done! You successfully read this important alert message.' });
			});
		}

	};
	$scope.schrijfKindIn = function(teller){
		Profile.createKind($scope.user.uid, $scope.kinderen[teller]).then(function (ref){
			$scope.alerts.push({ type: 'success', msg: 'Well done! You successfully read this important alert message.' });
		});
	};
});
