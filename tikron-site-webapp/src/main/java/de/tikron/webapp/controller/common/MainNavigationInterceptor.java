/**
 * Copyright (c) 2013 by Titus Kruse.
 */
package de.tikron.webapp.controller.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.CatalogName;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikron.webapp.assembler.gallery.CatalogDTOAssembler;
import de.tikron.webapp.assembler.gallery.CategoryDTOAssembler;
import de.tikron.webapp.model.gallery.BasicCatalogDTO;
import de.tikron.webapp.model.gallery.BasicCategoryDTO;
import de.tikron.webapp.service.gallery.GalleryService;

/**
 * Prepares some information shown on the main navigation menu.
 *
 * @date 06.01.2013
 * @author Titus Kruse
 */
@Component
public class MainNavigationInterceptor extends AbstractInterceptor implements ApplicationContextAware {

	private GalleryService galleryService;
	
	private CatalogDTOAssembler catalogDTOAssembler;
	
	private CategoryDTOAssembler categoryDTOAssembler;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		if (modelAndView != null) {
			// No need to provide navigation for Ajax Request/Response
			if (!isXmlHttpRequest(request)) {
				ModelMap modelMap = modelAndView.getModelMap();
				String viewName = modelAndView.getViewName();
				// Add good old Struts namespace and action to model map
				String namespace = getNamespace(viewName);
				modelMap.addAttribute("topNavNamespace", namespace);
				String action = getAction(viewName);
				modelMap.addAttribute("topNavAction", action);
				// Fetch request parameters
				Long pictureId = NumberUtils.createLong(request.getParameter(ControllerConstants.REQUEST_PARAM_PICTURE_ID));
				Long categoryId = NumberUtils.createLong(request.getParameter(ControllerConstants.REQUEST_PARAM_CATEGORY_ID));
				Long catalogId = NumberUtils.createLong(request.getParameter(ControllerConstants.REQUEST_PARAM_CATALOG_ID));
				// Try to translate namespace and action into catalog and categoy for special content
				// TODO Replace hard coded mapping by db driven relations from navigation presentation to content handlers.
				if (catalogId == null && "/travels".equals(namespace)) {
					catalogId = galleryService.getCatalog(CatalogName.TRAVELS.toString()).getId();
				}
				if (categoryId == null && "displayBalticSea2014".equals(action)) {
					categoryId = galleryService.getCategory("baltic_sea").getId();
				}
				// Try to fetch current category ID on a possible given picture ID
				if (categoryId == null) {
					if (pictureId != null) {
						Picture picture = galleryService.getPicture(pictureId);
						if (picture != null) {
							categoryId = picture.getCategory().getId();
						}
					}
				}
				// Try to fetch current catalog ID on a category ID
				if (catalogId == null) {
					if (categoryId != null) {
						Category category = galleryService.getCategory(categoryId);
						if (category != null) {
							catalogId = category.getCatalog().getId();
						}
					}
				}
				// Add IDs to model map
				modelMap.addAttribute("topNavPictureId", pictureId);
				modelMap.addAttribute("topNavCategoryId", categoryId);
				modelMap.addAttribute("topNavCatalogId", catalogId);
				// Fetch top navigation catalogs and categories and build DTO matrix.
				List<Catalog> catalogs = galleryService.getCatalogsWithCategories(true);
				List<BasicCatalogDTO> catalogDTOs = new ArrayList<BasicCatalogDTO>(catalogs.size());
				for (Catalog catalog : catalogs) {
					BasicCatalogDTO catalogDTO = catalogDTOAssembler.toBasicDTO(catalog);
					catalogDTOs.add(catalogDTO);
					// Filter categories here, because in JPA conditions for child entities are not available.
					List<BasicCategoryDTO> categoryDTOs = new ArrayList<BasicCategoryDTO>();
					for (Category category : catalog.getCategories()) {
						if (category.getVisible()) {
							categoryDTOs.add(categoryDTOAssembler.toBasicDTO(category));
						}
					}
					Collections.sort(categoryDTOs, Comparator.comparingDouble(BasicCategoryDTO::getSequence));
					catalogDTO.addCategories(categoryDTOs);
				}
				modelMap.addAttribute("topNavCatalogs", catalogDTOs);
			}
		}
	}

	private static String getNamespace(String viewName) {
		int lastSeparator = viewName.lastIndexOf("/");
		String namespace = lastSeparator > 0 ? viewName.substring(0, lastSeparator) : "";
		if (!namespace.startsWith("/"))
			namespace = "/" + namespace;
		return namespace;
	}

	private static String getAction(String viewName) {
		int lastSeparator = viewName.lastIndexOf("/");
		return viewName.substring(lastSeparator + 1);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// Application context unsued yet.
	}

	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}

	@Autowired
	public void setCatalogDTOAssembler(CatalogDTOAssembler catalogDTOAssembler) {
		this.catalogDTOAssembler = catalogDTOAssembler;
	}

	@Autowired
	public void setCategoryDTOAssembler(CategoryDTOAssembler categoryDTOAssembler) {
		this.categoryDTOAssembler = categoryDTOAssembler;
	}

}
