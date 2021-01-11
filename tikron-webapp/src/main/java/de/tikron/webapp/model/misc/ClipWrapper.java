/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.model.misc;

import de.tikron.persistence.model.misc.Clip;
import de.tikron.webapp.model.common.EntityBean;

/**
 * Wrapper around an entity. This is an approach extending the entity and using its copy constructor. No need of coding
 * delegate methods but not really a facade.
 *
 * @since 18.03.2015
 * @author Titus Kruse
 */
public class ClipWrapper extends Clip implements EntityBean<Clip> {

	private static final long serialVersionUID = 5222088166919383979L;

	/**
	 * Default constructor.
	 * 
	 * @param e The video clip entity.
	 */
	public ClipWrapper(Clip e) {
		super(e);
	}

	@Override
	public Clip getEntity() {
		return this;
	}

}
