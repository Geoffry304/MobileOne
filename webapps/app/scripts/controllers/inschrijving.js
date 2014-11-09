'use strict';
/*global app:true*/
app.controller('InschrijvingCtrl', function ($scope, $location, Inschrijving, Auth) {
	$scope.inschrijving = {aantal: '', naam: '', voornaam: ''};
	$scope.alerts = [];
	$scope.signedIn = Auth.signedIn;
	$scope.logout = Auth.logout;
	$scope.user = Auth.user;

	$scope.submitInschrijving = function ()
	{
		$scope.inschrijving.creator = $scope.user.profile.username;
		$scope.inschrijving.creatorUID = $scope.user.uid;
		Inschrijving.create($scope.inschrijving).then(function (ref){
			$scope.alerts.push({ type: 'success', msg: 'Well done! You successfully read this important alert message.' });
		});
	};
});
