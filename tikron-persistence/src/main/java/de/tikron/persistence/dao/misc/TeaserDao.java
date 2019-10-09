package de.tikron.persistence.dao.misc;

import java.util.List;

import de.tikron.jpa.dao.GenericDao;
import de.tikron.persistence.model.misc.Teaser;

public interface TeaserDao extends GenericDao<Teaser, Long> {

	/**
	 * Finds the teaser with the given name.
	 * 
	 * @param name Der name of the teaser to find.
	 * 
	 * @return The teaser or null, if not found.
	 */
	public Teaser findByName(String name);
	
	/**
	 * Finds all teasers ordered by name.
	 * 
	 * @return A list of teasers.
	 */
	public List<Teaser> findAllOrderByName();
	
	/**
	 * Finds all teasers ordered by squence and starting date.
	 * 
	 * @return A list of teasers.
	 */
	public List<Teaser> findAllOrderBySquence();
	
	/**
	 * Finds currently visible teasers ordered by squence and starting date.
	 * 
	 * @return A list of teasers.
	 */
	public List<Teaser> findVisibleOrderBySquence();

}
