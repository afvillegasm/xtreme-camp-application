package com.upgradeinc.extremecamp.customer.service;

import com.upgradeinc.extremecamp.customer.dto.CustomerCRUDResponseDTO;
import com.upgradeinc.extremecamp.customer.dto.CustomerDTO;
import com.upgradeinc.extremecamp.customer.dto.CustomerListResponseDTO;
import com.upgradeinc.extremecamp.customer.dto.UpdateCustomerRequestDTO;

public interface CustomerService {

	public CustomerCRUDResponseDTO create(CustomerDTO dto);
	public CustomerCRUDResponseDTO update(UpdateCustomerRequestDTO dto);
	public CustomerCRUDResponseDTO delete(CustomerDTO dto);
	public CustomerListResponseDTO findAll();
	public CustomerListResponseDTO findById(Long idCustomer);
	public CustomerListResponseDTO findByEmail(String emailCustomer);
	
}
