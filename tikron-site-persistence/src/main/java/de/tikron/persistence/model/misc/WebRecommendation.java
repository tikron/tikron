/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.misc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import de.tikron.jpa.domain.GeneratedKeyEntity;
import de.tikron.jpa.domain.ShowableEntity;

/**
 * A web recommendation.
 *
 * @date 21.03.2015
 * @author Titus Kruse
 */
@Entity
@NamedQueries({
	@NamedQuery(name = WebRecommendation.NQ_FIND_ALL, query = "SELECT o FROM WebRecommendation o"),
	@NamedQuery(name = WebRecommendation.NQ_FIND_ALL_ORDERBY_SEQUENCE, query = "SELECT o FROM WebRecommendation o ORDER BY o.sequence")})
@Table(name = "web_recommendation")
public class WebRecommendation extends GeneratedKeyEntity implements ShowableEntity<Long> {

	public static final String NQ_FIND_ALL = "WebRecommendation.findAll";
	public static final String NQ_FIND_ALL_ORDERBY_SEQUENCE = "WebRecommendation.findAllOrderBySequence";
	
	@Column
	private String title;
	@Column(name = "description", columnDefinition = ("text"))
	private String description;
	@Column(name = "image_name")
	private String imageName;
	@Column
	private String url;
	@Column
	private Double sequence;

	public WebRecommendation() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getSequence() {
		return sequence;
	}

	public void setSequence(Double sequence) {
		this.sequence = sequence;
	}

	@Override
	public String getDisplayName() {
		return getTitle();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("title", title).toString();
	}

}
