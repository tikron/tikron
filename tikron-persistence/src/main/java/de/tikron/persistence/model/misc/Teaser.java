/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.misc;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikron.persistence.model.gallery.Picture;
import de.tikru.commons.jpa.domain.GeneratedKeyEntity;
import de.tikru.commons.jpa.domain.ShowableEntity;

/**
 * A teaser storing site related information to be shown as an eye catcher on the home page.  
 * 
 * @date 29.05.2015
 * @author Titus Kruse
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Teaser.NQ_FIND_ALL, query = "SELECT DISTINCT o FROM Teaser o"),
		@NamedQuery(name = Teaser.NQ_FIND_ALL_ORDERBY_NAME, query = "SELECT o FROM Teaser o ORDER BY o.name"),
		@NamedQuery(name = Teaser.NQ_FIND_ALL_ORDERBY_SEQUENCE, query = "SELECT o FROM Teaser o ORDER BY o.sequence, o.startDate"),
		@NamedQuery(name = Teaser.NQ_FIND_VISIBLE_ORDERBY_SEQUENCE, query = "SELECT o FROM Teaser o WHERE o.visible = true AND (o.startDate = NULL or o.startDate <= CURRENT_TIMESTAMP) AND (o.endDate = NULL or o.endDate >= CURRENT_TIMESTAMP) ORDER BY o.sequence, o.startDate"),
		@NamedQuery(name = Teaser.NQ_FIND_BY_NAME, query = "SELECT o FROM Teaser o WHERE o.name = :name") })
@Table(name = "teaser", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Teaser extends GeneratedKeyEntity<Long> implements ShowableEntity<Long> {

	private static final long serialVersionUID = -6293446250983491689L;
	
	public static final String NQ_FIND_ALL = "Teaser.findAll";
	public static final String NQ_FIND_ALL_ORDERBY_NAME = "Teaser.findAllOrderByName";
	public static final String NQ_FIND_ALL_ORDERBY_SEQUENCE = "Teaser.findAllOrderBySequence";
	public static final String NQ_FIND_VISIBLE_ORDERBY_SEQUENCE = "Teaser.findVisibileOrderSequence";
	public static final String NQ_FIND_BY_NAME = "Teaser.findByName";

	@Column(nullable = false)
	private String name;
	@Column
	private Double sequence;
	@Column(name = "start_date")
	private LocalDateTime startDate;
	@Column(name = "end_date")
	private LocalDateTime endDate;
	@Column(columnDefinition = ("smallint"))
	private Boolean visible;
	@Column
	private String title;
	@Column(name = "image_name")
	private String imageName;
	@Column(name = "image_alt")
	private String imageAlternate;
	@ManyToOne
	@JoinColumn(name = "picture_id")
	private Picture picture;
	@Column(name = "target_url")
	private String targetUrl;
	@Column
	private String caption;
	@Column
	private String subTitle;

	public Teaser() {
	}
	
	public Teaser(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSequence() {
		return sequence;
	}

	public void setSequence(Double sequence) {
		this.sequence = sequence;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageAlternate() {
		return imageAlternate;
	}

	public void setImageAlternate(String imageAlternate) {
		this.imageAlternate = imageAlternate;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	@Override
	public String getDisplayName() {
		return getTitle() != null ? getTitle() : getName();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("title", title).toString();
	}

}
