/**
 * Tikron base javascript. Copyright 2010 by Titus Kruse
 */

function Tikron(options) {

	var _root;
	var _cfg;
	var _ui;
	var _text = {
			email: {contact: {address: 'mail@tikron.de', subject: 'Kontaktanfrage'},
				owner: {address: 'webmaster@tikron.de', subject: 'Kontaktanfrage'}
			},
			address: {headline: '"Tikron" wird von {0} vertreten. Die Postanschrift lautet:'},
			postal : {name: 'Titus Kruse', address: 'Birnweg 2', city: '22335 Hamburg', country: 'Germany'},
			phone: {label: '+49 40 59360711', number: '+494059360711'}
	};

  this.config = $.extend({
  }, options);

	this.init = function() {
		_root = this;
		_cfg = this.config;
		_ui = {
				goup: $('#goup'),
				spinner: _createDefaultSpinner()
		};
		_consoleWrapper();
		_updateUI();
		_bindUIActions();
//		console.log(_cfg);
	}
	
	this.bindEventHandler = function(container) {
		_bindAjaxLinks(container);
		_bindAjaxDefaultSpinner(container);
		_bindScroller(container);
	}
	
	var _updateUI = function() {
		$('.contact-email').attr('href', _getEmailLink('contact')).html(_getEmailText('contact'));
		$('.site-owner.address').append(_getAddressHtml());
		$('.site-owner.email').attr('href', _getEmailLink('owner')).html(_getEmailText('owner'));
		$('.site-owner.phone').attr('href', _getPhoneLink()).html(_text.phone.label);
	}

	var _bindUIActions = function() {
		// "Go Up" scrolling button
		_bindGoUpButton();
		// Form elements
		_bindForms();
		// Bind ajax request for Ajax loading links
		_bindAjaxLinks();
		// Bind default ajax spinner and scroller
		_bindAjaxDefaultSpinner();
		_bindScroller();
		// Tooltips
		$(document).tooltip({track: true, show: {delay: 500}});
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
	var _getEmailLink = function(type) {
		return 'mailto:' + _text.email[type].address + '?subject=' + _text.email[type].subject;
	}

	/**
	 * Returns the contact email address text.
	 * 
	 * @returns {String} The email address.
	 */
	var _getEmailText = function(type) {
		return _text.email[type].address;
	}

	/**
	 * Returns the phone number link.
	 * 
	 * @returns {String} The phone number link.
	 */
	var _getPhoneLink = function() {
		return 'tel:' + _text.phone.number;
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
		return _text.postal.name + '<br />' + _text.postal.address + '<br />' + _text.postal.city + '<br />' + _text.postal.country;
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
	 * Bind ajax request for Ajax loading links
	 */
	var _bindAjaxLinks = function(container) {
		$('a.ajax', container).on('click', function(e) {
			e.preventDefault();
			$.ajax({
				url: $(this).attr('href'),
				context: this,
				dataType: 'html',
				success: function(response) {
					// Try to obtain response target from data attribute
					var $target = $($(this).data('response-target'));
					if ($target.length == 0) {
						// console.debug('No data-response-target specified. Using default.');
						$target = $(this);
					}
					// Render response and bind ajax events again
					$target.html(response);
					_root.bindEventHandler($target);
				}
			});
		});
	}
	
	/**
	 * Binds default spinner for all Ajax requests.
	 */
	var _bindAjaxDefaultSpinner = function(container) {
		$('a.ajax', container).ajaxSend(function(e) {
			_ui.spinner.spin(e.target);
		})
		$('a.ajax', container).ajaxComplete(function(e) {
			_ui.spinner.stop();
		})
		$('form.ajax', container).ajaxSend(function(e) {
			_ui.spinner.spin(e.target.querySelectorAll("button[type=submit]")[0]);
		})
		$('form.ajax', container).ajaxComplete(function(e) {
			_ui.spinner.stop();
		})
	}
	
	var _createDefaultSpinner = function() {
		// http://spin.js.org/

		var opts = {
			ines: 13, // The number of lines to draw
			length: 8, // The length of each line
			width: 6, // The line thickness
			radius: 12, // The radius of the inner circle
			scale: 1, // Scales overall size of the spinner
			corners: 1, // Corner roundness (0..1)
			color: '#ff8000', // CSS color or array of colors
			fadeColor: 'transparent', // CSS color or array of colors
			opacity: 0.25, // Opacity of the lines
			rotate: 0, // The rotation offset
			direction: 1, // 1: clockwise, -1: counterclockwise
			speed: 0.7, // Rounds per second
			trail: 75, // Afterglow percentage
			fps: 20, // Frames per second when using setTimeout() as a fallback in IE 9
			zIndex: 2e9, // The z-index (defaults to 2000000000)
		  className: 'spinner', // The CSS class to assign to the spinner
		  top: '50%', // Top position relative to parent
		  left: '50%', // Left position relative to parent
		  shadow: 'none', // Box-shadow for the lines
		  position: 'absolute' // Element positioning
		};

		return new Spinner(opts);
	}
	
	/**
	 * Enables smooth scrolling on to anchors on page 
	 */
	var _bindScroller = function(container) {
	  $("a.smooth", container).on('click', function(event) {
	    // Make sure this.hash has a value before overriding default behavior
	    if (this.hash !== "") {
	      // Prevent default anchor click behavior
	      event.preventDefault();

	      // Store hash
	      var hash = this.hash;

	      // Using jQuery's animate() method to add smooth page scroll
	      // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
	      $('html, body').animate({
	        scrollTop: $(hash).offset().top
	      }, 800, function(){
	   
	        // Add hash (#) to URL when done scrolling (default click behavior)
	        window.location.hash = hash;
	      });
	    } // End if
	  });
	}
	
	/**
	 * Javascript console logger poylfill
	 */
	var _consoleWrapper = function() {
		if (!window.console) window.console = {};
		if (!window.console.error) window.console.error = function () {};
		if (!window.console.info) window.console.info = function () {};
		if (!window.console.log) window.console.log = function () {};
		if (!window.console.trace) window.console.trace = function () {};
		if (!window.console.warn) window.console.warn = function () {};
	}
};

var tikron = new Tikron();

$(document).ready(function() {
	tikron.init();
});
