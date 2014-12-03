'use strict';
/*global app:true*/
app.controller('ProfileCtrl', function($scope, $routeParams, Profile) {
	var uid = $routeParams.userId;
	$scope.myKinderen = {};
	$scope.kinderen = Profile.getKinderen(uid);
	$scope.profile = Profile.get(uid);
	

	$scope.submitProfiel = function () {
		console.log($scope.profile.username);
		Profile.update(uid,$scope.profile);
	};
	$scope.change = function () {
		
	};
	$scope.submitKind = function () {


		if (document.getElementById('checkKind').value == ""){
			if (document.getElementById('anders').checked != true){
				$scope.myKinderen.straat = $scope.profile.straat;
				$scope.myKinderen.nummer = $scope.profile.nummer;
				$scope.myKinderen.postcode = $scope.profile.postcode;
				$scope.myKinderen.gemeente = $scope.profile.gemeente;
				Profile.createKind(uid,$scope.myKinderen);
			}else
			{
				Profile.createKind(uid,$scope.myKinderen);
			}
		}else
		{
			if (document.getElementById('anders').checked != true){
				$scope.myKinderen.straat = $scope.profile.straat;
				$scope.myKinderen.nummer = $scope.profile.nummer;
				$scope.myKinderen.postcode = $scope.profile.postcode;
				$scope.myKinderen.gemeente = $scope.profile.gemeente;
				Profile.updateKind($scope.myKinderen.$id,$scope.myKinderen, uid);
			}else
			{
				Profile.updateKind($scope.myKinderen.$id,$scope.myKinderen, uid);
			}
		}
		
		console.log(document.getElementById('checkKind').value);
		//console.log(uid);
		//console.log($scope.myKinderen.$id);
		//console.log($scope.kinderen[0]);

	};

});