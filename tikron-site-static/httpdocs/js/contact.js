/**
 * Tikron javascript module for handling of so called rating stars. 
 * 
 * Copyright 2015 by Titus Kruse
 */

function TikronContact(options) {

	var _root;
	var _cfg;
	var _ui;

  this.config = $.extend({
  	'reloadCaptcha': function(){},
    'msg': {}
  }, options);

	this.init = function() {
		_root = this;
		_cfg = this.config;
		_ui = {
				form: $('form#contact')
		};
		_bindUIActions();
	}

	_bindUIActions = function() {
		_ui.form.bind('submit', function(e) {
			var url = _ui.form.attr('action');
			// Collect entered data from form fields
			var data = tikronUtil.collectFormData(_ui.form);
			// Post data with URL request and JSON reponse
			$.post(
				url,
				data,
				function(response) {
					tikronUtil.resetErrors(_ui.form);
					_cfg.reloadCaptcha();
					if (response.status == 'ERROR') {
						tikronUtil.showErrors(_ui.form, response);
					} else {
						// Handle success
						tikronUtil.showGlobalMsg(_ui.form, response.message, 'info');
						_ui.form.trigger('reset');
					}
//					var $globalErrors = _ui.form.find('ul#globalErrors');
//					_ui.form.tooltip({items: _ui.form, content: 'Hossa!'});
//					_ui.form.tooltip('open');
				},
				'json'
			);
			e.preventDefault();
			return false;
		});
	}

}
