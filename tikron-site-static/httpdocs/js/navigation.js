/**
 * Tikron navigation javascript module. Copyright 2015 by Titus Kruse
 */

function TikronNav(options) {

	var _root;
	var _cfg;
	var _ui;

  this.config = $.extend({
    'loadSubNavUrl': '#'
  }, options);

	this.init = function() {
		_root = this;
		_cfg = this.config;
		_ui = {
				topNav: $('nav#main div.top'),
				subNav: $('nav#main div.sub')
		};
		_bindUIActions();
//		console.log(_cfg);
	}

	_bindUIActions = function() {
		// Handle hover events on main top navigation items
		_ui.topNav.find('ul li.hasSubNav').hover(function() {
			_switchSubNav(this, true);
		}, function() {
			_switchSubNav(this, false);
		});
	}

	/**
	 * Show or hide a a sub navigation.
	 * 
	 * @param hoverElement
	 *          The DOM element of the top navigation list item.
	 * @param enter
	 *          If true, the sub navigation will be shown, otherwise it will be
	 *          hidden.
	 */
	_switchSubNav = function(hoverElement, enter) {
		var hoverId = $(hoverElement).attr('id');
		// console.log(enter + '/' + hoverId);
		if (hoverId) {
			var hoverName = hoverId.replace(/navTop_/, '')
			// Hide sub nav on leave or already shown current
			_ui.subNav.each(function() {
				var subEl=$(this);
				var subId = subEl.attr('id');
				var subName = subId.replace(/navSub_/, '')
				var isPermanent = subEl.hasClass('permanent');
				var isHover = subName == hoverName;
				if (!enter && isHover && !isPermanent || enter && !isHover && isPermanent) {
					subEl.fadeOut(150, function() {
						subEl.removeClass('active')
					});
				}
			});
			// Show sub nav on enter or already shown current
			_ui.subNav.each(function() {
				var subEl=$(this);
				var subId = subEl.attr('id');
				var subName = subId.replace(/navSub_/, '')
				var isPermanent = subEl.hasClass('permanent');
				var isHover = subName == hoverName;
				if (enter && isHover && !isPermanent || !enter && !isHover && isPermanent) {
					_loadSubNav(this, subName);
					subEl.fadeIn(150, function() {
						subEl.addClass('active')
					});
				}
			});
		}
	}

	/**
	 * Lazy loads inner content of sub navigation.
	 * 
	 * @param container
	 *          The HTML element receiving the response.
	 * @param identifier
	 *          The identifier of the navigation (catalog) to load.
	 */
	_loadSubNav = function(container, identifier) {
		if (!$.trim($(container).html())) {
			$.ajax({
				url : _cfg.loadSubNavUrl + identifier,
				success : function(msg) {
					$(container).html(msg);
				}
			});
		}
	}

}
