package com.upgradeinc.extremecamp.api.service;

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

public interface APIService {
	
	public FindCampSitesResponseDTO findAllCampSites();
	public FindCampSitesResponseDTO findCampSiteById(Long id);
	public FindCustomerByEmailResponseDTO findCustomerByEmail(String email);
	public CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO request);
	public UpdateCustomerResponseDTO updateCustomer(Long id, UpdateCustomerRequestDTO request);
	public FindBookingByBookingCodeResponseDTO findBookingByBookingCode(String bookingCode);
	public ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO validateBookingAvailabilityForCampSiteDateRange(BookingAvailabilityForCampSiteDateRangeRequestDTO request);
	public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO request);
	public UpdateBookingResponseDTO updateBooking(String bookingCode, UpdateBookingRequestDTO request);
	public CancelBookingResponseDTO cancelBooking(String bookingCode);

}
