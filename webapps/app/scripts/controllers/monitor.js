'use strict';
/*global app:true*/
app.controller('MonitorCtrl', function ($scope, $location, Profile, Auth) {
	$scope.signedIn = Auth.signedIn;
	$scope.monitor = function(role){
		return role === '20';
	};
});