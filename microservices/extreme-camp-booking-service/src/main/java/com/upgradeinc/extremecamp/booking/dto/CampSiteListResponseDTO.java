package com.upgradeinc.extremecamp.booking.dto;

import java.util.List;

public class CampSiteListResponseDTO {
	
	List<CampSiteDTO> results;
	ErrorStatusDTO status;
	
	public List<CampSiteDTO> getResults() {
		return results;
	}
	public void setResults(List<CampSiteDTO> results) {
		this.results = results;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}
	
	

}
