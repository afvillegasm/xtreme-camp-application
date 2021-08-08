package com.upgradeinc.extremecamp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.upgradeinc.extremecamp.api.dto.BookingAvailabilityForCampSiteDateRangeRequestDTO;
import com.upgradeinc.extremecamp.api.dto.CancelBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CreateBookingRequestDTO;
import com.upgradeinc.extremecamp.api.dto.CreateBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.CreateCustomerRequestDTO;
import com.upgradeinc.extremecamp.api.dto.CreateCustomerResponseDTO;
import com.upgradeinc.extremecamp.api.dto.FindBookingByBookingCodeResponseDTO;
import com.upgradeinc.extremecamp.api.dto.FindCampSitesResponseDTO;
import com.upgradeinc.extremecamp.api.dto.FindCustomerByEmailResponseDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateBookingRequestDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateBookingResponseDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateCustomerRequestDTO;
import com.upgradeinc.extremecamp.api.dto.UpdateCustomerResponseDTO;
import com.upgradeinc.extremecamp.api.dto.ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO;

@Service
@PropertySource("classpath:message.properties")
public class APIServiceImpl implements APIService{
	
	@Autowired
	private Environment env;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public FindCampSitesResponseDTO findAllCampSites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FindCampSitesResponseDTO findCampSiteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FindCustomerByEmailResponseDTO findCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateCustomerResponseDTO updateCustomer(Long id, UpdateCustomerRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FindBookingByBookingCodeResponseDTO findBookingByBookingCode(String bookingCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO validateBookingAvailabilityForCampSiteDateRange(
			BookingAvailabilityForCampSiteDateRangeRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateBookingResponseDTO updateBooking(String bookingCode, UpdateBookingRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CancelBookingResponseDTO cancelBooking(String bookingCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
