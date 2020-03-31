package de.tikron.webapp.model.user;

import de.tikron.webapp.model.common.EntityDTO;


public class CommentDTO extends EntityDTO<Long> {
	
	private final String createdOn;

	private final String author;

	private final String text;

	private final String email;

	private final String url;

	public CommentDTO(Long id, String createdOn, String author, String text, String email, String url) {
		super(id);
		this.createdOn = createdOn;
		this.author = author;
		this.text = text;
		this.email = email;
		this.url = url;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public String getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}

	public String getEmail() {
		return email;
	}

	public String getUrl() {
		return url;
	}

	/**
	 * Liefert die Domain der URL.
	 * 
	 * @return Die Domain.
	 */
	public String getUrlDomain() {
		String result = getUrl();
		if (result != null) {
			int colon = result.indexOf("://");
			if (colon > -1) {
				result = result.substring(colon + 3);
			}
			int slash = result.indexOf("/");
			if (slash > -1) {
				result = result.substring(0, slash);
			}
		}
		return result;
	}

}
