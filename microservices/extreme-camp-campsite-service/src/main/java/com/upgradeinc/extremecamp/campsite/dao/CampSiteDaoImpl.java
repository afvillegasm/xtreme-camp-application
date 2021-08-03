package com.upgradeinc.extremecamp.campsite.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upgradeinc.extremecamp.campsite.common.Constants;
import com.upgradeinc.extremecamp.campsite.entity.CampSite;

@Repository
public class CampSiteDaoImpl implements CampSiteDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void create(CampSite entity) throws Exception {
		
		em.persist(entity);
		
	}

	@Override
	@Transactional
	public void update(CampSite entity) throws Exception {
		
		em.merge(entity);
		
	}

	@Override
	@Transactional
	public void delete(CampSite entity) throws Exception {
		
		em.merge(entity);
		
	}

	@Override
	public List<CampSite> findAll() throws Exception {
		
		Query q = em.createNamedQuery("CampSite.findAll", CampSite.class);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		
		return q.getResultList();
		
	}

	@Override
	public CampSite findById(Long id) throws Exception {
		
		Query q = em.createNamedQuery("CampSite.findById", CampSite.class);
		q.setParameter("id", id);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		List<CampSite> lst = (List<CampSite>)q.getResultList();
		
		if(lst != null && lst.size() > 0) {
			return lst.get(0);
		}
		
		return null;
		
	}

	@Override
	public List<CampSite> findByName(String name) throws Exception {
		
		Query q = em.createNamedQuery("CampSite.findByName", CampSite.class);
		q.setParameter("name", name);
		return q.getResultList();
		
	}

}
