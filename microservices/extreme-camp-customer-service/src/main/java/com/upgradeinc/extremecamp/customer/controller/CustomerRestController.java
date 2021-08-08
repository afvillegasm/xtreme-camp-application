package com.upgradeinc.extremecamp.customer.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.upgradeinc.extremecamp.customer.dto.CustomerCRUDResponseDTO;
import com.upgradeinc.extremecamp.customer.dto.CustomerDTO;
import com.upgradeinc.extremecamp.customer.dto.CustomerListResponseDTO;
import com.upgradeinc.extremecamp.customer.service.CustomerService;

@RestController
@Validated
@RequestMapping(path = "/extreme-camp/customer-service")
public class CustomerRestController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(path = {"/customers/create"},method = RequestMethod.POST)
	public ResponseEntity<CustomerCRUDResponseDTO> create(@Valid @RequestBody CustomerDTO request){
		
		CustomerCRUDResponseDTO response = customerService.create(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CustomerCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CustomerCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@RequestMapping(path = {"/customers/update/{id}"},method = RequestMethod.PUT)
	public ResponseEntity<CustomerCRUDResponseDTO> update(@PathVariable(name = "id") Long id,@Valid @RequestBody CustomerDTO request){
		
		request.setId(id);
		CustomerCRUDResponseDTO response = customerService.update(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CustomerCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CustomerCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/customers/delete/{id}"},method = RequestMethod.DELETE)
	public ResponseEntity<CustomerCRUDResponseDTO> delete(@PathVariable(name = "id") Long id){
		
		CustomerDTO request = new CustomerDTO();
		
		request.setId(id);
		
		CustomerCRUDResponseDTO response = customerService.delete(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CustomerCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CustomerCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/customers"},method = RequestMethod.GET)
	public ResponseEntity<CustomerListResponseDTO> findAll(){
		
		CustomerListResponseDTO response = customerService.findAll();
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CustomerListResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CustomerListResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/customers/id/{id}"},method = RequestMethod.GET)
	public ResponseEntity<CustomerListResponseDTO> findById(@Valid @NotNull(message = "{validation.error.message.id.notempty}") @PathVariable(name = "id") Long id){
		
		CustomerListResponseDTO response = customerService.findById(id);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CustomerListResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CustomerListResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/customers/email/{email}"},method = RequestMethod.GET)
	public ResponseEntity<CustomerListResponseDTO> findByEmail(@Valid 
															   @Email(message = "{validation.error.message.email.invalid}") 
															   @PathVariable(name = "email") String email){
		
		CustomerListResponseDTO response = customerService.findByEmail(email);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CustomerListResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CustomerListResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
