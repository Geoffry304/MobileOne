'use strict';


angular.module('webappsApp')
	.controller('LoginCtrl', function($scope, $firebase, $firebaseSimpleLogin){
 var ref = new Firebase('https://mobileone.firebaseIO.com');

 $scope.auth = $firebaseSimpleLogin(ref);

 $scope.dummySignin= function() {
 	console.log('signing in...');

 	$scope.auth.$login('password', {
        email: 'geoffry304@live.be',
        password: 'azerty12'
      }).then(function(user) {
        console.log('user: ', user);
      }, function(error) {
        console.log('error: ', error);
      });
    };
});