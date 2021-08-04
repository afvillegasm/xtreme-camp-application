package com.upgradeinc.extremecamp.customer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upgradeinc.extremecamp.customer.common.Constants;
import com.upgradeinc.extremecamp.customer.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void create(Customer entity) throws Exception {
		
		em.persist(entity);
		
	}

	@Override
	@Transactional
	public void update(Customer entity) throws Exception {
		
		em.merge(entity);
		
	}

	@Override
	@Transactional
	public void delete(Customer entity) throws Exception {
		
		em.merge(entity);
		
	}

	@Override
	public List<Customer> findAll() throws Exception {
		
		Query q = em.createNamedQuery("Customer.findAll", Customer.class);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		
		return q.getResultList();
		
	}

	@Override
	public Customer findById(Long id) throws Exception {
		
		Query q = em.createNamedQuery("Customer.findById", Customer.class);
		q.setParameter("id", id);
		q.setParameter("status", Constants.DB_STATUS_DELETED);
		List<Customer> lst = (List<Customer>)q.getResultList();
		
		if(lst != null && lst.size() > 0) {
			return lst.get(0);
		}
		
		return null;
		
	}

	@Override
	public List<Customer> findByEmail(String email) throws Exception {
		
		Query q = em.createNamedQuery("Customer.findByEmail", Customer.class);
		q.setParameter("email", email);
		return q.getResultList();
		
	}

}
