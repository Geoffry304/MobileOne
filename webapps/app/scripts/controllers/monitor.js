'use strict';
/*global app:true*/
app.controller('MonitorCtrl', function ($scope, $location, Auth) {
	$scope.user = Auth.user;
	$scope.evenementTitel = '';
	$scope.signedIn = Auth.signedIn;
	$scope.monitor = function(role){
		return role === '20' || role === '99';
	};
});