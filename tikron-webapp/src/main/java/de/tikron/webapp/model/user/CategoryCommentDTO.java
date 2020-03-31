package de.tikron.webapp.model.user;

import de.tikron.webapp.model.gallery.CategoryDTO;

public class CategoryCommentDTO extends CommentDTO {
	
	private final CategoryDTO category;

	public CategoryCommentDTO(Long id, String createdOn, String author, String text, String email, String url, CategoryDTO category) {
		super(id, createdOn, author, text, email, url);
		this.category = category;
	}

	public CategoryDTO getCategory() {
		return category;
	}

}
