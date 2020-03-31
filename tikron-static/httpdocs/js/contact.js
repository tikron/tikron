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

	var _bindUIActions = function() {
		_ui.form.bind('submit', function(e) {
			var url = $(this).attr('action');
			// Collect entered data from form fields
			var data = $(this).serialize();
			// Post data with URL request and JSON reponse
			$.ajax(url,	{
				context: this,
				data: data,
				dataType: 'json',	
				method: 'post',
				success: function(response) {
					var $form = $(this);
					tikronUtil.resetErrors($form);
					_cfg.reloadCaptcha();
					if (response.status == 'ERROR') {
						tikronUtil.showErrors($form, response);
					} else {
						// Handle success
						tikronUtil.showGlobalMsg($form, response.message, 'info');
						$form.trigger('reset');
					}
//					var $globalErrors = $form.find('ul#globalErrors');
//					$form.tooltip({items: $form, content: 'Hossa!'});
//					$form.tooltip('open');
				}
			});
			e.preventDefault();
		});
	}

}
