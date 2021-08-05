package com.upgradeinc.extremecamp.booking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.upgradeinc.extremecamp.booking.dto.BookingAvailabilityForCampSiteDateRangeRequestDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingAvailabilityForCampSiteDateRangeResponseDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingCRUDResponseDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingListResponseDTO;
import com.upgradeinc.extremecamp.booking.dto.CampSiteDTO;
import com.upgradeinc.extremecamp.booking.dto.CampSiteListResponseDTO;
import com.upgradeinc.extremecamp.booking.entity.Booking;
import com.upgradeinc.extremecamp.booking.common.Constants;
import com.upgradeinc.extremecamp.booking.dao.BookingDao;
import com.upgradeinc.extremecamp.booking.dto.BookingCRUDResponseDTO;
import com.upgradeinc.extremecamp.booking.dto.ErrorStatusDTO;
import com.upgradeinc.extremecamp.booking.entity.Booking;

@Service
@PropertySource("classpath:message.properties")
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	BookingDao dao;
	
	@Autowired
	private Environment env;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public BookingCRUDResponseDTO create(BookingDTO dto) {
		
		try {
			
			List<Booking> existsBookingWithIdCampSiteIdCustomerBookingInitDateBookingEndDate = dao.findByIdCampSiteIdCustomerBookingInitDateBookingEndDate(dto.getIdCampSite(), dto.getIdCustomer(), dto.getBookingInitDate(), dto.getBookingEndDate());
			
			if(existsBookingWithIdCampSiteIdCustomerBookingInitDateBookingEndDate != null && existsBookingWithIdCampSiteIdCustomerBookingInitDateBookingEndDate.size() > 0) {
				
				Booking obj = existsBookingWithIdCampSiteIdCustomerBookingInitDateBookingEndDate.get(0);
				if(obj.getStatus() != null && !obj.getStatus().equals(Constants.DB_STATUS_DELETED)) {
				
					BookingCRUDResponseDTO errorResponse = new BookingCRUDResponseDTO();
					
					ErrorStatusDTO status = new ErrorStatusDTO();
					status.setErrorCode(env.getProperty("status.error.code.booking.already.exists"));
					status.setErrorMessage(env.getProperty("status.error.message.booking.already.exists"));
					status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
					errorResponse.setStatus(status);
					
					return errorResponse;
				
				}
				
			}
			
			Booking entity = new Booking();
			
			entity.setIdCustomer(dto.getIdCustomer());
			entity.setIdCampSite(dto.getIdCampSite());
			entity.setBookingCode(UUID.randomUUID().toString());
			entity.setBookingInitDate(dto.getBookingInitDate());
			entity.setBookingEndDate(dto.getBookingEndDate());
			entity.setStatus(Constants.DB_STATUS_ACTIVE);
			entity.setCreatedBy(env.getProperty("extremecamp.booking.application.dbuser"));
			entity.setCreatedAt(new Date());
		
			dao.create(entity);
			
			BookingCRUDResponseDTO response = new BookingCRUDResponseDTO();
			
			response.setBookingCode(entity.getBookingCode());
			response.setIdBooking(entity.getId());
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.created.successful"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.created.successful"));
			status.setHttpStatusCode(HttpStatus.OK.value());
			response.setStatus(status);
			
			return response;
			
		} catch (Exception e) {

			e.printStackTrace();
			
			BookingCRUDResponseDTO errorResponse = new BookingCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.create.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.create.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public BookingCRUDResponseDTO update(BookingDTO dto) {
		
		try {
			
			Booking existsBookingWithId = dao.findByBookingCode(dto.getBookingCode());
			
			if(existsBookingWithId != null) {
				
				existsBookingWithId.setBookingInitDate(dto.getBookingInitDate());
				existsBookingWithId.setBookingEndDate(dto.getBookingEndDate());
				existsBookingWithId.setStatus(Constants.DB_STATUS_MODIFIED);
				existsBookingWithId.setModifiedBy(env.getProperty("extremecamp.booking.application.dbuser"));
				existsBookingWithId.setModifiedAt(new Date());
			
				dao.update(existsBookingWithId);
				
				BookingCRUDResponseDTO response = new BookingCRUDResponseDTO();
				
				response.setIdBooking(existsBookingWithId.getId());
				response.setBookingCode(existsBookingWithId.getBookingCode());
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.updated.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.updated.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				BookingCRUDResponseDTO errorResponse = new BookingCRUDResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.doesnt.exist"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.doesnt.exist"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			BookingCRUDResponseDTO errorResponse = new BookingCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.update.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.update.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public BookingCRUDResponseDTO delete(BookingDTO dto) {
		
		try {
			
			Booking existsBookingWithId = dao.findByBookingCode(dto.getBookingCode());
			
			if(existsBookingWithId != null) {
				
				existsBookingWithId.setStatus(Constants.DB_STATUS_DELETED);
				existsBookingWithId.setModifiedBy(env.getProperty("extremecamp.booking.application.dbuser"));
				existsBookingWithId.setModifiedAt(new Date());
			
				dao.delete(existsBookingWithId);
				
				BookingCRUDResponseDTO response = new BookingCRUDResponseDTO();
				
				response.setIdBooking(existsBookingWithId.getId());
				response.setBookingCode(existsBookingWithId.getBookingCode());
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.deleted.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.deleted.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				BookingCRUDResponseDTO errorResponse = new BookingCRUDResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.doesnt.exist"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.doesnt.exist"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			BookingCRUDResponseDTO errorResponse = new BookingCRUDResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.delete.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.delete.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public BookingListResponseDTO findAll() {
		
		try {
			
			List<Booking> lstExistingBookings = dao.findAll();
			
			if(lstExistingBookings != null && lstExistingBookings.size() > 0) {
				
				BookingListResponseDTO response = new BookingListResponseDTO();
				
				response.setResults(lstExistingBookings.stream().map(x -> new BookingDTO(x.getId(), x.getIdCampSite(), x.getIdCustomer(), x.getBookingCode(), x.getBookingInitDate(), x.getBookingEndDate())).collect(Collectors.toList()));
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.retrieve.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.retrieve.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				BookingListResponseDTO errorResponse = new BookingListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.customer.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			BookingListResponseDTO errorResponse = new BookingListResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.retrieve.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.retrieve.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public BookingListResponseDTO findById(Long id) {
		
		try {
			
			Booking existingBooking = null;
				
			existingBooking = dao.findById(id);
			
			if(existingBooking != null) {
				
				BookingListResponseDTO response = new BookingListResponseDTO();
				
				response.setResults(new ArrayList<BookingDTO>());
				
					
				BookingDTO obj = new BookingDTO(existingBooking.getId(), existingBooking.getIdCampSite(), existingBooking.getIdCustomer(), existingBooking.getBookingCode(), existingBooking.getBookingInitDate(), existingBooking.getBookingEndDate());
					
				response.getResults().add(obj);
					
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.retrieve.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.retrieve.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				BookingListResponseDTO errorResponse = new BookingListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			BookingListResponseDTO errorResponse = new BookingListResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.retrieve.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.retrieve.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public BookingListResponseDTO findByBookingCode(String bookingCode) {
		
		try {
			
			Booking existingBooking = null;
				
			existingBooking = dao.findByBookingCode(bookingCode);
			
			if(existingBooking != null) {
				
				BookingListResponseDTO response = new BookingListResponseDTO();
				
				response.setResults(new ArrayList<BookingDTO>());
				
					
				BookingDTO obj = new BookingDTO(existingBooking.getId(), existingBooking.getIdCampSite(), existingBooking.getIdCustomer(), existingBooking.getBookingCode(), existingBooking.getBookingInitDate(), existingBooking.getBookingEndDate());
					
				response.getResults().add(obj);
					
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.retrieve.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.retrieve.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				BookingListResponseDTO errorResponse = new BookingListResponseDTO();
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			BookingListResponseDTO errorResponse = new BookingListResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.retrieve.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.retrieve.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public BookingAvailabilityForCampSiteDateRangeResponseDTO validateBookingAvailabilityForCampSiteDateRange(BookingAvailabilityForCampSiteDateRangeRequestDTO dto) {
		
		try {
			
			/*CampSite restService invoke for get the maxNumReservationsPerDay for the correponding idCampSite*/
			ResponseEntity<CampSiteListResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-CAMPSITE-SERVICE/extreme-camp/campsite-service/campsites/" + dto.getIdCampSite(), HttpMethod.GET, null, CampSiteListResponseDTO.class);
			
			if(resp.getBody().getStatus() != null && resp.getBody().getStatus().getHttpStatusCode() != HttpStatus.OK.value()) {
				
				BookingAvailabilityForCampSiteDateRangeResponseDTO errorResponse = new BookingAvailabilityForCampSiteDateRangeResponseDTO();
				
				errorResponse.setAvailable(false);
				errorResponse.setIdCampSite(dto.getIdCampSite());
				errorResponse.setBookingInitDate(dto.getBookingInitDate());
				errorResponse.setBookingEndDate(dto.getBookingEndDate());
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.campsite.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.campsite.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
			Integer maxNumReservationsPerDay = 0;
			if(resp.getBody().getResults() != null && resp.getBody().getResults().size() > 0) {
				
				CampSiteDTO campSite = resp.getBody().getResults().get(0);
				maxNumReservationsPerDay = campSite.getMaxNumReservationsPerDay();
				
			}
			
			boolean dateRangeAvailableForBooking = false;
				
			dateRangeAvailableForBooking = dao.validateBookingAvailabilityForDateRange(dto.getIdCampSite(), maxNumReservationsPerDay, dto.getBookingInitDate(), dto.getBookingEndDate());
			
			if(dateRangeAvailableForBooking) {
				
				BookingAvailabilityForCampSiteDateRangeResponseDTO response = new BookingAvailabilityForCampSiteDateRangeResponseDTO();
				
				response.setAvailable(true);
				response.setIdCampSite(dto.getIdCampSite());
				response.setBookingInitDate(dto.getBookingInitDate());
				response.setBookingEndDate(dto.getBookingEndDate());	
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.available.campsite.daterange.successful"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.available.campsite.daterange.successful"));
				status.setHttpStatusCode(HttpStatus.OK.value());
				response.setStatus(status);
				
				return response;
				
			} else {
				
				BookingAvailabilityForCampSiteDateRangeResponseDTO errorResponse = new BookingAvailabilityForCampSiteDateRangeResponseDTO();
				
				errorResponse.setAvailable(false);
				errorResponse.setIdCampSite(dto.getIdCampSite());
				errorResponse.setBookingInitDate(dto.getBookingInitDate());
				errorResponse.setBookingEndDate(dto.getBookingEndDate());
				
				ErrorStatusDTO status = new ErrorStatusDTO();
				status.setErrorCode(env.getProperty("status.error.code.booking.available.campsite.daterange.notfound"));
				status.setErrorMessage(env.getProperty("status.error.message.booking.available.campsite.daterange.notfound"));
				status.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
				errorResponse.setStatus(status);
				
				return errorResponse;
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			BookingAvailabilityForCampSiteDateRangeResponseDTO errorResponse = new BookingAvailabilityForCampSiteDateRangeResponseDTO();
			
			errorResponse.setAvailable(false);
			errorResponse.setIdCampSite(dto.getIdCampSite());
			errorResponse.setBookingInitDate(dto.getBookingInitDate());
			errorResponse.setBookingEndDate(dto.getBookingEndDate());
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.available.campsite.daterange.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.available.campsite.daterange.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

}
