package de.tikron.persistence.dao.user;

import java.util.List;

import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.CategoryComment;
import de.tikron.persistence.model.user.ClipComment;
import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.CommentType;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikron.persistence.model.user.PictureComment;
import de.tikru.commons.jpa.dao.GenericDao;

public interface CommentDao extends GenericDao<Comment, Long> {
	
	/**
	 * Holt einen Kommentar inklusive des kommentierten Objekts und des Benutzers.
	 * 
	 * @param id Die ID des Kommentars.
	 * @return Der Kommentar oder null, falls er nicht existiert.
	 */
	public Comment findByIdFetchUserAndCommented(Long id);

	/**
	 * Liste der Kommentare vom Typ commentType.
	 * 
	 * @param commentType Der Kommentartyp.
	 * @return Die Liste der Kommentare.
	 */
	public List<Comment> findByCommentType(CommentType commentType);

	/**
	 * Liste sichtbarer Kommentare vom Typ commentType.
	 * 
	 * @param commentType Der Kommentartyp.
	 * @param visibleOnly Gibt an, ob die Liste nur sichtbare Kommentare enthält.
	 * @return Die Liste der Kommentare.
	 */
	public List<Comment> findByCommentTypeAndVisibility(CommentType commentType, boolean visibleOnly);

	/**
	 * Liste der Kommentare vom Typ commentTypeId.
	 * 
	 * @param commentTypeId Die Kommentartyp ID.
	 * @return Die Liste der Kommentare.
	 */
	public List<Comment> findByCommentTypeId(CommentTypeId commentTypeId);

	/**
	 * Liste aller Kommentare eines Kurzfilms.
	 * 
	 * @param clip Der Kurzfilm.
	 * @return Die Liste der Kommentare.
	 */
	public List<ClipComment> findByClip(Clip clip);

	/**
	 * Liste sichtbarer Kommentare eines Kurzfilms.
	 * 
	 * @param clip Der Kurzfilm.
	 * @return Die Liste der Kommentare.
	 */
	public List<ClipComment> findVisibleByClip(Clip clip);

	/**
	 * Liste aller Kommentare eines Bildes.
	 * 
	 * @param picture Das Bild.
	 * @return Die Liste der Kommentare.
	 */
	public List<PictureComment> findByPicture(Picture picture);

	/**
	 * Liste sichtbarer Kommentare eines Bildes.
	 * 
	 * @param picture Das Bild.
	 * @return Die Liste der Kommentare.
	 */
	public List<PictureComment> findVisibleByPicture(Picture picture);

	/**
	 * Liste sichtbarer Kommentare einer Kategorie.
	 * 
	 * @param category Die Kategorie.
	 * @return Die Liste der Kommentare.
	 */
	public List<CategoryComment> findVisibleByCategory(Category category);

}
