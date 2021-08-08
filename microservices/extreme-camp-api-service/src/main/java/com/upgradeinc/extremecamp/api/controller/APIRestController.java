package com.upgradeinc.extremecamp.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upgradeinc.extremecamp.api.dto.BookingAvailabilityForCampSiteDateRangeRequestDTO;
import com.upgradeinc.extremecamp.api.dto.CampSiteListResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CancelBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CreateBookingRequestDTO;
import com.upgradeinc.extremecamp.api.dto.CreateBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CreateCustomerRequestDTO;
import com.upgradeinc.extremecamp.api.dto.CreateCustomerResponseDTO;
import com.upgradeinc.extremecamp.api.service.APIService;
import com.upgradeinc.extremecamp.api.dto.CustomerDTO;
import com.upgradeinc.extremecamp.api.dto.FindBookingByBookingCodeResponseDTO;
import com.upgradeinc.extremecamp.api.dto.FindCampSitesResponseDTO;
import com.upgradeinc.extremecamp.api.dto.FindCustomerByEmailResponseDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateBookingRequestDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateCustomerRequestDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateCustomerResponseDTO;
import com.upgradeinc.extremecamp.api.dto.ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO;

@RestController
@Validated
@RequestMapping(path = "/extreme-camp/api-service")
public class APIRestController {
	
	@Autowired
	APIService apiService;

	@RequestMapping(path = {"/campsites"},method = RequestMethod.GET)
	public ResponseEntity<FindCampSitesResponseDTO> findAllCampSites(){
		
		FindCampSitesResponseDTO response = apiService.findAllCampSites();
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<FindCampSitesResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<FindCampSitesResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/campsites/{id}"},method = RequestMethod.GET)
	public ResponseEntity<FindCampSitesResponseDTO> findCampSiteById(@PathVariable(name = "id") Long id){
		
		FindCampSitesResponseDTO response = apiService.findCampSiteById(id);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<FindCampSitesResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<FindCampSitesResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/customers/{email}"},method = RequestMethod.GET)
	public ResponseEntity<FindCustomerByEmailResponseDTO> findCustomerByEmail(@Valid 
															   @Email(message = "{validation.error.message.email.invalid}") 
															   @PathVariable(name = "email") String email){
		
		FindCustomerByEmailResponseDTO response = apiService.findCustomerByEmail(email);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<FindCustomerByEmailResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<FindCustomerByEmailResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/customers/create"},method = RequestMethod.POST)
	public ResponseEntity<CreateCustomerResponseDTO> createCustomer(@Valid @RequestBody CreateCustomerRequestDTO request){
		
		CreateCustomerResponseDTO response = apiService.createCustomer(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CreateCustomerResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CreateCustomerResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@RequestMapping(path = {"/customers/update/{id}"},method = RequestMethod.PUT)
	public ResponseEntity<UpdateCustomerResponseDTO> updateCustomer(@PathVariable(name = "id") Long id,@Valid @RequestBody UpdateCustomerRequestDTO request){
		
		UpdateCustomerResponseDTO response = apiService.updateCustomer(id, request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<UpdateCustomerResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<UpdateCustomerResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/bookings/{bookingCode}"},method = RequestMethod.GET)
	public ResponseEntity<FindBookingByBookingCodeResponseDTO> findBookingByBookingCode(@Valid 
																	@Size(min = 1, max = 64, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "bookingCode") String bookingCode) {
		
		FindBookingByBookingCodeResponseDTO response = apiService.findBookingByBookingCode(bookingCode);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<FindBookingByBookingCodeResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<FindBookingByBookingCodeResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/bookings/validateBookingAvailabilityForCampSiteDateRange"},method = RequestMethod.POST)
	public ResponseEntity<ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO> validateBookingAvailabilityForCampSiteDateRange(@Valid @RequestBody BookingAvailabilityForCampSiteDateRangeRequestDTO request){
		
		ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO response = apiService.validateBookingAvailabilityForCampSiteDateRange(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@RequestMapping(path = {"/bookings/createBooking"},method = RequestMethod.POST)
	public ResponseEntity<CreateBookingResponseDTO> createBooking(@Valid @RequestBody CreateBookingRequestDTO dto){
		
		CreateBookingResponseDTO response = apiService.createBooking(dto);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CreateBookingResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CreateBookingResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/bookings/updateBooking/{bookingCode}"},method = RequestMethod.PUT)
	public ResponseEntity<UpdateBookingResponseDTO> updateBooking(@PathVariable(name = "bookingCode") String bookingCode, @Valid @RequestBody UpdateBookingRequestDTO dto){
		
		UpdateBookingResponseDTO response = apiService.updateBooking(bookingCode, dto);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<UpdateBookingResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<UpdateBookingResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/bookings/cancelBooking/{bookingCode}"},method = RequestMethod.DELETE)
	public ResponseEntity<CancelBookingResponseDTO> cancelBooking(@PathVariable(name = "bookingCode") String bookingCode){
		
		CancelBookingResponseDTO response = apiService.cancelBooking(bookingCode);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<CancelBookingResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<CancelBookingResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
