'use strict';
/*global app:true*/
app.controller('BeheerderCtrl', function ($scope, $location, Auth, Vakantie) {
	$scope.user = Auth.user;
	$scope.signedIn = Auth.signedIn;
	$scope.vakanties = Vakantie.all;
	$scope.adminvak = 'views/adminVakanties.html';
	$scope.admin = function(role){
		return role === '99';
	};

});
