'use strict';
/*global app:true*/
app.controller('InschrijvingCtrl', function ($scope, $location, Auth, user, Inschrijving) {
	$scope.inschrijving = {aantal: '', naam: '', voornaam: ''};
	$scope.submitInschrijving = function ()
	{
		Inschrijving.create($scope.inschrijving).then(function (ref){
			
		});
	};
});
