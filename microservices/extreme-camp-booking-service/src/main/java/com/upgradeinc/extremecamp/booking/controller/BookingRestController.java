package com.upgradeinc.extremecamp.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upgradeinc.extremecamp.booking.dto.BookingAvailabilityForCampSiteDateRangeRequestDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingAvailabilityForCampSiteDateRangeResponseDTO;
import com.upgradeinc.extremecamp.booking.service.BookingService;

@RestController
@RequestMapping(path = "/extreme-camp/booking-service")
public class BookingRestController {
	
	@Autowired
	BookingService bookingService;
	
	@RequestMapping(path = {"/bookings/validateBookingAvailabilityForCampSiteDateRange"},method = RequestMethod.POST)
	public ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO> validateBookingAvailabilityForCampSiteDateRange(@RequestBody BookingAvailabilityForCampSiteDateRangeRequestDTO request){
		
		BookingAvailabilityForCampSiteDateRangeResponseDTO response = bookingService.validateBookingAvailabilityForCampSiteDateRange(request);
		
		if(response != null && response.getStatus() != null) {
			return new ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO>(response, HttpStatus.valueOf(response.getStatus().getHttpStatusCode()));
		} else {
			return new ResponseEntity<BookingAvailabilityForCampSiteDateRangeResponseDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

}
