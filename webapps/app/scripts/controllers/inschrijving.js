'use strict';
/*global app:true*/
app.controller('InschrijvingCtrl', function ($scope, $location, Auth, user, Inschrijving) {
	$scope.inschrijving = {aantal: '', naam: '', voornaam: ''};
	$scope.alerts = [];
	$scope.user = Auth.user;
	$scope.inschrijving.creator = $scope.user.profile.username;
	$scope.inschrijving.creatorUID = $scope.user.uid;
	$scope.submitInschrijving = function ()
	{
		Inschrijving.create($scope.inschrijving).then(function (ref){
			$scope.alerts.push({ type: 'success', msg: 'Well done! You successfully read this important alert message.' });
		});
	};
});
