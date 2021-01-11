/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.service.user;

import java.util.List;

import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikron.persistence.model.user.User;

/**
 * Schnittstelle zum Service-Objekt für Benutzer-Entities.
 *
 * @author Titus Kruse
 * @since 23.03.2012
 */
public interface UserService {
	
	/**
	 * Initialisiert einen Gastbenutzer. Die Methode liefert einen neuen Gastbenutzer, falls dieser nicht bereits
	 * vorhanden ist.
	 * 
	 * @param name Der Name des Benutzers.
	 * 
	 * @return Der Benutzer.
	 */
	public User initGuestUser(String name);

	/**
	 * Lädt eine Liste von Kommentaren vom übergebenen Typ. Der Typ wird als dessen Primäreschlüssel statt als Entität
	 * erwartet, weil dies häufiger Verwendung findet.
	 * 
	 * @param commentTypeId Der Schlüssel des Kommentartyps.
	 * 
	 * @return Die Liste der Kommentare.
	 */
	public List<Comment> getComments(CommentTypeId commentTypeId);

	/**
	 * Speichert einen Kommentar.
	 * 
	 * @param comment Der Kommentar.
	 */
	public void addComment(Comment comment);

}
