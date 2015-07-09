/**
 * Tikron javascript module for handling of so called rating stars. 
 * 
 * Copyright 2015 by Titus Kruse
 */

function TikronRating(options) {

	var _root;
	var _cfg;
	var _ui;

  this.config = $.extend({
    'addRatingUrl': '#',
    'msg': {}
  }, options);

	this.init = function() {
		_root = this;
		_cfg = this.config;
		_ui = {
				ratingStars: $('div.ratingStars')
		};
		_bindUIActions();
	}

	_bindUIActions = function() {
		_ui.ratingStars.each(function() {
			// Fetch unique entity ID from HTML element
			var entityId = $(this).attr('data-id');
			var ratingValue = $(this).attr('data-avarage');
			var ratingCount = $(this).attr('data-count');
			// Set current rating as text message
			var textE = $(this).find('span.text');
			textE.text(_composeRatingText(ratingCount));
			// Bind jRate to each ratingStars inner DIV
			var starsE = $(this).find('div.stars');
			starsE.jRate({
				precision : 1.0,
				backgroundColor : 'gray',
				rating : ratingValue,
				onSet : function(value) {
					_handleRating($(this), value);
				}
			});
		});
		/*
			*/
	}
	
	/**
	 * Persists new rating and override rating text with Ajax response.
	 * 
	 * @param e The DOM element of the rating event.
	 * @param value The rating value chosen by the user.
	 */
	_handleRating = function(e, value) {
		var entityId = e.parent().parent().attr('data-id');
		var requestUrl = _buildRequestUrl(entityId, value)
		$.ajax({
			url: requestUrl,
			success: function(ratingResult) {
				var resultE=_ui.ratingStars.filter('[data-id = "' + ratingResult.id + '"]');
				var textE=resultE.find('span.text');
				textE.text(_composeRatingText(ratingResult.count));
			}
		});
	}
	
	/**
	 * Builds the Ajax request for adding a rating value for an entity.
	 * 
	 * @param entityId The entity ID, of the object to be rated.
	 * @param value The rating value chosen by the user.
	 */
	_buildRequestUrl = function(entityId, value) {
		return _cfg.addRatingUrl.replace('Id=', 'Id=' + entityId).replace('ratingValue=', 'ratingValue=' + value)
	}
	
	/**
	 * Composes a message text bases on the given rating data.
	 * 
	 * @param rating The rating result.
	 * @raturn The formatted text.
	 */
	_composeRatingText = function(ratingCount) {
		if (ratingCount > 0) {
			return tikronUtil.substitute(_cfg.msg.ratingCount, [ratingCount]);
		} else {
			return tikronUtil.substitute(_cfg.msg.ratingEmpty, []);
		}
	}

}
