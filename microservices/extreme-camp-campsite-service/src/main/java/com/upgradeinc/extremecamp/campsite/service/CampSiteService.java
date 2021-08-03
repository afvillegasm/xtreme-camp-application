package com.upgradeinc.extremecamp.campsite.service;

import com.upgradeinc.extremecamp.campsite.dto.CampSiteCRUDResponseDTO;
import com.upgradeinc.extremecamp.campsite.dto.CampSiteDTO;
import com.upgradeinc.extremecamp.campsite.dto.CampSiteListResponseDTO;

public interface CampSiteService {
	
	public CampSiteCRUDResponseDTO create(CampSiteDTO dto);
	public CampSiteCRUDResponseDTO update(CampSiteDTO dto);
	public CampSiteCRUDResponseDTO delete(CampSiteDTO dto);
	public CampSiteListResponseDTO findAll();
	public CampSiteListResponseDTO findById(Long idCampSite);

}
