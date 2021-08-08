package com.upgradeinc.extremecamp.campsite.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

import com.upgradeinc.extremecamp.campsite.dto.CampSiteCRUDResponseDTO;
import com.upgradeinc.extremecamp.campsite.dto.CampSiteDTO;
import com.upgradeinc.extremecamp.campsite.dto.CampSiteListResponseDTO;
import com.upgradeinc.extremecamp.campsite.service.CampSiteService;

@RestController
@Validated
@RequestMapping(path = "/extreme-camp/campsite-service")
public class CampSiteRestController {
	
	@Autowired
	CampSiteService campSiteService;
	
	@RequestMapping(path = {"/campsites/create"},method = RequestMethod.POST)
	public ResponseEntity<CampSiteCRUDResponseDTO> create(@Valid @RequestBody CampSiteDTO request){
		
		CampSiteCRUDResponseDTO response = campSiteService.create(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/campsites/update/{id}"},method = RequestMethod.PUT)
	public ResponseEntity<CampSiteCRUDResponseDTO> update(@PathVariable(name = "id") Long id, 
														  @Valid @RequestBody CampSiteDTO request){
		
		request.setId(id);
		CampSiteCRUDResponseDTO response = campSiteService.update(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/campsites/delete/{id}"},method = RequestMethod.DELETE)
	public ResponseEntity<CampSiteCRUDResponseDTO> delete(@PathVariable(name = "id") Long id){
		
		CampSiteDTO request = new CampSiteDTO();
		
		request.setId(id);
		
		CampSiteCRUDResponseDTO response = campSiteService.delete(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/campsites"},method = RequestMethod.GET)
	public ResponseEntity<CampSiteListResponseDTO> findAll(){
		
		CampSiteListResponseDTO response = campSiteService.findAll();
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CampSiteListResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CampSiteListResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/campsites/{id}"},method = RequestMethod.GET)
	public ResponseEntity<CampSiteListResponseDTO> findById(@PathVariable(name = "id") Long id){
		
		CampSiteListResponseDTO response = campSiteService.findById(id);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CampSiteListResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CampSiteListResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
