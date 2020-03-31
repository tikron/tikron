/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.facade.misc;

import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.misc.Teaser;
import de.tikron.persistence.model.misc.WebRecommendation;
import de.tikron.webapp.model.misc.ClipEntityBean;
import de.tikron.webapp.model.misc.ClipWrapper;
import de.tikron.webapp.model.misc.TeaserEntityBean;
import de.tikron.webapp.model.misc.WebRecommendationEntityBean;

/**
 * A bean factory used to create miscelanious entity beans.
 *
 * @date 19.03.2015
 * @author Titus Kruse
 */
public interface MiscelaniuosBeanFactory {
	
	public ClipWrapper clipWrapper(Clip clip);
	
	public ClipEntityBean clipEntityBean(Clip clip);
	
	public TeaserEntityBean teaserEntityBean(Teaser teaser);
	
	public WebRecommendationEntityBean webRecommendationEntityBean(WebRecommendation webRecommendation);

}
