'use strict';
/*global app:true*/
app.controller('BeheerderCtrl', function ($scope, $location, Auth) {
	$scope.user = Auth.user;
	$scope.signedIn = Auth.signedIn
	$scope.admin = function(role){
    return role === '99';
    };

});
