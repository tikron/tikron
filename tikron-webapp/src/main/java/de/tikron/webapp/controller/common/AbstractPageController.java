/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.tikron.webapp.util.RobotsDirective;

/**
 * Abstract base controller for all servlets containing full page content.
 *
 * @since 27.12.2012
 * @author Titus Kruse
 */
public abstract class AbstractPageController extends AbstractController {

	protected static final String MODEL_ATTR_ROBOTS_DIRECTIVE = "robotsDirective";

	protected static final String MODEL_ATTR_PAGE_DESCRIPTION = "pageDescription";

	protected static final String MODEL_ATTR_PAGE_TITLE = "pageTitle";

	protected static final String MODEL_ATTR_CANONICAL_URL = "canonicalUrl";

	/**
	 * Liefert den Seitentitel, der im TITLE-Tag eingefügt wird.
	 * 
	 * @return Der Seitentitel.
	 */
	@ModelAttribute(MODEL_ATTR_PAGE_TITLE)
	public String getPageTitle() {
		return formatPageTitle(getSubTitle());
	}

	/**
	 * Formatiert den Seitentitel.
	 * 
	 * @param subTitle Ein Text, der dem Title angefügt wird.
	 * @return Der Titel.
	 */
	protected String formatPageTitle(String subTitle) {
		if (subTitle != null) {
			return String.format("%s - %s", subTitle, getMessage("title"));
		} else {
			return getMessage("title");
		}
	}

	/**
	 * Liefert den Untertitel einer Seite. Es wird versucht, ein Standardtext mit dem Key <action>.subtitle zu finden. Um
	 * einen spezifischen Text bereitzustellen, sollte die Methode überschrieben werden.
	 * 
	 * @return Der Untertitel oder null, falls keiner definiert ist.
	 */
	protected String getSubTitle() {
		try {
			return getMessage(getActionMessageKey("subTitle"));
		} catch (NoSuchMessageException e) {
			return null;
		}
	}

	/**
	 * Liefert den beschreibenden Text einer Seite, der für das Meta-Tag "description" verwendet wird. Es wird versucht,
	 * ein Standardtext mit dem Key <action>.description zu finden. Gibt es keine solchen Text, wird der Standardtext
	 * zurückgegeben. Um einen spezifischen Text bereitzustellen, sollte die Methode überschrieben werden.
	 * 
	 * @return Der beschreibende Text.
	 */
	@ModelAttribute(MODEL_ATTR_PAGE_DESCRIPTION)
	public String getPageDescription() {
		try {
			return getMessage(getActionMessageKey("description"));
		} catch (NoSuchMessageException e) {
			return getMessage("description");
		}
	}

	/**
	 * Liefert die Indexierungsdirektive für Webcrawler.
	 * 
	 * @return Die Robots-Direktive.
	 */
	@ModelAttribute(MODEL_ATTR_ROBOTS_DIRECTIVE)
	public String getRobotsDirective() {
		return RobotsDirective.INDEX.toString();
	}
}
