package com.upgradeinc.extremecamp.booking.controller;

import javax.validation.Valid;
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

import com.upgradeinc.extremecamp.booking.dto.BookingAvailabilityForCampSiteDateRangeRequestDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingAvailabilityForCampSiteDateRangeResponseDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingCRUDResponseDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingListResponseDTO;
import com.upgradeinc.extremecamp.booking.service.BookingService;

@RestController
@Validated
@RequestMapping(path = "/extreme-camp/booking-service")
public class BookingRestController {
	
	@Autowired
	BookingService bookingService;
	
	@RequestMapping(path = {"/bookings/validateBookingAvailabilityForCampSiteDateRange"},method = RequestMethod.POST)
	public ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO> validateBookingAvailabilityForCampSiteDateRange(@Valid @RequestBody BookingAvailabilityForCampSiteDateRangeRequestDTO request){
		
		BookingAvailabilityForCampSiteDateRangeResponseDTO response = bookingService.validateBookingAvailabilityForCampSiteDateRange(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@RequestMapping(path = {"/bookings/createBooking"},method = RequestMethod.POST)
	public ResponseEntity<BookingCRUDResponseDTO> createBooking(@Valid @RequestBody BookingDTO dto){
		
		BookingCRUDResponseDTO response = bookingService.create(dto);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<BookingCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<BookingCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/bookings/updateBooking/{bookingCode}"},method = RequestMethod.PUT)
	public ResponseEntity<BookingCRUDResponseDTO> updateBooking(@PathVariable(name = "bookingCode") String bookingCode, @Valid @RequestBody BookingDTO dto){
		
		dto.setBookingCode(bookingCode);
		BookingCRUDResponseDTO response = bookingService.update(dto);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<BookingCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<BookingCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/bookings/deleteBooking/{bookingCode}"},method = RequestMethod.DELETE)
	public ResponseEntity<BookingCRUDResponseDTO> deleteBooking(@PathVariable(name = "bookingCode") String bookingCode){
		
		BookingDTO dto = new BookingDTO();
		
		dto.setBookingCode(bookingCode);
		BookingCRUDResponseDTO response = bookingService.delete(dto);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<BookingCRUDResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<BookingCRUDResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(path = {"/bookings/bookingCode/{bookingCode}"},method = RequestMethod.GET)
	public ResponseEntity<BookingListResponseDTO> findByBookingCode(@Valid 
																	@Size(min = 1, max = 64, message = "{validation.error.message.bookingcode.maxlength}") 
																	@PathVariable(name = "bookingCode") String bookingCode) {
		
		BookingListResponseDTO response = bookingService.findByBookingCode(bookingCode);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<BookingListResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<BookingListResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
