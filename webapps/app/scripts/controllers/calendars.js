'use strict';
/*global app:true*/
app.controller('CalendarCtrl', function ($scope, $compile, uiCalendarConfig, Activiteit, Auth) {

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
    $scope.activiteit;
    $scope.user = Auth.user;

    $scope.change = function() {

        $scope.activiteiten = Activiteit.all;
        $scope.activiteiten.$loaded(function()
        {
            for (var i = 0; i < $scope.activiteiten.length; i++){
                $scope.events.push({
                    id: $scope.activiteiten[i].$id,
                    title: $scope.activiteiten[i].naam,
                    start: $scope.activiteiten[i].periode.van,
                    end: $scope.activiteiten[i].periode.tot,
                    className: ['openSesame'],
                    color: 'red',
                    textColor: 'black'
                });
            }});

    };

    /* event source that pulls from google.com */
    $scope.eventSource = {
      url: "http://www.google.com/calendar/feeds/eu__nl%40holiday.calendar.google.com/public/basic",
            className: 'gcal-event',           // an option!
            color: 'red',
            currentTimezone: 'Europe/Brussels' // an option!
        };
        /* event source that contains custom events on the scope */
        $scope.events = [];

        /* event source that calls a function on every view switch */
        $scope.eventsF = function (start, end, timezone, callback) {
        	var s = new Date(start).getTime() / 1000;
        	var e = new Date(end).getTime() / 1000;
        	var m = new Date(start).getMonth();
        	var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
            $scope.change();
        	callback(events);
        };



        /* alert on eventClick */
        $scope.alertOnEventClick = function( date, jsEvent, view){
            $('#activiteitModal').modal('show');
            $scope.activiteit = Activiteit.get(date.id);
        	$scope.alertMessage = (date.id + ' was clicked ');

             $scope.schrijfIn = function(){
                //console.log($scope.user);
                Activiteit.Inschrijving(date.id, $scope.user.uid);
        };
        };
        /* alert on Drop */
        $scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
        	$scope.alertMessage = ('Event Droped to make dayDelta ' + delta);
        };
        /* alert on Resize */
        $scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
        	$scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
        };
        /* add and removes an event source of choice */
        $scope.addRemoveEventSource = function(sources,source) {
        	var canAdd = 0;
        	angular.forEach(sources,function(value, key){
        		if(sources[key] === source){
        			sources.splice(key,1);
        			canAdd = 1;
        		}
        	});
        	if(canAdd === 0){
        		sources.push(source);
        	}
        };
        /* add custom event*/
        $scope.addEvent = function() {
        	$scope.events.push({
        		title: 'Open Sesame',
        		start: new Date(y, m, 28),
        		end: new Date(y, m, 29),
        		className: ['openSesame']
        	});
        };
        /* remove event */
        $scope.remove = function(index) {
        	$scope.events.splice(index,1);
        };
        /* Change View */
        $scope.changeView = function(view,calendar) {
        	uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
            
        };
        /* Change View */
        $scope.renderCalender = function(calendar) {
        	if(uiCalendarConfig.calendars[calendar]){
        		uiCalendarConfig.calendars[calendar].fullCalendar('render');
                
        	}
        };
        /* Render Tooltip */
        $scope.eventRender = function( event, element, view ) { 
        	element.attr({'tooltip': event.title,
        		'tooltip-append-to-body': true});
        	$compile(element)($scope);
        };
        /* config object */
        $scope.uiConfig = {
        	calendar:{
        		height: 450,
        		editable: false,
        		header:{
        			left: 'title',
        			center: '',
        			right: 'today prev,next'
        		},
        		eventClick: $scope.alertOnEventClick,
        		eventDrop: $scope.alertOnDrop,
        		eventResize: $scope.alertOnResize,
        		eventRender: $scope.eventRender
        	}
        };

        /* event sources array*/
        $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];
    });