/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.common;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 * Bean providing basic functions for data lists.
 * 
 * @param <T> The type of list list data.
 *
 * @date 24.01.2012
 * @author Titus Kruse
 */
public abstract class AbstractListBean<T> extends BaseBean {

	private List<T> list;

	private DataModel<T> model;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
		this.model = null;
	}

	public DataModel<T> getModel() {
		if (model == null) {
			model = new ListDataModel<T>(getList());
		}
		return model;
	}

}
