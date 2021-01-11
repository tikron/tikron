/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.assembler.user;

import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tikron.persistence.model.user.CategoryComment;
import de.tikron.persistence.model.user.Comment;
import de.tikron.persistence.model.user.PictureComment;
import de.tikron.persistence.model.user.User;
import de.tikron.webapp.assembler.gallery.CategoryDTOAssembler;
import de.tikron.webapp.assembler.gallery.PictureDTOAssembler;
import de.tikron.webapp.model.gallery.CategoryDTO;
import de.tikron.webapp.model.gallery.PictureDTO;
import de.tikron.webapp.model.user.CategoryCommentDTO;
import de.tikron.webapp.model.user.CommentDTO;
import de.tikron.webapp.model.user.PictureCommentDTO;
import de.tikron.webapp.util.LocalizationConverter;
import de.tikru.commons.util.FormattedTextCompiler;

/**
 * Component class mapping from/to data transfer objects of type Comment. 
 *
 * @author Titus Kruse
 * @since 02.01.2015
 */
@Component
public class CommentDTOAssembler {
	
	private CategoryDTOAssembler categoryDTOAssembler;
	
	private PictureDTOAssembler pictureDTOAssembler;
	
	/**
	 * Returns a {@link de.tikron.persistence.model.user.Comment} mapped from the given
	 * {@link de.tikron.webapp.model.user.CommentDTO}.
	 * 
	 * Note: Mapping to an already existing entity is currently not supported by this method. 
	 * 
	 * @param commentDTO The CommentDTO.
	 * @param type The type of Comment to return.
	 * @return The new Comment.
	 * @throws IllegalArgumentException
	 */
	public <D extends CommentDTO, E extends Comment> E fromDTO(D commentDTO, Class<E> type) throws IllegalArgumentException {
		if (type.equals(CategoryCommentDTO.class)) {
			// TODO Implement mapping from CategoryCommentDTO to CategoryComment
			throw new IllegalArgumentException("Unsupported subclass of type CommentDTO.");
		} else if (type.equals(PictureCommentDTO.class)) {
			// TODO Implement mapping from PictureCommentDTO to PictureComment
			throw new IllegalArgumentException("Unsupported subclass of type CommentDTO.");
		} else {
			throw new IllegalArgumentException("Unsupported subclass of type CommentDTO.");
		}
	}
	
	/**
	 * Returns a {@link de.tikron.webapp.model.user.CommentDTO} of the given type constructed from the given
	 * {@link de.tikron.persistence.model.user.Comment}.
	 * 
	 * @param comment The Comment entity.
	 * @param type The type of CommentDTO to return.
	 * @param localConverter A LocalizationConverter used to convert to external representation.
	 * @return The CommentDTO.
	 * @throws IllegalArgumentException If the given Comment is not supported by the method.
	 */
	public <D extends CommentDTO, E extends Comment> D toDTO(E comment, Class<D> type, LocalizationConverter localConverter) throws IllegalArgumentException {
		User user = comment.getUser();
		// Compile new line only to avoid possible code injection attack by a user having knowledge about text compiler commands.
		String text = FormattedTextCompiler.getInstance().compile(comment.getText(), FormattedTextCompiler.CONVERT_NEWLINE);
		String createdOn = localConverter.getDateFormatter(FormatStyle.SHORT).format(comment.getCreatedOn());
		if (type.equals(CategoryCommentDTO.class)) {
			CategoryDTO categoryDTO = categoryDTOAssembler.toDTO(((CategoryComment) comment).getCategory());
			return type.cast(new CategoryCommentDTO(comment.getId(), createdOn, user.getName(), text,
					user.getEmail(), user.getUrl(), categoryDTO));
		} else if (type.equals(PictureCommentDTO.class)) {
			PictureDTO pictureDTO = pictureDTOAssembler.toDTO(((PictureComment) comment).getPicture());
			return type.cast(new PictureCommentDTO(comment.getId(), createdOn, user.getName(), text,
					user.getEmail(), user.getUrl(), pictureDTO));
		} else if (type.equals(CommentDTO.class)) {
			return type.cast(new CommentDTO(comment.getId(), createdOn, user.getName(), text,
					user.getEmail(), user.getUrl()));
		} else {
			throw new IllegalArgumentException("Unsupported subclass of type CommentDTO.");
		}
	}
	
	/**
	 * Returns a list of {@link de.tikron.webapp.model.user.CommentDTO} baked from the given Collection of {@link de.tikron.persistence.model.user.Comment}.
	 * 
	 * @param comments The Collection of Comment.
	 * @param type The type of CommentDTO to return.
	 * @param localConverter A LocalizationConverter used to convert to external representation.
	 * @return A List of CommentDTO.
	 */
	public <D extends CommentDTO, E extends Comment> List<D> toDTOList(Collection<E> comments, Class<D> type, LocalizationConverter localConverter) {
		List<D> dtoList = new ArrayList<D>(comments.size());
		for (E comment : comments) {
			dtoList.add(toDTO(comment, type, localConverter));
		}
		return dtoList;
	}

	@Autowired
	public void setCategoryDTOAssembler(CategoryDTOAssembler categoryDTOAssembler) {
		this.categoryDTOAssembler = categoryDTOAssembler;
	}

	@Autowired
	public void setPictureDTOAssembler(PictureDTOAssembler pictureDTOAssembler) {
		this.pictureDTOAssembler = pictureDTOAssembler;
	}

}
