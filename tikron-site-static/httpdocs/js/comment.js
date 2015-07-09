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
		_ui = {
				form: $('form#addComment'),
				list: $('ul.comments')
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
					// Reset errors
					tikronUtil.resetErrors(_ui.form);
					if (response.status == 'ERROR') {
						tikronUtil.showErrors(_ui.form, response);
					} else {
						// Handle success
						if (_ui.list) {
							_addListEntry(response.data);
							_updateList();
						}
						_ui.form.trigger('reset');
					}
				},
				'json'
			);
			e.preventDefault();
			return false;
		});
	}
	
	/**
	 * Builds comment list entry HTML and adds it to the current list.
	 * 
	 * @param The comment to add.
	 */
	_addListEntry = function(comment) {
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
	_updateList = function() {
		var $li = _ui.list.find('li');
		$li.removeClass('first last');
		$li.first().addClass('first');
		$li.last().addClass('last');
	}

}
