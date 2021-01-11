/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

/**
 * Gives a summary of all views available in the application. 
 *
 * @author Titus Kruse
 * @since 30.12.2012
 */
public final class ViewConstants {
	
	private ViewConstants() {};
	
	// TODO Should be refactored (Replaced by generic logic)

	public static final String DISPLAY_HOMEPAGE = "displayHomepage";
	public static final String DISPLAY_LOG = "displayLog";
	public static final String DISPLAY_LOG_AJAX = "displayLogAjax";
	public static final String DISPLAY_APPLICATION_ERROR = "displayApplicationError";
	public static final String DISPLAY_SYSTEM_ERROR = "displaySystemError";
	public static final String SEND_CONTACT_MESSAGE = "sendContactMessage";
	public static final String SEND_CONTACT_MESSAGE_CONFIRMATION = "sendContactMessageConfirmation";
	public static final String DISPLAY_SUB_NAVIGATION_AJAX = "displaySubNavAjax";

	public static final String GALLERY_DISPLAY_CATALOG = "gallery/displayCatalog";
	public static final String GALLERY_DISPLAY_CATEGORY = "gallery/displayCategory";
	public static final String GALLERY_DISPLAY_PICTURE = "gallery/displayPicture";
	public static final String GALLERY_DISPLAY_PICTURE_AJAX = "gallery/displayPictureAjax";

	public static final String TRAVELS_DISPLAY_TRAVELS = "travels/displayTravels";
	public static final String TRAVELS_DISPLAY_TRAVEL = "travels/displayTravel";

	public static final String CLIPS_DISPLAY_CLIPS = "clips/displayClips";
	public static final String CLIPS_DISPLAY_CLIP = "clips/displayClip";

	public static final String WEBRECOMMENDATIONS_DISPLAY_ENTRIES = "webRecommendations/displayEntries";
}
