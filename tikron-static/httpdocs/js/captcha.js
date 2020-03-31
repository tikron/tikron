/**
 * Tikron Captcha Image Utils. Copyright 2015 by Titus Kruse
 */

function TikronCaptcha(options) {

	var _root;
	var _cfg;

  this.config = $.extend({
  	'captchaImageUrl' : '#'
  }, options);

	this.init = function() {
		_root = this;
		_cfg = this.config;
		_ui = {
				groupImage: $('.captchaImage'),
				groupCode: $('.captchaCode')
		};
		_bindUIActions();
	}
	
	this.reloadImage = function() {
		_reloadImage();
	}

	var _bindUIActions = function() {
		_bindCaptchaImages();
	}
	
	/**
	 * Requests a new captcha code image on click event.
	 */
	var _bindCaptchaImages = function() {
		_ui.groupImage.find('a, img').click(function() {
			_reloadImage();
		})
	}
	
	var _reloadImage = function() {
		var requestUrl = _cfg.captchaImageUrl + Math.floor(Math.random() * 1000);
		_ui.groupImage.find('img').attr('src', requestUrl);
		_ui.groupCode.find('input').val('');
	}
	
};
