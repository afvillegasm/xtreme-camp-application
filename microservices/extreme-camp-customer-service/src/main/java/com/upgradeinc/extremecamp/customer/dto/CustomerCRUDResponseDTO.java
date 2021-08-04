package com.upgradeinc.extremecamp.customer.dto;

import com.upgradeinc.extremecamp.customer.dto.ErrorStatusDTO;

public class CustomerCRUDResponseDTO {
	
	private Long idCustomer;
	ErrorStatusDTO status;
	
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}

}
