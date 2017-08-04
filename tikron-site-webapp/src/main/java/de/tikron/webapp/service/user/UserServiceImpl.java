/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.service.user;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.common.spring.EmailService;
import de.tikron.persistence.dao.user.CommentDao;
import de.tikron.persistence.dao.user.CommentTypeDao;
import de.tikron.persistence.dao.user.UserDao;
import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikron.persistence.model.user.User;

/**
 * Standard-Implementation des Service für Benutzer.
 *
 * @date 23.03.2012
 * @author Titus Kruse
 */
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	private static Logger USERACTION_LOGGER = LogManager.getLogger("de.tikron.webapp.service.user.UserAction");

	private UserDao userDao;

	private CommentDao commentDao;

	private CommentTypeDao commentTypeDao;

	private EmailService emailService;

	private MessageSource messageSource;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	// Using isolation level SERIALIZABLE can cause concurrent locks. Performance problems are the consequence on high traffic.
	// An alternative is to handle "find or insert" on database level by a stored procedure.
	public User initGuestUser(String name) {
		return userDao.findOrCreate(name);
	}

	@Override
	public List<Comment> getComments(CommentTypeId commentTypeId) {
		List<Comment> comments = commentDao.findByCommentTypeAndVisibility(commentTypeDao.getReference(commentTypeId), true);
		comments.sort(Comparator.comparing(Comment::getCreatedOn).reversed());
		return comments;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	// Using isolation level SERIALIZABLE can cause concurrent locks. Performance problems are the consequence on high traffic.
	// An alternative is to handle "find or insert" on database level by a stored procedure.
	public void addComment(Comment comment) {
		User user = comment.getUser();
		// Try to find a possible already existing user with the same name
		User matchUser = userDao.findOrCreate(user.getName());
		// Set or override user information with actually entered data
		// This smells a little bit. To avoid overring information, a historical address table should be introduced. 
		if (StringUtils.isNotBlank(user.getEmail())) {
			matchUser.setEmail(user.getEmail());
		}
		if (StringUtils.isNotBlank(user.getUrl())) {
			matchUser.setUrl(user.getUrl());
		}
		userDao.save(matchUser);
		// Set matched user for comment and persist
		comment.setUser(matchUser);
		comment.setVisible(Boolean.TRUE);
		commentDao.insert(comment);
		// Send notification message to web master
		sendNotificationEmail(comment);
		// Logging
		if (comment.getRelatedEntity() != null) {
			USERACTION_LOGGER.info(MessageFormat.format("Comment of type {0} for object {1} added by User with name {3}.", 
					comment.getClass().getSimpleName(), comment.getRelatedEntity().getId(), comment.getRelatedEntity().getDisplayName(), comment.getUser().getDisplayName()));
		} else {
			USERACTION_LOGGER.info(MessageFormat.format("Comment of type {0} added by User with name {1}.", 
					comment.getClass().getSimpleName(), comment.getUser().getDisplayName()));
		}
	}

	/**
	 * Sendet einen Hinweisnachricht per E-Mail über einen neuen Kommentar.
	 * 
	 * @param comment Der Kommentar, über den informiert wird.
	 * @return true, falls das Senden erfolgreich war.
	 */
	private boolean sendNotificationEmail(Comment comment) {
		final char LINEFEED = '\n';
		// Fetch information from object, the user has commented
// CommentType is discriminator column and not set by ORM in new comment.		
//		String type = comment.getCommentType().getDescription();
		String type = comment.getClass().getSimpleName();
		// Fetch message meta data
		String subject = messageSource.getMessage("addComment.email.subject", new Object[] { type }, null);
		// Compose message
		StringBuffer content = new StringBuffer();
		content.append(messageSource.getMessage("addComment.email.headline", new Object[] { type }, null));
		content.append(LINEFEED);
		if (comment.getRelatedEntity() != null) {
			content.append(LINEFEED);
			content.append(messageSource.getMessage("addComment.email.id", new Object[] { comment.getRelatedEntity().getId() }, null));
			content.append(LINEFEED);
			content.append(messageSource.getMessage("addComment.email.name", new Object[] { comment.getRelatedEntity().getDisplayName() }, null));
		}
		content.append(LINEFEED);
		content.append(messageSource.getMessage("addComment.email.author", new Object[] { comment.getUser().getName() }, null));
		content.append(LINEFEED);
		content.append(messageSource.getMessage("addComment.email.email", new Object[] { comment.getUser().getEmail() }, null));
		content.append(LINEFEED);
		content.append(messageSource.getMessage("addComment.email.url", new Object[] { comment.getUser().getUrl() }, null));
		content.append(LINEFEED);
		content.append(messageSource.getMessage("addComment.email.text", new Object[] { comment.getText() }, null));
		content.append(LINEFEED);
		// Send message
		if (comment.getUser().getEmail() != null) {
			return emailService.sendEmail(comment.getUser().getEmail(), subject, content.toString());
		} else {
			return emailService.sendEmail(subject, content.toString());
		}
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Autowired
	public void setCommentTypeDao(CommentTypeDao commentTypeDao) {
		this.commentTypeDao = commentTypeDao;
	}

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@Resource
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
