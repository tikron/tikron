package de.tikron.persistence.dao.misc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.tikron.persistence.model.misc.Teaser;
import de.tikru.commons.jpa.dao.GenericJpaDao;

public class TeaserDaoJpaImpl extends GenericJpaDao<Teaser, Long> implements TeaserDao {

	public TeaserDaoJpaImpl(EntityManager entityManager) {
		super(Teaser.class, entityManager);
	}

	public TeaserDaoJpaImpl() {
		super(Teaser.class);
	}

	@Override
	public Teaser findByName(String name) {
		TypedQuery<Teaser> query = entityManager.createNamedQuery(Teaser.NQ_FIND_BY_NAME, Teaser.class);
		query.setParameter("name", name);
		return singleResultOrNull(query);
	}

	@Override
	public List<Teaser> findAllOrderByName() {
		TypedQuery<Teaser> query = entityManager.createNamedQuery(Teaser.NQ_FIND_ALL_ORDERBY_NAME, Teaser.class);
		return query.getResultList();
	}

	@Override
	public List<Teaser> findAllOrderBySquence() {
		TypedQuery<Teaser> query = entityManager.createNamedQuery(Teaser.NQ_FIND_ALL_ORDERBY_SEQUENCE, Teaser.class);
		return query.getResultList();
	}

	@Override
	public List<Teaser> findVisibleOrderBySquence() {
		TypedQuery<Teaser> query = entityManager.createNamedQuery(Teaser.NQ_FIND_VISIBLE_ORDERBY_SEQUENCE, Teaser.class);
		return query.getResultList();
	}

}
