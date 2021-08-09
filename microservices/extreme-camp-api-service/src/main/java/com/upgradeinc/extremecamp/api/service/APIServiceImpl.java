package com.upgradeinc.extremecamp.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.upgradeinc.extremecamp.api.dto.BookingAvailabilityForCampSiteDateRangeRequestDTO;
import com.upgradeinc.extremecamp.api.dto.BookingDTO;
import com.upgradeinc.extremecamp.api.dto.BookingInfoDTO;
import com.upgradeinc.extremecamp.api.dto.BookingListResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CampSiteInfoDTO;
import com.upgradeinc.extremecamp.api.dto.CancelBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CreateBookingRequestDTO;
import com.upgradeinc.extremecamp.api.dto.CreateBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CreateCustomerRequestDTO;
import com.upgradeinc.extremecamp.api.dto.CreateCustomerResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CustomerDTO;
import com.upgradeinc.extremecamp.api.dto.FindBookingByBookingCodeResponseDTO;
import com.upgradeinc.extremecamp.api.dto.FindCampSitesResponseDTO;
import com.upgradeinc.extremecamp.api.dto.FindCustomerByEmailResponseDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateBookingRequestDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateCustomerRequestDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateCustomerResponseDTO;
import com.upgradeinc.extremecamp.api.dto.ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO;
import com.upgradeinc.extremecamp.api.dto.BookingAvailabilityForCampSiteDateRangeResponseDTO;
import com.upgradeinc.extremecamp.api.dto.BookingCRUDResponseDTO;
import com.upgradeinc.extremecamp.api.dto.ErrorStatusDTO;
import com.upgradeinc.extremecamp.api.dto.CampSiteListResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CustomerListResponseDTO;

@Service
@PropertySource("classpath:message.properties")
public class APIServiceImpl implements APIService{
	
	@Autowired
	private Environment env;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public FindCampSitesResponseDTO findAllCampSites() {
		
		ResponseEntity<CampSiteListResponseDTO> restResponse = invokeCampSiteServiceFindById(null);
		
		if(restResponse ==  null) {
			
			FindCampSitesResponseDTO errorResponse = new FindCampSitesResponseDTO();
			
			errorResponse.setResults(new ArrayList<CampSiteInfoDTO>());
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.campsite.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.campsite.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		if(restResponse.getBody().getResults() != null) {
			
			List<CampSiteInfoDTO> lstResults = new ArrayList<CampSiteInfoDTO>(); 
			
			if(restResponse.getBody().getResults().size() > 0) {
				lstResults = restResponse.getBody().getResults()
									  .stream()
									  .map(x -> new CampSiteInfoDTO(x.getId(), x.getName(), x.getDescription(), x.getFoundationDate()))
									  .collect(Collectors.toList());
			}
			
			FindCampSitesResponseDTO response = new FindCampSitesResponseDTO();
			
			response.setResults(lstResults);
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
			
		} else {
			
			FindCampSitesResponseDTO response = new FindCampSitesResponseDTO();
			
			response.setResults(new ArrayList<CampSiteInfoDTO>());
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
			
		}
		
	}

	@Override
	public FindCampSitesResponseDTO findCampSiteById(Long id) {
		
		ResponseEntity<CampSiteListResponseDTO> restResponse = invokeCampSiteServiceFindById(id);
		
		if(restResponse ==  null) {
			
			FindCampSitesResponseDTO errorResponse = new FindCampSitesResponseDTO();
			
			errorResponse.setResults(new ArrayList<CampSiteInfoDTO>());
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.campsite.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.campsite.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		if(restResponse.getBody().getResults() != null) {
			
			List<CampSiteInfoDTO> lstResults = new ArrayList<CampSiteInfoDTO>(); 
			
			if(restResponse.getBody().getResults().size() > 0) {
				lstResults = restResponse.getBody().getResults()
									  .stream()
									  .map(x -> new CampSiteInfoDTO(x.getId(), x.getName(), x.getDescription(), x.getFoundationDate()))
									  .collect(Collectors.toList());
			}
			
			FindCampSitesResponseDTO response = new FindCampSitesResponseDTO();
			
			response.setResults(lstResults);
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
			
		} else {
			
			FindCampSitesResponseDTO response = new FindCampSitesResponseDTO();
			
			response.setResults(new ArrayList<CampSiteInfoDTO>());
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
			
		}
		
	}

	@Override
	public FindCustomerByEmailResponseDTO findCustomerByEmail(String email) {
		
		ResponseEntity<CustomerListResponseDTO>  restResponse = invokeCustomerServiceFindByEmail(email);
		
		if(restResponse == null) {
			
			FindCustomerByEmailResponseDTO errorResponse = new FindCustomerByEmailResponseDTO();
			
			errorResponse.setResults(new ArrayList<CustomerDTO>());
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		if(restResponse.getBody().getResults() != null) {
			
			List<CampSiteInfoDTO> lstResults = new ArrayList<CampSiteInfoDTO>(); 
			
			FindCustomerByEmailResponseDTO response = new FindCustomerByEmailResponseDTO();
			
			response.setResults(restResponse.getBody().getResults());
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
			
		} else {
			
			FindCustomerByEmailResponseDTO response = new FindCustomerByEmailResponseDTO();
			
			response.setResults(new ArrayList<CustomerDTO>());
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
			
		}
		
	}

	@Override
	public CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO request) {
		
		ResponseEntity<CreateCustomerResponseDTO> restResponse = invokeCustomerServiceCreate(request);
		
		if(restResponse == null) {
			
			CreateCustomerResponseDTO errorResponse = new CreateCustomerResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
			
		return restResponse.getBody();
		
	}

	@Override
	public UpdateCustomerResponseDTO updateCustomer(Long id, UpdateCustomerRequestDTO request) {
		
		ResponseEntity<UpdateCustomerResponseDTO> restResponse = invokeCustomerServiceUpdate(request, id);
		
		if(restResponse == null) {
			
			UpdateCustomerResponseDTO errorResponse = new UpdateCustomerResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.customer.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.customer.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		return restResponse.getBody();
		
	}

	@Override
	public FindBookingByBookingCodeResponseDTO findBookingByBookingCode(String bookingCode) {
		
		ResponseEntity<BookingListResponseDTO> restResponse = invokeBookingServiceFindByBookingCode(bookingCode);
		
		if(restResponse == null) {
			
			FindBookingByBookingCodeResponseDTO errorResponse = new FindBookingByBookingCodeResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		if(restResponse.getBody().getResults() != null && restResponse.getBody().getResults().size() > 0) {
			
			FindBookingByBookingCodeResponseDTO response = new FindBookingByBookingCodeResponseDTO();
			
			BookingDTO dto = restResponse.getBody().getResults().get(0);
			
			BookingInfoDTO bookingInfo = new BookingInfoDTO(dto.getBookingCode(), dto.getBookingInitDate(), dto.getBookingEndDate());
			
			response.setBookingInfo(bookingInfo);
			
			if(dto.getIdCampSite() != null) {
				
				FindCampSitesResponseDTO campsiteRest = findCampSiteById(dto.getIdCampSite());
				
				if(campsiteRest.getResults() != null && campsiteRest.getResults().size() > 0) {
					
					response.setCampSiteInfo(campsiteRest.getResults().get(0));
					
				}
				
			}
			
			if(dto.getIdCustomer() != null) {
				
				ResponseEntity<CustomerListResponseDTO> restCustomer = invokeCustomerServiceFindById(dto.getIdCustomer());
				
				if(restCustomer != null && restCustomer.getBody().getResults() != null && restCustomer.getBody().getResults().size() > 0) {
					
					response.setCustomerInfo(restCustomer.getBody().getResults().get(0));
					
				}
				
			}
			
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
			
		} else {
			
			FindBookingByBookingCodeResponseDTO response = new FindBookingByBookingCodeResponseDTO();
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
			
		}
		
	}

	@Override
	public ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO validateBookingAvailabilityForCampSiteDateRange(
			BookingAvailabilityForCampSiteDateRangeRequestDTO request) {
		
		/*validation dateformat*/
		if(!isValidDateFormat(request.getBookingInitDate())) {
			
			ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO errorResponse = new ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.notavalidinitdate"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.notavalidinitdate"));
			status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		if(!isValidDateFormat(request.getBookingEndDate())) {
			
			ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO errorResponse = new ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.notavalidenddate"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.notavalidenddate"));
			status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO> restResponse = invokeBookingServiceValidateBookingAvailabilityForCampSiteDateRange(request);
		
		if(restResponse == null) {
			
			ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO errorResponse = new ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		if(restResponse.getBody() != null) {
			ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO response = new ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO();
			 
			response.setAvailableDateRangeToBooking(restResponse.getBody().isAvailable());
			response.setStatus(restResponse.getBody().getStatus());
			
			return response;
		
		} else {
			
			ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO errorResponse = new ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
	}

	@Override
	public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO request) {
		
		/*validation dateformat*/
		if(!isValidDateFormat(request.getBookingInitDate())) {
			
			CreateBookingResponseDTO errorResponse = new CreateBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.notavalidinitdate"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.notavalidinitdate"));
			status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		if(!isValidDateFormat(request.getBookingEndDate())) {
			
			CreateBookingResponseDTO errorResponse = new CreateBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.notavalidenddate"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.notavalidenddate"));
			status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		ResponseEntity<BookingCRUDResponseDTO> restResponse = invokeBookingServiceCreate(request);
		
		if(restResponse == null) {
			
			CreateBookingResponseDTO errorResponse = new CreateBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
			
		CreateBookingResponseDTO response = new CreateBookingResponseDTO();
		
		if(restResponse.getBody() != null) {
			
			if(restResponse.getBody().getBookingCode() != null) {
				response.setBookingCode(restResponse.getBody().getBookingCode());
			}
			
			response.setStatus(restResponse.getBody().getStatus());
			
		} else {
			
			CreateBookingResponseDTO errorResponse = new CreateBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		return response;
		
	}

	@Override
	public UpdateBookingResponseDTO updateBooking(String bookingCode, UpdateBookingRequestDTO request) {
		
		/*validation dateformat*/
		if(!isValidDateFormat(request.getBookingInitDate())) {
			
			UpdateBookingResponseDTO errorResponse = new UpdateBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.notavalidinitdate"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.notavalidinitdate"));
			status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		if(!isValidDateFormat(request.getBookingEndDate())) {
			
			UpdateBookingResponseDTO errorResponse = new UpdateBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.notavalidenddate"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.notavalidenddate"));
			status.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		FindBookingByBookingCodeResponseDTO findBookingCode =  findBookingByBookingCode(bookingCode);
		
		if(!env.getProperty("status.error.code.booking.retrieve.successful").equals(findBookingCode.getStatus().getErrorCode())) {
			
			UpdateBookingResponseDTO errorResponse = new UpdateBookingResponseDTO();
			
			errorResponse.setStatus(findBookingCode.getStatus());
			
			return errorResponse;
			
		}
		
		BookingDTO dtoRequest = new BookingDTO();
		
		dtoRequest.setBookingInitDate(request.getBookingInitDate());
		dtoRequest.setBookingEndDate(request.getBookingEndDate());
		
		if(findBookingCode.getCampSiteInfo() != null) {
			
			dtoRequest.setIdCampSite(findBookingCode.getCampSiteInfo().getId());
			
		}
		
		if(findBookingCode.getCustomerInfo() != null) {
			
			dtoRequest.setIdCustomer(findBookingCode.getCustomerInfo().getId());
			
		}
		
		ResponseEntity<BookingCRUDResponseDTO> restResponse = invokeBookingServiceUpdate(dtoRequest, bookingCode);
		
		if(restResponse == null) {
			
			UpdateBookingResponseDTO errorResponse = new UpdateBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
			
		UpdateBookingResponseDTO response = new UpdateBookingResponseDTO();
		
		if(restResponse.getBody() != null) {
			
			if(restResponse.getBody().getBookingCode() != null) {
				response.setBookingCode(restResponse.getBody().getBookingCode());
			}
			
			response.setStatus(restResponse.getBody().getStatus());
			
		} else {
			
			UpdateBookingResponseDTO errorResponse = new UpdateBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		return response;
		
	}

	@Override
	public CancelBookingResponseDTO cancelBooking(String bookingCode) {
		
		FindBookingByBookingCodeResponseDTO findBookingCode =  findBookingByBookingCode(bookingCode);
		
		if(!env.getProperty("status.error.code.booking.retrieve.successful").equals(findBookingCode.getStatus().getErrorCode())) {
			
			CancelBookingResponseDTO errorResponse = new CancelBookingResponseDTO();
			
			errorResponse.setStatus(findBookingCode.getStatus());
			
			return errorResponse;
			
		}
		
		ResponseEntity<BookingCRUDResponseDTO> restResponse = invokeBookingServiceDelete(bookingCode);
		
		if(restResponse == null) {
			
			CancelBookingResponseDTO errorResponse = new CancelBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
			
		CancelBookingResponseDTO response = new CancelBookingResponseDTO();
		
		if(restResponse.getBody() != null) {
			
			if(restResponse.getBody().getBookingCode() != null) {
				response.setBookingCode(restResponse.getBody().getBookingCode());
			}
			
			response.setStatus(restResponse.getBody().getStatus());
			
		} else {
			
			CancelBookingResponseDTO errorResponse = new CancelBookingResponseDTO();
			
			ErrorStatusDTO status = new ErrorStatusDTO();
			status.setErrorCode(env.getProperty("status.error.code.booking.failed"));
			status.setErrorMessage(env.getProperty("status.error.message.booking.failed"));
			status.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setStatus(status);
			
			return errorResponse;
			
		}
		
		return response;
		
	}
	
	private ResponseEntity<CampSiteListResponseDTO> invokeCampSiteServiceFindById(Long idCampSite) {
		
		try {
			ResponseEntity<CampSiteListResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-CAMPSITE-SERVICE/extreme-camp/campsite-service/campsites/" + (idCampSite == null ? "" : idCampSite), HttpMethod.GET, null, CampSiteListResponseDTO.class);
			
			return resp;

		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				CampSiteListResponseDTO errResp = parser.readValueAs(CampSiteListResponseDTO.class);
			    
			    return new ResponseEntity<CampSiteListResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<CustomerListResponseDTO> invokeCustomerServiceFindByEmail(String email) {
		
		try {
			ResponseEntity<CustomerListResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-CUSTOMER-SERVICE/extreme-camp/customer-service/customers/email/" + email, HttpMethod.GET, null, CustomerListResponseDTO.class);
			
			return resp;

		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				CustomerListResponseDTO errResp = parser.readValueAs(CustomerListResponseDTO.class);
			    
			    return new ResponseEntity<CustomerListResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<CreateCustomerResponseDTO> invokeCustomerServiceCreate(CreateCustomerRequestDTO request) {
		
		try {
			
			HttpEntity<CreateCustomerRequestDTO> httpEntity = null;
			
			httpEntity = new HttpEntity<CreateCustomerRequestDTO>(request, null);
			
			ResponseEntity<CreateCustomerResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-CUSTOMER-SERVICE/extreme-camp/customer-service/customers/create", HttpMethod.POST, httpEntity, CreateCustomerResponseDTO.class);
			
			return resp;
			
		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				CreateCustomerResponseDTO errResp = parser.readValueAs(CreateCustomerResponseDTO.class);
			    
			    return new ResponseEntity<CreateCustomerResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<UpdateCustomerResponseDTO> invokeCustomerServiceUpdate(UpdateCustomerRequestDTO request, Long id) {
		
		try {
			
			HttpEntity<UpdateCustomerRequestDTO> httpEntity = null;
			
			httpEntity = new HttpEntity<UpdateCustomerRequestDTO>(request, null);
			
			ResponseEntity<UpdateCustomerResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-CUSTOMER-SERVICE/extreme-camp/customer-service/customers/update/" + id, HttpMethod.PUT, httpEntity, UpdateCustomerResponseDTO.class);
			
			return resp;
			
		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				UpdateCustomerResponseDTO errResp = parser.readValueAs(UpdateCustomerResponseDTO.class);
			    
			    return new ResponseEntity<UpdateCustomerResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<CustomerListResponseDTO> invokeCustomerServiceFindById(Long idCustomer) {
		
		try {
			ResponseEntity<CustomerListResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-CUSTOMER-SERVICE/extreme-camp/customer-service/customers/id/" + idCustomer, HttpMethod.GET, null, CustomerListResponseDTO.class);
			
			return resp;

		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				CustomerListResponseDTO errResp = parser.readValueAs(CustomerListResponseDTO.class);
			    
			    return new ResponseEntity<CustomerListResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<BookingListResponseDTO> invokeBookingServiceFindByBookingCode(String bookingCode) {
		
		try {
			ResponseEntity<BookingListResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-BOOKING-SERVICE/extreme-camp/booking-service/bookings/bookingCode/" + bookingCode, HttpMethod.GET, null, BookingListResponseDTO.class);
			
			return resp;

		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				BookingListResponseDTO errResp = parser.readValueAs(BookingListResponseDTO.class);
			    
			    return new ResponseEntity<BookingListResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO> invokeBookingServiceValidateBookingAvailabilityForCampSiteDateRange(BookingAvailabilityForCampSiteDateRangeRequestDTO request) {
		
		ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO> resp = null;
		
		try {
			
			HttpEntity<BookingAvailabilityForCampSiteDateRangeRequestDTO> httpEntity = null;
			
			httpEntity = new HttpEntity<BookingAvailabilityForCampSiteDateRangeRequestDTO>(request, null);
			
			resp =  restTemplate.exchange("http://EXTREME-CAMP-BOOKING-SERVICE/extreme-camp/booking-service/bookings/validateBookingAvailabilityForCampSiteDateRange", HttpMethod.POST, httpEntity, BookingAvailabilityForCampSiteDateRangeResponseDTO.class);
			
			return resp;

		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				BookingAvailabilityForCampSiteDateRangeResponseDTO errResp = parser.readValueAs(BookingAvailabilityForCampSiteDateRangeResponseDTO.class);
			    
			    return new ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<BookingCRUDResponseDTO> invokeBookingServiceCreate(CreateBookingRequestDTO request) {
		
		try {
			
			HttpEntity<CreateBookingRequestDTO> httpEntity = null;
			
			httpEntity = new HttpEntity<CreateBookingRequestDTO>(request, null);
			
			ResponseEntity<BookingCRUDResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-BOOKING-SERVICE/extreme-camp/booking-service/bookings/createBooking", HttpMethod.POST, httpEntity, BookingCRUDResponseDTO.class);
			
			return resp;

		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				BookingCRUDResponseDTO errResp = parser.readValueAs(BookingCRUDResponseDTO.class);
			    
			    return new ResponseEntity<BookingCRUDResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<BookingCRUDResponseDTO> invokeBookingServiceUpdate(BookingDTO request, String bookingCode) {
		
		try {
			
			HttpEntity<BookingDTO> httpEntity = null;
			
			httpEntity = new HttpEntity<BookingDTO>(request, null);
			
			ResponseEntity<BookingCRUDResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-BOOKING-SERVICE/extreme-camp/booking-service/bookings/updateBooking/" + bookingCode, HttpMethod.PUT, httpEntity, BookingCRUDResponseDTO.class);
			
			return resp;

		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				BookingCRUDResponseDTO errResp = parser.readValueAs(BookingCRUDResponseDTO.class);
			    
			    return new ResponseEntity<BookingCRUDResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private ResponseEntity<BookingCRUDResponseDTO> invokeBookingServiceDelete(String bookingCode) {
		
		try {
			
			ResponseEntity<BookingCRUDResponseDTO> resp =  restTemplate.exchange("http://EXTREME-CAMP-BOOKING-SERVICE/extreme-camp/booking-service/bookings/deleteBooking/" + bookingCode, HttpMethod.DELETE, null, BookingCRUDResponseDTO.class);
			
			return resp;

		} catch (HttpClientErrorException ex) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			JsonFactory factory = mapper.getFactory();
		    JsonParser parser;
			try {
				parser = factory.createParser(ex.getResponseBodyAsString());
				BookingCRUDResponseDTO errResp = parser.readValueAs(BookingCRUDResponseDTO.class);
			    
			    return new ResponseEntity<BookingCRUDResponseDTO>(errResp, HttpStatus.valueOf(ex.getRawStatusCode()));
			    
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
	}
	
	private boolean isValidDateFormat(String stringDate) {
		
		try {
			
			String[] splitDate = stringDate.split("-");
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1);
			cal.set(Calendar.YEAR, Integer.parseInt(splitDate[2]));
			
			if(cal.get(Calendar.DAY_OF_MONTH) != Integer.parseInt(splitDate[0]) || cal.get(Calendar.MONTH) != (Integer.parseInt(splitDate[1]) - 1) || cal.get(Calendar.YEAR) != Integer.parseInt(splitDate[2])) {
				return false;
			}
			
			return true;
			
		} catch(Exception e) {
			
			return false;
			
		}
		
	}

}
