package de.tikron.manager.bean.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tikru.commons.jpa.domain.Entity;

/**
 * Bean providing basic functions for a data list with a selection column.
 * 
 * @param <T> The type of list list data.
 * @param <Serializable> The type of Serializables (the primary key) of the list data rows.
 *
 * @date 24.01.2012
 * @author Titus Kruse
 */
public class AbstractSelectableListBean<T extends Entity<ID>, ID extends Serializable> extends AbstractListBean<T> {

	private static final long serialVersionUID = 745126489681509651L;
	
	private Map<ID, Boolean> selectedIds = new HashMap<ID, Boolean>();

	/**
	 * Returns a list of selected T and clears the selection.
	 * 
	 * @return The list of T.
	 */
	public List<T> getSelectedItems() {
		List<T> selectedDataList = new ArrayList<T>();
		for (T dataItem : getList()) {
			if (selectedIds.get(dataItem.getId()).booleanValue()) {
				selectedDataList.add(dataItem);
				selectedIds.remove(dataItem.getId());
			}
		}
		return selectedDataList;
	}

	public void selectAll() {
		selectedIds.clear();
		for (T dataItem : getList()) {
			selectedIds.put(dataItem.getId(), Boolean.TRUE);
		}
	}

	public void selectNone() {
		selectedIds.clear();
	}

	/**
	 * Provides access to the selected Serializables.
	 * 
	 * @return A Map of Serializables and their state of selected or not.
	 */
	public Map<ID, Boolean> getSelectedIds() {
		return selectedIds;
	}

	/**
	 * Default action method to refresh the current list.
	 * 
	 * @return Faces-Navigation.
	 */
	public String refresh() {
		selectNone();
		return super.refresh();
	}

}
