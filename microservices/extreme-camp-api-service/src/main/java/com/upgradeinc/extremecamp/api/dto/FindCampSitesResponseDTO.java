package com.upgradeinc.extremecamp.api.dto;

import java.util.List;

public class FindCampSitesResponseDTO {
	
	List<CampSiteInfoDTO> results;
	ErrorStatusDTO status;
	
	public List<CampSiteInfoDTO> getResults() {
		return results;
	}
	public void setResults(List<CampSiteInfoDTO> results) {
		this.results = results;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}

}
