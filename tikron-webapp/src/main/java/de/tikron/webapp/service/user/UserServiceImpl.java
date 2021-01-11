/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.webapp.service.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.persistence.dao.user.CommentDao;
import de.tikron.persistence.dao.user.CommentTypeDao;
import de.tikron.persistence.dao.user.UserDao;
import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikron.persistence.model.user.User;
import de.tikru.commons.spring.MailService;

/**
 * Standard-Implementation des Service für Benutzer.
 *
 * @author Titus Kruse
 * @since 23.03.2012
 */
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	private static Logger userAction = LoggerFactory.getLogger("de.tikron.webapp.service.user.UserAction");

	private UserDao userDao;

	private CommentDao commentDao;

	private CommentTypeDao commentTypeDao;

	private MailService mailService;

	private MessageSource messageSource;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	// Using isolation level SERIALIZABLE can cause concurrent locks. Performance problems are the consequence on high traffic.
	// TODO An alternative is to handle "find or insert" on database level by a stored procedure.
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
		sendNotificationMail(comment);
		// Logging
		if (comment.getRelatedEntity() != null) {
			userAction.info(MessageFormat.format("Comment of type {0} for object {1} added by User with name {3}.", 
					comment.getClass().getSimpleName(), comment.getRelatedEntity().getId(), comment.getRelatedEntity().getDisplayName(), comment.getUser().getDisplayName()));
		} else {
			userAction.info(MessageFormat.format("Comment of type {0} added by User with name {1}.", 
					comment.getClass().getSimpleName(), comment.getUser().getDisplayName()));
		}
	}

	/**
	 * Sendet einen Hinweisnachricht per E-Mail über einen neuen Kommentar.
	 * 
	 * @param comment Der Kommentar, über den informiert wird.
	 * @return true, falls das Senden erfolgreich war.
	 */
	private boolean sendNotificationMail(Comment comment) {
		// Fetch information from object, the user has commented
// CommentType is discriminator column and not set by ORM in new comment.		
//		String type = comment.getCommentType().getDescription();
		String type = comment.getClass().getSimpleName();
		// Fetch message meta data
		String subject = messageSource.getMessage("addComment.email.subject", new Object[] { type }, null);
		// Compose message
		try (StringWriter out = new StringWriter()) {
			try (PrintWriter writer = new PrintWriter(out)) {
				writer.println(messageSource.getMessage("addComment.email.headline", new Object[] { type }, null));
				if (comment.getRelatedEntity() != null) {
					writer.println(messageSource.getMessage("addComment.email.id", new Object[] { comment.getRelatedEntity().getId() }, null));
					writer.println(messageSource.getMessage("addComment.email.name", new Object[] { comment.getRelatedEntity().getDisplayName() }, null));
				}
				writer.println(messageSource.getMessage("addComment.email.author", new Object[] { comment.getUser().getName() }, null));
				writer.println(messageSource.getMessage("addComment.email.email", new Object[] { comment.getUser().getEmail() }, null));
				writer.println(messageSource.getMessage("addComment.email.url", new Object[] { comment.getUser().getUrl() }, null));
				writer.println(messageSource.getMessage("addComment.email.text", new Object[] { comment.getText() }, null));
				// Send message
				return mailService.send(subject, out.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
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
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	@Resource
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
