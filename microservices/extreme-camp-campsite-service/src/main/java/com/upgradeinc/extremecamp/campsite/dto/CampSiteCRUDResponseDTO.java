package com.upgradeinc.extremecamp.campsite.dto;

public class CampSiteCRUDResponseDTO {
	
	private Long idCampSite;
	ErrorStatusDTO status;
	
	public Long getIdCampSite() {
		return idCampSite;
	}
	public void setIdCampSite(Long idCampSite) {
		this.idCampSite = idCampSite;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}
	
}
