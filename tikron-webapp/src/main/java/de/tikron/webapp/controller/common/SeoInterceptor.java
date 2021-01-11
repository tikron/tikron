/**
 * Copyright (c) 2014 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.webapp.service.gallery.GalleryService;
import de.tikron.webapp.util.SeoURI;

/**
 * Prepares default SEO data for views.
 *
 * @since 06.04.2014
 * @author Titus Kruse
 */
@Component
public class SeoInterceptor extends AbstractInterceptor {

	private GalleryService galleryService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		if (modelAndView != null) {
			ModelMap modelMap = modelAndView.getModelMap();
			// Add canonical URL, if other interceptor or controller didn't set it.
			if (!modelMap.containsAttribute(AbstractPageController.MODEL_ATTR_CANONICAL_URL)) {
				String canonicalUrl = new SeoURI(modelAndView.getViewName()).toString();
				modelMap.addAttribute(AbstractPageController.MODEL_ATTR_CANONICAL_URL, canonicalUrl);
			}
			// Add map of catalogs used to build SEO URLs on several pages
			Map<String, String> catalogSeoNames = new HashMap<String, String>();
			List<Catalog> catalogs = galleryService.getCatalogs(true);
			for (Catalog catalog : catalogs) {
				catalogSeoNames.put(catalog.getName(), buildDisplayCatalogUrl(catalog));
			}
			modelMap.addAttribute("displayCatalogURLs", catalogSeoNames);
			// Add map of categories used to build SEO URLs on several pages
			Map<String, String> categorySeoNames = new HashMap<String, String>();
			List<Category> categories = galleryService.getCategories(true);
			for (Category category : categories) {
				SeoURI uri = new SeoURI(ViewConstants.GALLERY_DISPLAY_CATEGORY);
				uri.addParameter(ControllerConstants.REQUEST_PARAM_CATEGORY_ID, category.getId());
				uri.addParameter(ControllerConstants.REQUEST_PARAM_SEO_NAME, category.getDisplayName());
				categorySeoNames.put(category.getName(), uri.toString());
			}
			modelMap.addAttribute("displayCategoryURLs", categorySeoNames);
		}
	}

	public static String buildDisplayCatalogUrl(Catalog catalog) {
		SeoURI uri = new SeoURI(ViewConstants.GALLERY_DISPLAY_CATALOG);
		uri.addParameter(ControllerConstants.REQUEST_PARAM_CATALOG_ID, catalog.getId());
		uri.addParameter(ControllerConstants.REQUEST_PARAM_SEO_NAME, catalog.getDisplayName());
		return uri.toString();
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

}
