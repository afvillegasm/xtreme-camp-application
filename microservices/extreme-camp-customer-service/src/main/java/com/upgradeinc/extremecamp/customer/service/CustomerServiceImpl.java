package com.upgradeinc.extremecamp.customer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.upgradeinc.extremecamp.customer.common.Constants;
import com.upgradeinc.extremecamp.customer.dao.CustomerDao;
import com.upgradeinc.extremecamp.customer.dto.CustomerCRUDResponseDTO;
import com.upgradeinc.extremecamp.customer.dto.ErrorStatusDTO;
import com.upgradeinc.extremecamp.customer.dto.UpdateCustomerRequestDTO;
import com.upgradeinc.extremecamp.customer.entity.Customer;
import com.upgradeinc.extremecamp.customer.dto.CustomerCRUDResponseDTO;
import com.upgradeinc.extremecamp.customer.dto.CustomerDTO;
import com.upgradeinc.extremecamp.customer.dto.CustomerListResponseDTO;
import com.upgradeinc.extremecamp.customer.entity.Customer;

@PropertySource("classpath:message.properties")
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao dao;
	
	@Autowired
	private Environment env;

	@Override
	public CustomerCRUDResponseDTO create(CustomerDTO dto) {
		
		try {
			
			List<Customer> existsCustomerWithEmail = dao.findByEmail(dto.getEmail());
			
			if(existsCustomerWithEmail != null && existsCustomerWithEmail.size() > 0) {
				
				/*validate if retrieved object is in status deleted*/
				Customer obj = existsCustomerWithEmail.get(0);
				if(obj.getStatus() != null && obj.getStatus().equals(Constants.DB_STATUS_DELETED)) {
					
					obj.setCreatedBy(env.getProperty("extremecamp.customer.application.dbuser"));
					obj.setCreatedAt(new Date());
					obj.setModifiedBy(env.getProperty("extremecamp.customer.application.dbuser"));
					obj.setModifiedAt(new Date());
					obj.setFirstName(dto.getFirstName());
					obj.setLastName(dto.getLastName());
					obj.setStatus(Constants.DB_STATUS_ACTIVE);
					
					dao.update(obj);
					
					CustomerCRUDResponseDTO response = new CustomerCRUDResponseDTO();
					
					response.setIdCustomer(obj.getId());
					ErrorStatusDTO status = new ErrorStatusDTO();
					status.setErrorCode(env.getProperty("status.error.code.customer.created.successful"));
					status.setErrorMessage(env.getProperty("status.error.message.customer.created.successful"));
					status.setHttpStatusCode(HttpStatus.OK.value());
					response.setStatus(status);
					
					return response;
					
				}
				
				CustomerCRUDResponseDTO errorResponse = new CustomerCRUDResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.already.exists"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.already.exists"));
				status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
			Customer entity = new Customer();
			
			entity.setFirstName(dto.getFirstName());
			entity.setLastName(dto.getLastName());
			entity.setEmail(dto.getEmail());
			entity.setStatus(Constants.DB_STATUS_ACTIVE);
			entity.setCreatedBy(env.getProperty("extremecamp.customer.application.dbuser"));
			entity.setCreatedAt(new Date());
		
			dao.create(entity);
			
			CustomerCRUDResponseDTO response = new CustomerCRUDResponseDTO();
			
			response.setIdCustomer(entity.getId());
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.created.successful"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.created.successful"));
			status.setHttpStatusCode(HttpStatus.OK.value());
			response.setStatus(status);
			
			return response;
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CustomerCRUDResponseDTO errorResponse = new CustomerCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.create.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.create.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CustomerCRUDResponseDTO update(UpdateCustomerRequestDTO dto) {
		
		try {
			
			Customer existsCustomerWithId = dao.findById(dto.getId());
			
			if(existsCustomerWithId != null) {
				
				existsCustomerWithId.setFirstName(dto.getFirstName());
				existsCustomerWithId.setLastName(dto.getLastName());
				/*existsCustomerWithId.setEmail(dto.getEmail());*/
				existsCustomerWithId.setStatus(Constants.DB_STATUS_MODIFIED);
				existsCustomerWithId.setModifiedBy(env.getProperty("extremecamp.customer.application.dbuser"));
				existsCustomerWithId.setModifiedAt(new Date());
			
				dao.update(existsCustomerWithId);
				
				CustomerCRUDResponseDTO response = new CustomerCRUDResponseDTO();
				
				response.setIdCustomer(existsCustomerWithId.getId());
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.updated.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.updated.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CustomerCRUDResponseDTO errorResponse = new CustomerCRUDResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.doesnt.exist"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.doesnt.exist"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CustomerCRUDResponseDTO errorResponse = new CustomerCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.update.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.update.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CustomerCRUDResponseDTO delete(CustomerDTO dto) {
		
		try {
			
			Customer existsCustomerWithId = dao.findById(dto.getId());
			
			if(existsCustomerWithId != null) {
				
				existsCustomerWithId.setStatus(Constants.DB_STATUS_DELETED);
				existsCustomerWithId.setModifiedBy(env.getProperty("extremecamp.customer.application.dbuser"));
				existsCustomerWithId.setModifiedAt(new Date());
			
				dao.delete(existsCustomerWithId);
				
				CustomerCRUDResponseDTO response = new CustomerCRUDResponseDTO();
				
				response.setIdCustomer(existsCustomerWithId.getId());
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.deleted.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.deleted.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CustomerCRUDResponseDTO errorResponse = new CustomerCRUDResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.doesnt.exist"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.doesnt.exist"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CustomerCRUDResponseDTO errorResponse = new CustomerCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.delete.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.delete.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CustomerListResponseDTO findAll() {
		
		try {
			
			List<Customer> lstExistingCustomers = dao.findAll();
			
			if(lstExistingCustomers != null && lstExistingCustomers.size() > 0) {
				
				CustomerListResponseDTO response = new CustomerListResponseDTO();
				
				response.setResults(lstExistingCustomers.stream().map(x -> new CustomerDTO(x.getId(), x.getFirstName(), x.getLastName(), x.getEmail())).collect(Collectors.toList()));
				/*response.setResults(new ArrayList<CustomerDTO>());
				for(Customer record: lstExistingCustomers) {
					
					CustomerDTO obj = new CustomerDTO(record.getId(), record.getFirstName(), record.getLastName(), record.getEmail());
										
					response.getResults().add(obj);
					
				}*/
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.retrieve.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.retrieve.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CustomerListResponseDTO errorResponse = new CustomerListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CustomerListResponseDTO errorResponse = new CustomerListResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.retrieve.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.retrieve.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CustomerListResponseDTO findById(Long idCampSite) {
		
		try {
			
			Customer existingCustomer = null;
			
			/*try {*/
				
			existingCustomer = dao.findById(idCampSite);
				
			/*} catch(NoResultException ex) {
				
				CampSiteListResponseDTO errorResponse = new CampSiteListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.notfound"));
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}*/
			
			if(existingCustomer != null) {
				
				CustomerListResponseDTO response = new CustomerListResponseDTO();
				
				//lstExistingCampSites.stream().map(x -> {new CampSiteDTO(x.getId(), x.getName(), x.getDescription(), x.getFoundationDate(), x.getMaxNumReservationsPerDay(), x.getCreatedBy());})
				response.setResults(new ArrayList<CustomerDTO>());
				
					
				CustomerDTO obj = new CustomerDTO(existingCustomer.getId(), existingCustomer.getFirstName(), existingCustomer.getLastName(), existingCustomer.getEmail());
					
				response.getResults().add(obj);
					
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.retrieve.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.retrieve.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CustomerListResponseDTO errorResponse = new CustomerListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CustomerListResponseDTO errorResponse = new CustomerListResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.retrieve.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.retrieve.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CustomerListResponseDTO findByEmail(String emailCustomer) {
		
		try {
			
			List<Customer> lstExistingCustomers = dao.findByEmail(emailCustomer);
			
			if(lstExistingCustomers != null && lstExistingCustomers.size() > 0) {
				
				CustomerListResponseDTO response = new CustomerListResponseDTO();
				
				response.setResults(lstExistingCustomers.stream().map(x -> new CustomerDTO(x.getId(), x.getFirstName(), x.getLastName(), x.getEmail())).collect(Collectors.toList()));
				/*response.setResults(new ArrayList<CustomerDTO>());
				for(Customer record: lstExistingCustomers) {
					
					CustomerDTO obj = new CustomerDTO(record.getId(), record.getFirstName(), record.getLastName(), record.getEmail());
										
					response.getResults().add(obj);
					
				}*/
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.retrieve.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.retrieve.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CustomerListResponseDTO errorResponse = new CustomerListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.customer.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CustomerListResponseDTO errorResponse = new CustomerListResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.retrieve.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.retrieve.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

}
