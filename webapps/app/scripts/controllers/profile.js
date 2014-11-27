'use strict';
/*global app:true*/
app.controller('ProfileCtrl', function($scope, $routeParams, Profile) {
	var uid = $routeParams.userId;
	$scope.kind = {};
	$scope.kinderen = Profile.getKinderen(uid);
	$scope.profile = Profile.get(uid);
	

	$scope.submitProfiel = function () {
		console.log($scope.profile.username);
		Profile.update(uid,$scope.profile);
	};
	$scope.submitKind = function () {

		if (document.getElementById('anders').checked = true){
			$scope.kind.straat = $scope.profile.straat;
			$scope.kind.nummer = $scope.profile.nummer;
			$scope.kind.postcode = $scope.profile.postcode;
			$scope.kind.gemeente = $scope.profile.gemeente;
			Profile.createKind(uid,$scope.kind);
		}else
		{
			Profile.createKind(uid,$scope.kind);
		}
		
		//console.log($scope.kinderen[0]);

	};




});