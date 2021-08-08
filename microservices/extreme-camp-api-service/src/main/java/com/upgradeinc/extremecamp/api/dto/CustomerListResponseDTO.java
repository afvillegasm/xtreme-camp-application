package com.upgradeinc.extremecamp.api.dto;

import java.util.List;

import com.upgradeinc.extremecamp.api.dto.CustomerDTO;
import com.upgradeinc.extremecamp.api.dto.ErrorStatusDTO;

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
