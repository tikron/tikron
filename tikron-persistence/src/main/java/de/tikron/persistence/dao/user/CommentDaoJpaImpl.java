/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;

import de.tikron.jpa.dao.GenericJpaDao;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.CategoryComment;
import de.tikron.persistence.model.user.ClipComment;
import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.CommentType;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikron.persistence.model.user.PictureComment;

/**
 * Data-Access-Object für Kommentare.
 * 
 * @author Titus Kruse
 */
public class CommentDaoJpaImpl extends GenericJpaDao<Comment, Long> implements CommentDao {

	/**
	 * Data Access Object erstellen
	 */
	public CommentDaoJpaImpl() {
		super(Comment.class);
	}

	/**
	 * Data Access Object mit EntityManager erstellen
	 * 
	 * @param entityManager Setzt den EntityManager für dieses DAO.
	 */
	public CommentDaoJpaImpl(EntityManager entityManager) {
		super(Comment.class, entityManager);
	}

	@Override
	public Comment findByIdFetchUserAndCommented(Long id) {
		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put(QueryHints.HINT_FETCHGRAPH, entityManager.getEntityGraph(Comment.NEG_USER));
		hints.put(QueryHints.HINT_FETCHGRAPH, entityManager.getEntityGraph(ClipComment.NEG_CLIP));
		hints.put(QueryHints.HINT_FETCHGRAPH, entityManager.getEntityGraph(PictureComment.NEG_PICTURE));
		return entityManager.find(Comment.class, id, hints);
	}

	@Override
	public List<Comment> findByCommentType(CommentType commentType) {
		TypedQuery<Comment> query = entityManager.createNamedQuery(Comment.NQ_FIND_BY_COMMENTTYPE, Comment.class);
		query.setParameter("commentType", commentType);
		return query.getResultList();
	}

	@Override
	public List<Comment> findByCommentTypeAndVisibility(CommentType commentType, boolean visibleOnly) {
		TypedQuery<Comment> query = entityManager.createNamedQuery(Comment.NQ_FIND_BY_COMMENTTYPE_AND_VISIBILITY, Comment.class);
		query.setParameter("commentType", commentType);
		query.setParameter("visibleOnly", visibleOnly);
		return query.getResultList();
	}

	@Override
	public List<Comment> findByCommentTypeId(CommentTypeId commentTypeId) {
		TypedQuery<Comment> query = entityManager.createQuery(
				"SELECT o FROM Comment o WHERE o.commentType.id = :commentTypeId", Comment.class);
		query.setParameter("commentTypeId", commentTypeId);
		return query.getResultList();
	}

	@Override
	public List<ClipComment> findByClip(Clip clip) {
		TypedQuery<ClipComment> query = entityManager.createNamedQuery(ClipComment.NQ_FIND_BY_CLIP, ClipComment.class);
		query.setParameter("clip", clip);
		return query.getResultList();
	}

	@Override
	public List<ClipComment> findVisibleByClip(Clip clip) {
		TypedQuery<ClipComment> query = entityManager.createNamedQuery(ClipComment.NQ_FIND_VISIBLE_BY_CLIP,
				ClipComment.class);
		query.setParameter("clip", clip);
		return query.getResultList();
	}

	@Override
	public List<PictureComment> findByPicture(Picture picture) {
		TypedQuery<PictureComment> query = entityManager.createNamedQuery(PictureComment.NQ_FIND_BY_PICTURE,
				PictureComment.class);
		query.setParameter("picture", picture);
		return query.getResultList();
	}

	@Override
	public List<PictureComment> findVisibleByPicture(Picture picture) {
		TypedQuery<PictureComment> query = entityManager.createNamedQuery(PictureComment.NQ_FIND_VISIBLE_BY_PICTURE,
				PictureComment.class);
		query.setParameter("picture", picture);
		return query.getResultList();
	}

	@Override
	public List<CategoryComment> findVisibleByCategory(Category category) {
		TypedQuery<CategoryComment> query = entityManager.createNamedQuery(CategoryComment.NQ_FIND_VISIBLE_BY_CATEGORY,
				CategoryComment.class);
		query.setParameter("category", category);
		return query.getResultList();
	}

}
