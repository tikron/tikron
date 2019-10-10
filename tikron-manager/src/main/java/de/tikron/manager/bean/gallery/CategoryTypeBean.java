/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.web.jsf.FacesContextUtils;

import de.tikron.manager.service.gallery.CategoryTypeService;
import de.tikron.persistence.model.gallery.CategoryType;

/**
 * Helper bean providing a list of available category types.
 *
 * @date 05.04.2015
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
public class CategoryTypeBean implements Serializable {

private static final long serialVersionUID = -2940941963125048972L;

	//	@ManagedProperty(value = "#{categoryTypeService}")
	private transient CategoryTypeService categoryTypeService;

	public List<CategoryType> getCategoryTypes() {
		return getCategoryTypeService().getAll();
	}

	/**
	 * Returns configured {@link de.tikron.manager.service.user.CategoryTypeService} (even after restore of serialized bean).
	 * 
	 * @see http://stackoverflow.com/questions/20067698/injecting-non-serializable-application-scoped-bean-as-managed-property-of-serial
	 *  
	 * @return The CategoryTypeService.
	 */
	private CategoryTypeService getCategoryTypeService() {
		if (categoryTypeService == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			categoryTypeService = FacesContextUtils.getWebApplicationContext(context).getBean(CategoryTypeService.class);
		}
		return categoryTypeService;
	}

}
