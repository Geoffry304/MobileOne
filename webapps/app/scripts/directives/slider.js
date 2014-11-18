'use strict';
/*global app:true, $: true*/
app.directive('slider', function () {
	return{
		link: function(scope, el, attr){
			$(function(){
				new $JssorSlider$('slider1_container', {
					$AutoPlay: true
				});
			});
		}
	};
});