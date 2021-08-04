package com.upgradeinc.extremecamp.customer.dao;

import java.util.List;

import com.upgradeinc.extremecamp.customer.entity.Customer;

public interface CustomerDao {
	
	public void create(Customer entity) throws Exception;
	public void update(Customer entity) throws Exception;
	public void delete(Customer entity) throws Exception;
	public List<Customer> findAll() throws Exception;
	public Customer findById(Long id) throws Exception;
	public List<Customer> findByEmail(String email) throws Exception;

}
