/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tikron.persistence.model.user.CategoryComment;
import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikron.persistence.model.user.PictureComment;
import de.tikron.persistence.model.user.User;
import de.tikron.webapp.assembler.user.CommentDTOAssembler;
import de.tikron.webapp.controller.common.AbstractAjaxFormController;
import de.tikron.webapp.controller.common.AjaxResponse;
import de.tikron.webapp.controller.common.ErrorResponse;
import de.tikron.webapp.controller.common.SuccessResponse;
import de.tikron.webapp.model.user.CommentDTO;
import de.tikron.webapp.model.user.CommentForm;
import de.tikron.webapp.service.gallery.GalleryService;
import de.tikron.webapp.service.user.UserService;

/**
 * Handler for an add comment Ajax form. 
 *
 * @date 10.05.2015
 * @author Titus Kruse
 */
@Controller
public class AddCommentController extends AbstractAjaxFormController {

	public static final String MODEL_ATTR_COMMENT_FORM = "comment";
	
	private UserService userService;
	
	private GalleryService galleryService;
	
	private CommentDTOAssembler commentDTOAssembler;

	/**
	 * Process comment form.
	 * 
	 * @param commentForm The comment to add.
	 * @param result Spring form result.
	 * 
	 * @return The form response. On success the response data contains the new comment. On error the reponse data contains the Spring errors. 
	 */
	@RequestMapping(value="/addComment.json", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse processSubmit(@Valid @ModelAttribute(MODEL_ATTR_COMMENT_FORM) CommentForm commentForm, BindingResult result) {
		// Validate if commenting the related category or picture ist allowed 
		if (commentForm.getRelatedEntityType().equals(CommentTypeId.CATEGORY) && !galleryService.getCategory(commentForm.getRelatedEntityId()).getCommentable()) {
			result.reject("comment.error.disallowed");
		} else if (commentForm.getRelatedEntityType().equals(CommentTypeId.PICTURE) && !galleryService.getPicture(commentForm.getRelatedEntityId()).getCategory().getCommentable()) {
			result.reject("comment.error.disallowed");
		}
		if (!result.hasErrors()) {
			// Create domain model beans from CommentForm DTO. Should be moved to DTO assembler or service layer.
			User user = new User(commentForm.getAuthor());
			user.setEmail(commentForm.getEmail());
			user.setUrl(commentForm.getUrl());
			Comment comment;
			if (commentForm.getRelatedEntityType().equals(CommentTypeId.CATEGORY)) {
				comment = new CategoryComment(galleryService.getCategory(commentForm.getRelatedEntityId()), user);
			} else if (commentForm.getRelatedEntityType().equals(CommentTypeId.PICTURE)) {
				comment = new PictureComment(galleryService.getPicture(commentForm.getRelatedEntityId()), user);
			} else {
				throw new IllegalArgumentException("Unsupported related entity type for comment: " + commentForm.getRelatedEntityType());
			}
			comment.setText(commentForm.getText());
			// Validate
// See note in CommentForm.java
//			Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//			Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
//      for (ConstraintViolation<Comment> constraintViolation : violations) {
//  			System.out.println("AddCommentAjaxController.processSubmit()" + constraintViolation);
//      }
			// Persist
			userService.addComment(comment);
			// Return comment as DTO back to frontend
			CommentDTO commentDto = commentDTOAssembler.toDTO(comment, CommentDTO.class, localizationContext);
			return new SuccessResponse(commentDto);
		} else {
			return new ErrorResponse(result, getLocalizationContext());
		}
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

	@Autowired
	public void setCommentDTOAssembler(CommentDTOAssembler commentDTOAssembler) {
		this.commentDTOAssembler = commentDTOAssembler;
	}

}
