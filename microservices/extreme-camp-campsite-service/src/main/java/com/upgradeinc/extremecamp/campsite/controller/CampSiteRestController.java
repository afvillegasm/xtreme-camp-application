package com.upgradeinc.extremecamp.campsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/extreme-camp/campsite-service")
public class CampSiteRestController {
	
	@Autowired
	CampSiteService campSiteService;
	
	@RequestMapping(path = {"/campsites/create"},method = RequestMethod.POST)
	public ResponseEntity<CampSiteCRUDResponseDTO> create(@RequestBody CampSiteDTO request){
		
		request.setUsername("ADMIN");/*FIXME PENDING LOGIN AUTHENTICATION*/
		CampSiteCRUDResponseDTO response = campSiteService.create(request);
		
		return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(path = {"/campsites/update/{id}"},method = RequestMethod.PUT)
	public ResponseEntity<CampSiteCRUDResponseDTO> update(@PathVariable(name = "id") Long id, @RequestBody CampSiteDTO request){
		
		request.setUsername("ADMIN");/*FIXME PENDING LOGIN AUTHENTICATION*/
		CampSiteCRUDResponseDTO response = campSiteService.update(request);
		
		return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.OK);
		
	}
	
	@RequestMapping(path = {"/campsites/delete/{id}"},method = RequestMethod.DELETE)
	public ResponseEntity<CampSiteCRUDResponseDTO> delete(@PathVariable(name = "id") Long id){
		
		CampSiteDTO request = new CampSiteDTO();
		
		request.setUsername("ADMIN");/*FIXME PENDING LOGIN AUTHENTICATION*/
		request.setId(id);
		
		CampSiteCRUDResponseDTO response = campSiteService.delete(request);
		
		return new ResponseEntity<CampSiteCRUDResponseDTO>(response, HttpStatus.OK);
		
	}
	
	@RequestMapping(path = {"/campsites"},method = RequestMethod.GET)
	public ResponseEntity<CampSiteListResponseDTO> findAll(){
		
		CampSiteListResponseDTO response = campSiteService.findAll();
		
		return new ResponseEntity<CampSiteListResponseDTO>(response, HttpStatus.OK);
		
	}
	
	@RequestMapping(path = {"/campsites/{id}"},method = RequestMethod.GET)
	public ResponseEntity<CampSiteListResponseDTO> findById(@PathVariable(name = "id") Long id){
		
		CampSiteListResponseDTO response = campSiteService.findById(id);
		
		return new ResponseEntity<CampSiteListResponseDTO>(response, HttpStatus.OK);
		
	}

}
