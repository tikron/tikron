/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.misc;

import java.time.LocalDate;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikron.persistence.model.user.ClipComment;
import de.tikru.commons.jpa.domain.DurationAttributeConverter;
import de.tikru.commons.jpa.domain.GeneratedKeyEntity;
import de.tikru.commons.jpa.domain.ShowableEntity;

/**
 * A video clip.
 *
 * @author Titus Kruse
 * @since 14.03.2015
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Clip.NQ_FIND_ALL, query = "SELECT o FROM Clip o"),
	@NamedQuery(name = Clip.NQ_FIND_ALL_ORDERBY_DATERECORDED, query = "SELECT o FROM Clip o ORDER BY o.dateRecorded"),
	@NamedQuery(name = Clip.NQ_FIND_BY_NAME, query = "SELECT o FROM Clip o WHERE o.name = :name") })
@Table(name = "clip", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Clip extends GeneratedKeyEntity<Long> implements ShowableEntity<Long> {

	private static final long serialVersionUID = -4554227552063589174L;
	
	public static final String NQ_FIND_ALL = "Clip.findAll";
	public static final String NQ_FIND_ALL_ORDERBY_DATERECORDED = "Clip.findAllOrderByDateRecorded";
	public static final String NQ_FIND_BY_NAME = "Clip.findByName";

	@Column(nullable = false)
	private String name;
	@Column
	private String title;
	@Column(name = "short_description", columnDefinition = ("text"))
	private String shortDescription;
	@Column(name = "long_description", columnDefinition = ("text"))
	private String longDescription;
	@Column(name = "image_name")
	private String imageName;
	@Column(name = "video_name")
	private String videoName;
	@Column(name = "video_width")
	private Short videoWidth;
	@Column(name = "video_height")
	private Short videoHeight;
	@Column(name = "date_recorded")
	private LocalDate dateRecorded;
	@Column
	@Convert(converter = DurationAttributeConverter.class) // autoApply doesn't work on java.time.Duration
	private Duration playtime;

	@OneToMany(mappedBy = "clip", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ClipComment> comments = new HashSet<ClipComment>();
	
	public Clip() {
	}
	
	/**
	 * Copy constuctor.
	 * 
	 * @param e An entity to copy from.
	 */
	protected Clip(Clip e) {
		super(e);
		this.name = e.name;
		this.title = e.title;
		this.shortDescription = e.shortDescription ;
		this.longDescription = e.longDescription ;
		this.imageName = e.imageName ;
		this.videoName = e.videoName ;
		this.videoWidth = e.videoWidth ;
		this.videoHeight = e.videoHeight ;
		this.dateRecorded = e.dateRecorded ;
		this.playtime = e.playtime ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public Short getVideoWidth() {
		return videoWidth;
	}

	public void setVideoWidth(Short videoWidth) {
		this.videoWidth = videoWidth;
	}

	public Short getVideoHeight() {
		return videoHeight;
	}

	public void setVideoHeight(Short videoHeight) {
		this.videoHeight = videoHeight;
	}

	public LocalDate getDateRecorded() {
		return dateRecorded;
	}

	public void setDateRecorded(LocalDate dateRecorded) {
		this.dateRecorded = dateRecorded;
	}

	public Duration getPlaytime() {
		return playtime;
	}

	public void setPlaytime(Duration playtime) {
		this.playtime = playtime;
	}

	public Set<ClipComment> getComments() {
		return comments;
	}

	public void setComments(Set<ClipComment> comments) {
		this.comments = comments;
	}

	public void addComment(ClipComment comment) {
		comment.setClip(this);
		comments.add(comment);
	}

	public void removeComment(ClipComment comment) {
		comments.remove(comment);
	}

	@Override
	public String getDisplayName() {
		return getTitle();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("title", title).toString();
	}

}
