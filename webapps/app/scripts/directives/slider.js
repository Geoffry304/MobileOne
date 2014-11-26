'use strict';
/*global app:true, $: true*/
app.directive('slider', function () {
	function link(){
		var slider = new IdealImageSlider.Slider({
		    selector: '#slider',
		    height: 400, // Required but can be set by CSS
		    interval: 4000,
		});
		slider.start();	
	}
	return{
		link: link
	};
});


