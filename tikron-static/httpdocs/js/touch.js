/**
 * Tikron touch device handler using hammer.js. Copyright 2016 by Titus Kruse
 */

var panDistance = 0;

function bindTouchHandler(settings) {
	var elPicturePan = document.getElementById(settings.elementId);
	var panThreshold = elPicturePan.offsetWidth / 100 * 20;
	
	var mc = new Hammer(elPicturePan);
	mc.on("panleft panright panstart panend", function(ev) {
		if (ev.type == 'panstart') {
			panDistance = 0;
		} else if (ev.type == 'panright') {
			panDistance = Math.max(ev.deltaX, panDistance);
		} else if (ev.type == 'panleft') {
			panDistance = Math.min(ev.deltaX, panDistance);
		} else if (ev.type == 'panend') {
			if (panDistance > panThreshold) {
				var elNav = elPicturePan.getElementsByClassName('nav-left');
				if (elNav.length > 0)	elNav[0].click();
			} else if (panDistance < -panThreshold) {
				var elNav = elPicturePan.getElementsByClassName('nav-right');
				if (elNav.length > 0) elNav[0].click();
			}
		}
	});
}