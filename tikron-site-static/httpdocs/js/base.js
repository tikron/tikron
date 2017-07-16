/**
 * Tikron base javascript. Copyright 2010 by Titus Kruse
 */

function Tikron(options) {

	var _root;
	var _cfg;
	var _text = {
			email: {address: 'contact@tikron.de', subject: 'Kontaktanfrage'},
			address: {headline: '"Tikron" wird von {0} vertreten. Die Postanschrift lautet:'},
			postal : {name: 'Titus Kruse', address: 'Birnweg 2', city: '22335 Hamburg', country: 'Germany'}
	};

  this.config = $.extend({
  }, options);

	this.init = function() {
		_root = this;
		_cfg = this.config;
		_ui = {
				goup: $('#goup')
		};
		_updateUI();
		_bindUIActions();
//		console.log(_cfg);
	}
	
	var _updateUI = function() {
		$('div.address').append(_getAddressHtml());
		$('a.email_link').attr('href', _getContactEmailLink());
		$('a.email_link').html(_getContactEmailText());
	}

	var _bindUIActions = function() {
		// "Go Up" scrolling button
		_bindGoUpButton();
		// Form elements
		_bindForms();
		// Bind default ajax spinner
		_bindAjaxDefaultSpinner();
	}
	
	var _bindGoUpButton = function() {
		// https://markgoodyear.com/2013/01/scrollup-jquery-plugin/
		$.scrollUp({
			animation: 'fade',
			activeOverlay: false,
			scrollImg: {
				active: true,
				type: 'background',
				src: 'img/top.png'
			}
		}); 
	}
	
	var _bindForms = function() {
		$('form input.url').change(function() {
			_formatUrl(this);
		});
	}

	/**
	 * Returns the contact email address link.
	 * 
	 * @returns {String} The email address link.
	 */
	var _getContactEmailLink = function() {
		return 'mailto:' + _text.email.address + '?subject=' + _text.email.subject;
	}

	/**
	 * Returns the contact email address text.
	 * 
	 * @returns {String} The email address.
	 */
	var _getContactEmailText = function() {
		return _text.email.address;
	}

	/**
	 * Returns the site owner address.
	 * 
	 * @returns Postal address object.
	 */
	var _getPostalAddress = function() {
		return _text.postal;
	}

	/**
	 * Returnes the site owner info and address as HTML text.
	 * 
	 * @returns {String} The HTML code.
	 */
	var _getAddressHtml = function() {
		var pa = _getPostalAddress();
		var headline = tikronUtil.substitute(_text.address.headline, [pa.name]);
		return '<p>' + headline + '</p>' + '<address>'
				+ pa.name + '<br />' + pa.address + '<br />' + pa.city + '<br />'
				+ pa.country + '</address>';
	}

	/**
	 * Formats an URL input field value.
	 * 
	 * @param field
	 *          The input field.
	 */
	var _formatUrl = function(field) {
		var value = $(field).val();
		if (value.length > 0 && value.indexOf('http:') != 0
				&& value.indexOf('https:') != 0) {
			$(field).val('http://' + value);
		}
	}
	
	/**
	 * Binds default spinner for all Ajax requests.
	 */
	var _bindAjaxDefaultSpinner = function() {
		$(document).ajaxStart(function() {
			$('#container').spin();
		});
		$(document).ajaxStop(function() {
			$('#container').spin(false);
		});
	}
};

var tikron = new Tikron();

$(document).ready(function() {
	tikron.init();
});
