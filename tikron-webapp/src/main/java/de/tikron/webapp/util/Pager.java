/**
 * Copyright (c) 2010 by Titus Kruse.
 */
package de.tikron.webapp.util;

import java.util.List;

/**
 * Stellt einen Pager zur Verfügung. Dem Pager wird eine Liste aus Objekten übergeben. Ein Cursor kann gesetzt werden.
 * Methoden liefern jeweils ein Element zu bestimmten Postionen in der Liste. equals() auf die Elemente wird angewendet,
 * um das aktuelle Element aufzufinden.
 * 
 * Hinweis: Um eine festgelegte Reihenfolge der Elemente und direkten Zugriff zu erhalten, wird List statt Set
 * verwendet. Enthält die Liste mehrere "gleiche" Elemente, wird auf das erte dieser Elemente positioniert.
 * 
 * Alternative: https://commons.apache.org/proper/commons-collections/javadocs/api-3.2.1/org/apache/commons/collections/list/SetUniqueList.html
 * 
 * @author Titus Kruse
 * @since 28.09.2010
 */
public class Pager<T> {

	private List<T> elements;

	private int index;

	public Pager(List<T> entities) {
		this.elements = entities;
		this.index = isEmpty() ? -1 : 0;
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public int getSize() {
		return elements.size();
	}

	public int getCurrentIndex() {
		return index;
	}

	public int getCurrentNumber() {
		return getCurrentIndex() + 1;
	}

	public int getFirstNumber() {
		return 1;
	}

	public int getLastNumber() {
		return getSize();
	}

	public void setCurrent(T element) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).equals(element)) {
				index = i;
				return;
			}
		}
		index = -1;
	}

	public T getCurrent() {
		return index < 0 ? null : elements.get(index);
	}

	public boolean getIsFirst() {
		return index == 0;
	}

	public T getFirst() {
		return elements.isEmpty() ? null : elements.get(0);
	}

	public boolean getHasPrevious() {
		return index > 0;
	}

	public T getPrevious() {
		return getHasPrevious() ? elements.get(index - 1) : null;
	}

	public boolean getHasNext() {
		return index < getSize() - 1;
	}

	public T getNext() {
		return getHasNext() ? elements.get(index + 1) : null;
	}

	public boolean getIsLast() {
		return index == getSize() - 1;
	}

	public T getLast() {
		return elements.isEmpty() ? null : elements.get(getSize() - 1);
	}

}
