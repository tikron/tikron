package de.tikron.webapp.model.user;

import de.tikron.webapp.model.gallery.PictureDTO;

public class PictureCommentDTO extends CommentDTO {
	
	private final PictureDTO picture;

	public PictureCommentDTO(Long id, String createdOn, String author, String text, String email, String url, PictureDTO picture) {
		super(id, createdOn, author, text, email, url);
		this.picture = picture;
	}

	public PictureDTO getPicture() {
		return picture;
	}

}
