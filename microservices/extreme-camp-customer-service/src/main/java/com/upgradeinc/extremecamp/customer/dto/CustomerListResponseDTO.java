package com.upgradeinc.extremecamp.customer.dto;

import java.util.List;

import com.upgradeinc.extremecamp.customer.dto.CustomerDTO;
import com.upgradeinc.extremecamp.customer.dto.ErrorStatusDTO;

public class CustomerListResponseDTO {
	
	List<CustomerDTO> results;
	ErrorStatusDTO status;
	
	public List<CustomerDTO> getResults() {
		return results;
	}
	public void setResults(List<CustomerDTO> results) {
		this.results = results;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}

}
