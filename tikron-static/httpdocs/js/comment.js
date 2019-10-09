/**
 * Tikron javascript module for handling of so called rating stars. 
 * 
 * Copyright 2015 by Titus Kruse
 */

function TikronComment(options) {

	var _root;
	var _cfg;
	var _ui;

  this.config = $.extend({
    'msg': {}
  }, options);

	this.init = function() {
		_root = this;
		_cfg = this.config;
	}
	
	this.build = function() {
		_ui = {
				form: $('form#comments'),
				list: $('ul.comments')
		};
		_bindUIActions();
	}
	
	this.initAndBuild = function() {
		_root = this;
		_cfg = this.config;
		_root.build();
	}

	var _bindUIActions = function() {
		_ui.form.bind('submit', function(e) {
			var $form = $(this);
			var url = $(this).attr('action');
			// Post data with request URL and JSON reponse
			$.ajax(url,	{
				context: this,
				data: $(this).serialize(),
			  dataType: 'json',
				method: 'post',
				success: function(response) {
					var $form = $(this);
					// Reset errors
					tikronUtil.resetErrors($form);
					if (response.status == 'ERROR') {
						tikronUtil.showErrors($form, response);
					} else {
						// Handle success
						if (_ui.list) {
							_addListEntry(response.data);
							_updateList();
						}
						$form.trigger('reset');
					}
				}
	  	});
			e.preventDefault();
		});
	}
	
	/**
	 * Builds comment list entry HTML and adds it to the current list.
	 * 
	 * @param The comment to add.
	 */
	var _addListEntry = function(comment) {
		var $li = $('<li>');
		var $text = $('<span>', {
			'class': 'text',
			html: comment.text
		});
		var $attr = $('<div>', {
			'class': 'attributes',
			html: [_cfg.msg.author, '&nbsp;', $('<strong>', {
				html: comment.author
			}), '&nbsp;', _cfg.msg.date, '&nbsp;', comment.createdOn]
		});
		if (comment.url) {
			$attr.append(',&nbsp;Site:&nbsp;', $('<a>', {
				href: comment.url,
				rel: 'nofollow',
				html: [comment.urlDomain, $('<i>', {'class': 'fa fa-external-link'})]
			}))
		}
		$li.append($text, $attr);
		$li.prependTo(_ui.list);
	}
	
	/**
	 * Updates the comment list.
	 */
	var _updateList = function() {
		var $li = _ui.list.find('li');
		$li.removeClass('first last');
		$li.first().addClass('first');
		$li.last().addClass('last');
	}

}
