package com.upgradeinc.extremecamp.campsite.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.upgradeinc.extremecamp.campsite.common.Constants;
import com.upgradeinc.extremecamp.campsite.dao.CampSiteDao;
import com.upgradeinc.extremecamp.campsite.dto.CampSiteCRUDResponseDTO;
import com.upgradeinc.extremecamp.campsite.dto.CampSiteDTO;
import com.upgradeinc.extremecamp.campsite.dto.CampSiteListResponseDTO;
import com.upgradeinc.extremecamp.campsite.dto.ErrorStatusDTO;
import com.upgradeinc.extremecamp.campsite.entity.CampSite;

@PropertySource("classpath:message.properties")
@Service
public class CampSiteServiceImpl implements CampSiteService{
	
	@Autowired
	CampSiteDao dao;
	
	@Autowired
	private Environment env;

	@Override
	public CampSiteCRUDResponseDTO create(CampSiteDTO dto) {
		
		try {
			
			SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy");
			isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			Calendar cinit = Calendar.getInstance();
			cinit.set(Calendar.HOUR_OF_DAY, 0);
			cinit.set(Calendar.MINUTE, 0);
			cinit.set(Calendar.SECOND, 0);
			cinit.set(Calendar.MILLISECOND, 0);
					
			String[] formDate = isoFormat.format(dto.getFoundationDate()).split("-");
			
			cinit.set(Calendar.DAY_OF_MONTH,Integer.parseInt(formDate[0]));
			cinit.set(Calendar.MONTH,Integer.parseInt(formDate[1]) - 1);
			cinit.set(Calendar.YEAR,Integer.parseInt(formDate[2]));
			
			List<CampSite> existsCampSiteWithName = dao.findByName(dto.getName());
			
			if(existsCampSiteWithName != null && existsCampSiteWithName.size() > 0) {
				
				/*validate if retrieved object is in status deleted*/
				CampSite obj = existsCampSiteWithName.get(0);
				if(obj.getStatus() != null && obj.getStatus().equals(Constants.DB_STATUS_DELETED)) {
					
					obj.setCreatedBy(env.getProperty("extremecamp.campsite.application.dbuser"));
					obj.setCreatedAt(new Date());
					obj.setModifiedBy(env.getProperty("extremecamp.campsite.application.dbuser"));
					obj.setModifiedAt(new Date());
					obj.setDescription(dto.getDescription());
					obj.setMaxNumReservationsPerDay(dto.getMaxNumReservationsPerDay());
					obj.setMaxNumDaysIncludedInDateRange(dto.getMaxNumDaysIncludedInDateRange());
					obj.setFoundationDate(cinit.getTime());
					obj.setStatus(Constants.DB_STATUS_ACTIVE);
					
					dao.update(obj);
					
					CampSiteCRUDResponseDTO response = new CampSiteCRUDResponseDTO();
					
					response.setIdCampSite(obj.getId());
					ErrorStatusDTO status = new ErrorStatusDTO();
					status.setErrorCode(env.getProperty("status.error.code.campsite.created.successful"));
					status.setErrorMessage(env.getProperty("status.error.message.campsite.created.successful"));
					status.setHttpStatusCode(HttpStatus.OK.value());
					response.setStatus(status);
					
					return response;
					
				}
				
				CampSiteCRUDResponseDTO errorResponse = new CampSiteCRUDResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.already.exists"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.already.exists"));
				status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
			CampSite entity = new CampSite();
			
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setFoundationDate(cinit.getTime());
			entity.setStatus(Constants.DB_STATUS_ACTIVE);
			entity.setMaxNumReservationsPerDay(dto.getMaxNumReservationsPerDay());
			entity.setMaxNumDaysIncludedInDateRange(dto.getMaxNumDaysIncludedInDateRange());
			entity.setCreatedBy(env.getProperty("extremecamp.campsite.application.dbuser"));
			entity.setCreatedAt(new Date());
		
			dao.create(entity);
			
			CampSiteCRUDResponseDTO response = new CampSiteCRUDResponseDTO();
			
			response.setIdCampSite(entity.getId());
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.campsite.created.successful"));
			status.setErrorMessage(env.getProperty("status.error.message.campsite.created.successful"));
			status.setHttpStatusCode(HttpStatus.OK.value());
			response.setStatus(status);
			
			return response;
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CampSiteCRUDResponseDTO errorResponse = new CampSiteCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.campsite.create.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.campsite.create.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CampSiteCRUDResponseDTO update(CampSiteDTO dto) {
		
		try {
			
			SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy");
			isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			Calendar cinit = Calendar.getInstance();
			cinit.set(Calendar.HOUR_OF_DAY, 0);
			cinit.set(Calendar.MINUTE, 0);
			cinit.set(Calendar.SECOND, 0);
			cinit.set(Calendar.MILLISECOND, 0);
					
			String[] formDate = isoFormat.format(dto.getFoundationDate()).split("-");
			
			cinit.set(Calendar.DAY_OF_MONTH,Integer.parseInt(formDate[0]));
			cinit.set(Calendar.MONTH,Integer.parseInt(formDate[1]) - 1);
			cinit.set(Calendar.YEAR,Integer.parseInt(formDate[2]));
			
			CampSite existsCampSiteWithName = dao.findById(dto.getId());
			
			if(existsCampSiteWithName != null) {
				
				existsCampSiteWithName.setName(dto.getName());
				existsCampSiteWithName.setDescription(dto.getDescription());
				existsCampSiteWithName.setFoundationDate(cinit.getTime());
				existsCampSiteWithName.setStatus(Constants.DB_STATUS_MODIFIED);
				existsCampSiteWithName.setMaxNumReservationsPerDay(dto.getMaxNumReservationsPerDay());
				existsCampSiteWithName.setMaxNumDaysIncludedInDateRange(dto.getMaxNumDaysIncludedInDateRange());
				existsCampSiteWithName.setModifiedBy(env.getProperty("extremecamp.campsite.application.dbuser"));
				existsCampSiteWithName.setModifiedAt(new Date());
			
				dao.update(existsCampSiteWithName);
				
				CampSiteCRUDResponseDTO response = new CampSiteCRUDResponseDTO();
				
				response.setIdCampSite(existsCampSiteWithName.getId());
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.updated.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.updated.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CampSiteCRUDResponseDTO errorResponse = new CampSiteCRUDResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.doesnt.exist"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.doesnt.exist"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CampSiteCRUDResponseDTO errorResponse = new CampSiteCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.campsite.update.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.campsite.update.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CampSiteCRUDResponseDTO delete(CampSiteDTO dto) {
		
		try {
			
			CampSite existsCampSiteWithName = dao.findById(dto.getId());
			
			if(existsCampSiteWithName != null) {
				
				existsCampSiteWithName.setStatus(Constants.DB_STATUS_DELETED);
				existsCampSiteWithName.setModifiedBy(env.getProperty("extremecamp.campsite.application.dbuser"));
				existsCampSiteWithName.setModifiedAt(new Date());
			
				dao.delete(existsCampSiteWithName);
				
				CampSiteCRUDResponseDTO response = new CampSiteCRUDResponseDTO();
				
				response.setIdCampSite(existsCampSiteWithName.getId());
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.deleted.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.deleted.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CampSiteCRUDResponseDTO errorResponse = new CampSiteCRUDResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.doesnt.exist"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.doesnt.exist"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CampSiteCRUDResponseDTO errorResponse = new CampSiteCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.campsite.delete.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.campsite.delete.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CampSiteListResponseDTO findAll() {
		
		try {
			
			List<CampSite> lstExistingCampSites = dao.findAll();
			
			if(lstExistingCampSites != null && lstExistingCampSites.size() > 0) {
				
				CampSiteListResponseDTO response = new CampSiteListResponseDTO();
				
				//lstExistingCampSites.stream().map(x -> {new CampSiteDTO(x.getId(), x.getName(), x.getDescription(), x.getFoundationDate(), x.getMaxNumReservationsPerDay(), x.getCreatedBy());})
				response.setResults(new ArrayList<CampSiteDTO>());
				for(CampSite record: lstExistingCampSites) {
					
					CampSiteDTO obj = new CampSiteDTO(record.getId(), record.getName(), record.getDescription(), record.getFoundationDate(), record.getMaxNumReservationsPerDay(), record.getMaxNumDaysIncludedInDateRange());
					
					response.getResults().add(obj);
					
				}
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.retrieve.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.retrieve.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CampSiteListResponseDTO errorResponse = new CampSiteListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CampSiteListResponseDTO errorResponse = new CampSiteListResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.campsite.retrieve.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.campsite.retrieve.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CampSiteListResponseDTO findById(Long idCampSite) {
		
		try {
			
			CampSite existingCampSite = null;
			
			/*try {*/
				
			existingCampSite = dao.findById(idCampSite);
				
			/*} catch(NoResultException ex) {
				
				CampSiteListResponseDTO errorResponse = new CampSiteListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.notfound"));
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}*/
			
			if(existingCampSite != null) {
				
				CampSiteListResponseDTO response = new CampSiteListResponseDTO();
				
				//lstExistingCampSites.stream().map(x -> {new CampSiteDTO(x.getId(), x.getName(), x.getDescription(), x.getFoundationDate(), x.getMaxNumReservationsPerDay(), x.getCreatedBy());})
				response.setResults(new ArrayList<CampSiteDTO>());
				
					
				CampSiteDTO obj = new CampSiteDTO(existingCampSite.getId(), existingCampSite.getName(), existingCampSite.getDescription(), existingCampSite.getFoundationDate(), existingCampSite.getMaxNumReservationsPerDay(), existingCampSite.getMaxNumDaysIncludedInDateRange());
					
				response.getResults().add(obj);
					
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.retrieve.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.retrieve.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				CampSiteListResponseDTO errorResponse = new CampSiteListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			CampSiteListResponseDTO errorResponse = new CampSiteListResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.campsite.retrieve.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.campsite.retrieve.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

}
