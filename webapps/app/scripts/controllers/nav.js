'use strict';
/*global app:true*/
app.controller('NavCtrl', function ($scope, $location, Auth) {  $scope.signedIn = Auth.signedIn;
  $scope.logout = Auth.logout;
  $scope.errormessage= '';

  $scope.login = function() {
    console.log('Test');
    Auth.login($scope.user).then($scope.error = '', function (error) {
      $scope.error = error.toString();
      console.log(error.toString());
    });
  };
  $scope.register = function() {
    if($scope.user.confirm == $scope.user.password)
    {
      $scope.errormessage='';
    Auth.register($scope.user).then(function(user) {
      return Auth.login($scope.user).then(function() {
        return Auth.createProfile(user);
      })
    }, function(error) {
      $scope.error = error.toString();
      console.log(error.toString());
    });
  }
  else{
    $scope.errormessage = "het wachtwoord komt niet overeen.";
  }
  };

  $scope.resetPass = function(){
    //Moet nog een parameter bij, maar eeerst de layout ervoor.
    Auth.resetPass();
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