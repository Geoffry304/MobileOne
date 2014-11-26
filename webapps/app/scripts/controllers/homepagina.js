'use strict';
/*global app:true*/
app.controller('HomeCtrl', function ($scope, Vakantie) {
$scope.vakanties = Vakantie.all;
});