/**
 * Tikron javascript utilities. Copyright 2015 by Titus Kruse
 */

function TikronUtil(options) {

	var _root;
	var _cfg;

  this.config = $.extend({
  }, options);

	this.init = function() {
		_root = this;
		_cfg = this.config;
//		console.log(_cfg);
	}

	/**
	 * Substitute placeholders with string values. A placeholder consists of two braces including an index number like {0}. 
	 * 
	 * @param {String}
	 *          str The string containing the placeholders
	 * @param {Array}
	 *          arr The array of values to substitute
	 */
	this.substitute = function(str, arr) {
		var i, pattern, re, n = arr.length;
		for (i = 0; i < n; i++) {
			pattern = '\\{' + i + '\\}';
			re = new RegExp(pattern, 'g');
			str = str.replace(re, arr[i]);
		}
		return str;
	}
	
	/**
	 * Enable or disable submit button of the given form.
	 * 
	 * @param jQuery form element.
	 * @param Enables the button, if set true. Disables it therwise. 
	 */
	this.toggleSubmitButton = function($form, enable) {
		if (enable) {
			$form.find('button[type=submit]').removeAttr('disabled');
		} else {
			$form.find('button[type=submit]').attr('disabled', 'disabled');
		}
	}
	
	/**
	 * Reset all error information on give form.
	 * 
	 * @param jQuery form element.
	 */
	this.resetErrors = function($form) {
		$form.find('ul.globalMsg').empty();
		$form.find('div.form-control').removeClass('error');
		$form.find('span.error').empty();
	}
	
	/**
	 * Show error information passed by ErrorResponse on the given form.
	 * 
	 * @param jQuery form element.
	 * @param response Ajax error response of java type ErrorResponse.  
	 */
	this.showErrors = function($form, response) {
		// Handle global errors
		for (var i = 0; i < response.globalErrors.length; i++) {
			_root.showGlobalMsg($form, response.globalErrors[i].message, 'error');
		}
		// Handle field errors
		for (var i = 0; i < response.fieldErrors.length; i++) {
			var error = response.fieldErrors[i];
			var $controlGroup = $form.find('div.form-control[id="' + error.field + '"]');
			$controlGroup.addClass('error');
			$controlGroup.find('span.error').html(error.message);
		}
	}
	
	/**
	 * Shows a message in the global message box of the given form.
	 * 
	 * @param jQuery form element.
	 * @param message The message text to show.
	 * @param clazz The CSS class to use for the added LI element. 
	 */
	this.showGlobalMsg = function($form, message, clazz) {
		var $globalErrors = $form.find('ul.globalMsg');
		$globalErrors.append($('<li>', {'class': clazz, html: message}));
	}
	
	/**
	 * Detect (decides) for mobile window size.
	 * 
	 * @return true, if mobile view port.
	 */
	this.viewPortMobile = function() {
		return window.innerWidth < 768;
	}
	
	this.bindColorbox = function(txt) {
		if (!_root.viewPortMobile()) {
			$('a.storyImage').colorbox({
				maxWidth: '90%',
				photo:true, 
				rel:'storyImage', 
				current: txt.labelCurrent, 
				title: function(){return $(this).attr('data-caption');}
			});
		} else {
			$('a.storyImage').on('click', function(e){e.preventDefault();});
			$('a.storyImage').removeAttr('title');
			$('a.storyImage').css('cursor', 'auto');
		}
	}

};

var tikronUtil = new TikronUtil();

$(document).ready(function() {
	tikronUtil.init();
});
