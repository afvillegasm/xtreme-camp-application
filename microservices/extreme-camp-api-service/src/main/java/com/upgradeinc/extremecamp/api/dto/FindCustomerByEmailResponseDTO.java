package com.upgradeinc.extremecamp.api.dto;

import java.util.List;

public class FindCustomerByEmailResponseDTO {
	
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
