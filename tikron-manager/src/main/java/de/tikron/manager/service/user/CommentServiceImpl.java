/**
 * Copyright (c) 2009 by Titus Kruse.
 */
package de.tikron.manager.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CRUDServiceImpl;
import de.tikron.persistence.dao.user.CommentDao;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.ClipComment;
import de.tikron.persistence.model.user.Comment;

/**
 * Default-Implementation des CommentService.
 *
 * @date 26.03.2009
 * @author Titus Kruse
 */
@Service("commentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommentServiceImpl extends CRUDServiceImpl<Comment, Long> implements CommentService {

	@Override
	public Comment get(Long id) {
		return getCommentDao().findByIdFetchUserAndCommented(id);
	}

	public List<ClipComment> getComments(Clip clip) {
		return getCommentDao().findVisibleByClip(clip);
	}

	protected CommentDao getCommentDao() {
		return (CommentDao) super.getDao();
	}

	@Autowired
	public void setCommentDao(CommentDao dao) {
		super.setDao(dao);
	}

}
