'use strict';
/*global app:true*/
app.controller('ActiviteitCtrl', function ($scope, $routeParams, $location, Activiteit, Auth) {

  $scope.activiteiten = Activiteit.all;
  $scope.user = Auth.user;
  $scope.eventSources = [];

  $scope.uiConfig = {
      calendar:{
        height: 450,
        editable: true,
        header:{
          left: 'month agendaWeek agendaDay',
          center: 'title',
          right: 'today prev,next'
        },
        dayClick: $scope.alertEventOnClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize
      }
    };
/* event source that pulls from google.com */
    $scope.eventSource = {
            url: "http://www.google.com/calendar/feeds/eu__nl%40holiday.calendar.google.com/public/basic",
            className: 'gcal-event',           // an option!
            currentTimezone: 'America/Chicago' // an option!
    };



if($routeParams.activiteitId !== undefined)
{
	$scope.activiteit = Activiteit.get($routeParams.activiteitId);
	$scope.Activiteit = function() {
    return Activiteit.update($routeParams.activiteitId, $scope.activiteit);
  };

}
else
{
	$scope.activiteit = {naam:'', promoTekst:'',adres:{straat:'', nr:'', postcode:'', gemeente:'', long:'', lat:''},prijs:'', periode:{van:'', tot:''}, soort: '', maxAantal: ''};
	$scope.Activiteit = function() {

    Activiteit.create($scope.activiteit);
  };
}

$scope.go = function(activiteitId){
	if(activiteitId !== undefined)
	{
    $location.path('/activiteit/' + activiteitId);
  }
  else
  {
    console.log('got undefined');
  }
};
$scope.admin = function(role){
  return role === '99';
};

});
