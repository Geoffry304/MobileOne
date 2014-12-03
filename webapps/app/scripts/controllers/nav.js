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
    $scope.register = function() {
    Auth.register($scope.user).then(function(user) {
      return Auth.login($scope.user).then(function() {
        return Auth.createProfile(user);
      })
    }, function(error) {
      $scope.error = error.toString();
      console.log(error.toString());
    });
  };
  $scope.user = Auth.user;

    $scope.admin = function(role){
    return role === '99';
    };

    $scope.loginWithFacebook = function() {

    Auth.facebookLogin().then(function(){
        console.log(Auth.$value);  
        Auth.createFbProfile(Auth.user);
      
      //Auth.createFbProfile(Auth.user);
      $location.path('/');
    });
  };  
 
});