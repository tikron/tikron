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
	 * Collect input field values and return them as array of strings.
	 * 
	 * @param jQuery form element.
	 * @return An array of field values.
	 */
	this.collectFormData = function(form) {
		var fields = form.find('input, select, textarea');
		var data = {};
		for (var i = 0; i < fields.length; i++) {
			var $item = $(fields[i]);
			var value = $item.val();
			data[$item.attr('name')] = $item.val();
		}
		return data;
	}
	
	/**
	 * Reset all error information on give form.
	 * 
	 * @param jQuery form element.
	 */
	this.resetErrors = function(form) {
		form.find('ul.globalMsg').empty();
		form.find('dl').removeClass('error');
		form.find('span.error').empty();
	}
	
	/**
	 * Show error information passed by ErrorResponse on the given form.
	 * 
	 * @param jQuery form element.
	 * @param response Ajax error response of java type ErrorResponse.  
	 */
	this.showErrors = function(form, response) {
		// Handle global errors
		for (var i = 0; i < response.globalErrors.length; i++) {
			_root.showGlobalMsg(form, response.globalErrors[i].message, 'error');
		}
		// Handle field errors
		for (var i = 0; i < response.fieldErrors.length; i++) {
			var error = response.fieldErrors[i];
			var $controlGroup = form.find('dl[id="' + error.field + '"]');
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
	this.showGlobalMsg = function(form, message, clazz) {
		var $globalErrors = form.find('ul.globalMsg');
		$globalErrors.append($('<li>', {'class': clazz, html: message}));
	}

};

var tikronUtil = new TikronUtil();

$(document).ready(function() {
	tikronUtil.init();
});
