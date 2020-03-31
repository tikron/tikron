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

	var _bindUIActions = function() {
		// Show/Hide mobile menu
		_ui.topNav.find('#toggleNav').on('click', function() {
			_ui.topNav.find('ul').toggleClass('show');
		});
		// Handle mouse enter and leave on main top navigation items having a sub navigation
		var $subNav = _ui.topNav.find('ul li.hasSubNav');
		var $subNavTrigger = $('a', $subNav);
		$subNavTrigger.mouseenter(function() {
			var id = $(this).parent().attr('id');
			_switchSubNav(id, true);
		});
		$subNav.mouseleave(function() {
			var id = $(this).attr('id');
			_switchSubNav(id, false);
		});
	}

	/**
	 * Show or hide a a sub navigation.
	 * 
	 * @param elHover
	 *          The DOM element of the top navigation list item.
	 * @param enter
	 *          If true, the sub navigation will be shown, otherwise it will be
	 *          hidden.
	 */
	var _switchSubNav = function(hoverId, enter) {
		var dropDownNav = (window.innerWidth < 960); 
		console.debug(enter + '/' + hoverId);
		if (hoverId && !dropDownNav) {
			var hoverName = hoverId.replace(/navTop_/, '')
			// Hide sub nav on leave or already shown current
			_ui.subNav.each(function() {
				var $subNav=$(this);
				var subId = $subNav.attr('id');
				var subName = subId.replace(/navSub_/, '')
				var isPermanent = $subNav.hasClass('permanent');
				var isHover = subName == hoverName;
				if (!enter && isHover && !isPermanent || enter && !isHover && isPermanent) {
					$subNav.fadeOut(150, function() {
						$subNav.removeClass('active')
					});
				}
			});
			// Show sub nav on enter or already shown current
			_ui.subNav.each(function() {
				var $subNav=$(this);
				var subId = $subNav.attr('id');
				var subName = subId.replace(/navSub_/, '')
				var isPermanent = $subNav.hasClass('permanent');
				var isHover = subName == hoverName;
				if (enter && isHover && !isPermanent || !enter && !isHover && isPermanent) {
					_loadSubNav(this, subName);
					$subNav.fadeIn(150, function() {
						$subNav.addClass('active')
					});
				}
			});
		}
	}

	/**
	 * Lazy loads inner content of sub navigation.
	 * 
	 * @param elContainer
	 *          The HTML element receiving the response.
	 * @param identifier
	 *          The identifier of the navigation (catalog) to load.
	 */
	var _loadSubNav = function(elContainer, identifier) {
		if (!$.trim($(elContainer).html())) {
			$.ajax({
				url : _cfg.loadSubNavUrl + identifier,
				dataType: 'html',
				success : function(msg) {
					$(elContainer).html(msg);
				}
			});
		}
	}

}
