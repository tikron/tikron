/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.tikron.manager.bean.common.NavigationBean;
import de.tikron.manager.navigation.ChildrenProvider;
import de.tikron.manager.navigation.LoadableNode;
import de.tikron.manager.navigation.Node;
import de.tikron.manager.navigation.NodeDescription;
import de.tikron.manager.navigation.SimpleNode;
import de.tikron.manager.service.common.Constants;
import de.tikron.manager.service.gallery.CatalogService;
import de.tikron.manager.service.gallery.CategoryService;
import de.tikron.manager.service.gallery.PictureService;
import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikru.commons.faces.util.Message;

/**
 * Managed bean providing the navigation tree for catalogs and associated children.
 *
 * @since 21.01.2012
 * @author Titus Kruse
 */
@ManagedBean
@SessionScoped
public class GalleryNavigationBean implements NavigationBean {

	private static final String MESSAGE_NODE_CATALOGS = Message.getMessage("navigationNodeCatalogs");

	private static final String MESSAGE_NODE_CATEGORIES = Message.getMessage("navigationNodeCategories");

	private static final String MESSAGE_NODE_PICTURES = Message.getMessage("navigationNodePictures");

	@ManagedProperty(value = "#{catalogService}")
	private CatalogService catalogService;

	@ManagedProperty(value = "#{pictureService}")
	private PictureService pictureService;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	@Override
	public Node getNode() {
		return getCatalogsNode();
	}

	/**
	 * @return
	 */
	private Node getCatalogsNode() {
		NodeDescription description = new NodeDescription(MESSAGE_NODE_CATALOGS, Constants.ICON_FOLDER_CLOSED);
		ChildrenProvider childrenProvider = new ChildrenProvider() {
			public List<Node> getChildren() {
				List<Node> nodes = new ArrayList<Node>();
				List<Catalog> catalogs = catalogService.getAll();
				for (Catalog catalog : catalogs) {
					nodes.add(getCatalogNode(catalog));
				}
				return nodes;
			};
		};
		return new LoadableNode("catalogs", description, "/pages/gallery/manageCatalogs.xhtml", childrenProvider);
	}

	/**
	 * @return
	 */
	private Node getCatalogNode(final Catalog catalog) {
		String identifier = "catalog_" + catalog.getId().toString();
		NodeDescription description = new NodeDescription(catalog.getDisplayName(), Constants.ICON_DOCUMENT);
		String outcome = "/pages/gallery/editCatalog.xhtml?catalogId=" + catalog.getId();
		Node node = new SimpleNode(identifier, description, outcome);
		node.addChild(getCategoriesNode(catalog));
		return node;
	}

	/**
	 * @return
	 */
	private Node getCategoriesNode(final Catalog catalog) {
		NodeDescription description = new NodeDescription(MESSAGE_NODE_CATEGORIES, Constants.ICON_FOLDER_CLOSED);
		String outcome = "/pages/gallery/manageCategories.xhtml?catalogId=" + catalog.getId();
		ChildrenProvider childrenProvider = new ChildrenProvider() {
			public List<Node> getChildren() {
				List<Node> nodes = new ArrayList<Node>();
				List<Category> categories = categoryService.getCategories(catalog);
				for (Category category : categories) {
					nodes.add(getCategoryNode(category));
				}
				return nodes;
			};
		};
		return new LoadableNode("categories", description, outcome, childrenProvider);
	}

	/**
	 * @return
	 */
	private Node getCategoryNode(final Category category) {
		String identifier = "category_" + category.getId().toString();
		NodeDescription description = new NodeDescription(category.getDisplayName(), Constants.ICON_DOCUMENT);
		String outcome = "/pages/gallery/editCategory.xhtml?categoryId=" + category.getId();
		Node node = new SimpleNode(identifier, description, outcome);
		node.addChild(getPicturesNode(category));
		return node;
	}

	/**
	 * @return
	 */
	private Node getPicturesNode(final Category category) {
		NodeDescription description = new NodeDescription(MESSAGE_NODE_PICTURES, Constants.ICON_FOLDER_CLOSED);
		String outcome = "/pages/gallery/managePictures.xhtml?categoryId=" + category.getId();
		ChildrenProvider childrenProvider = new ChildrenProvider() {
			public List<Node> getChildren() {
				List<Node> nodes = new ArrayList<Node>();
				List<Picture> pictures = pictureService.getPictures(category);
				for (Picture picture : pictures) {
					nodes.add(getPictureNode(picture));
				}
				return nodes;
			};
		};
		return new LoadableNode("pictures", description, outcome, childrenProvider);
	}

	/**
	 * @return
	 */
	private Node getPictureNode(final Picture picture) {
		String identifier = "picture_" + picture.getId().toString();
		NodeDescription description = new NodeDescription(picture.getDisplayName(), Constants.ICON_DOCUMENT);
		String outcome = "/pages/gallery/editPicture.xhtml?pictureId=" + picture.getId();
		return new SimpleNode(identifier, description, outcome);
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
