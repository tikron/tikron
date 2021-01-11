/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.webapp.facade.misc;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.misc.Teaser;
import de.tikron.persistence.model.misc.WebRecommendation;
import de.tikron.webapp.model.misc.ClipEntityBean;
import de.tikron.webapp.model.misc.ClipWrapper;
import de.tikron.webapp.model.misc.TeaserEntityBean;
import de.tikron.webapp.model.misc.WebRecommendationEntityBean;

/**
 * An implementation of MiscelaniuosBeanFactory configured with Spring annotations.
 *
 * @since 19.03.2015
 * @author Titus Kruse
 */
@Configuration
public class AnnotatedMiscelaniousBeanFactory implements MiscelaniuosBeanFactory {

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	@Override
	public ClipWrapper clipWrapper(Clip clip) {
		return new ClipWrapper(clip);
	}

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	@Override
	public ClipEntityBean clipEntityBean(Clip clip) {
		return new ClipEntityBean(clip);
	}

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	@Override
	public TeaserEntityBean teaserEntityBean(Teaser teaser) {
		return new TeaserEntityBean(teaser);
	}

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	@Override
	public WebRecommendationEntityBean webRecommendationEntityBean(WebRecommendation webRecommendation) {
		return new WebRecommendationEntityBean(webRecommendation);
	}

}
