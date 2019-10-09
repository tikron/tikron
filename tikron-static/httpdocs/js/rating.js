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
	}
	
	this.build = function(url) {
		_cfg.addRatingUrl = url;
		_ui = {
				ratingStars: $('div.ratingStars')
		};
		_bindUIActions();
	}
	
	this.initAndBuild = function() {
		_root = this;
		_cfg = this.config;
		_root.build(_cfg.addRatingUrl);
	}

	var _bindUIActions = function() {
		_ui.ratingStars.each(function() {
			// Fetch unique entity ID from HTML element
			var entityId = $(this).data('id');
			var ratingValue = $(this).data('avarage');
			var ratingCount = $(this).data('count');
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
	var _handleRating = function(e, value) {
		var $rating = e.parents('.ratingStars');
		$rating.find('.stars').spin();
		var entityId = $rating.data('id');
		var requestUrl = _buildRequestUrl(entityId, value)
		$.ajax({
			url: requestUrl,
			context: $rating,
			success: function(ratingResult) {
				var $text = $rating.find('span.text');
				$text.text(_composeRatingText(ratingResult.count));
				$rating.find('.stars').spin(false);
			}
		});
	}
	
	var _findById = function(id) {
		var $rating = _ui.ratingStars.filter(function() {
			return $(this).data("id") == id;
		});
		return $rating;
	}
	
	/**
	 * Builds the Ajax request for adding a rating value for an entity.
	 * 
	 * @param entityId The entity ID, of the object to be rated.
	 * @param value The rating value chosen by the user.
	 */
	var _buildRequestUrl = function(entityId, value) {
		return _cfg.addRatingUrl.replace('Id=', 'Id=' + entityId).replace('ratingValue=', 'ratingValue=' + value)
	}
	
	/**
	 * Composes a message text bases on the given rating data.
	 * 
	 * @param rating The rating result.
	 * @raturn The formatted text.
	 */
	var _composeRatingText = function(ratingCount) {
		if (ratingCount > 0) {
			return tikronUtil.substitute(_cfg.msg.ratingCount, [ratingCount]);
		} else {
			return tikronUtil.substitute(_cfg.msg.ratingEmpty, []);
		}
	}

}
