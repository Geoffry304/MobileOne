'use strict';
/*global app:true*/
app.controller('NavCtrl', function ($scope, $location, Auth) {  $scope.signedIn = Auth.signedIn;
  $scope.logout = Auth.logout;

  $scope.login = function() {
    console.log('Test');
    Auth.login($scope.user).then($scope.error = '', function (error) {
      $scope.error = error.toString();
      console.log(error.toString());
    });
  };
  $scope.user = Auth.user;

    $scope.admin = function(role){
    return role === '99';
    };

    $scope.loginWithFacebook = function() {
    console.log('Test fb');
    Auth.facebookLogin().then(function(){
      
        Auth.createFbProfile(Auth.user);
      
      //Auth.createFbProfile(Auth.user);
      $location.path('/');
    });
  };   
});