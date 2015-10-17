/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.math.NumberUtils;



/**
 * A result of ratings for a publication. This is an unmapped POJO used by a query with HPQL scalar functions result.
 *
 * @date 27.04.2015
 * @author Titus Kruse
 */
public class RatingResult {
	
	private RatingTypeId type;
	
	private Number id;
	
	private Long count;
	
	private Double avarage;

	public RatingResult(RatingTypeId type, Number id, Long count, Double avarage) {
		this.type = type;
		this.id = id;
		this.count = count;
		this.avarage = avarage;
	}

	public RatingResult(RatingTypeId type, Number id) {
		this(type, id, NumberUtils.LONG_ZERO, null);
	}

	public RatingTypeId getType() {
		return type;
	}

	public Number getId() {
		return id;
	}

	public Long getCount() {
		return count;
	}

	public Double getAvarage() {
		return avarage;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("type", type).append("count", count).append("avarage", avarage).toString();
	}

}
