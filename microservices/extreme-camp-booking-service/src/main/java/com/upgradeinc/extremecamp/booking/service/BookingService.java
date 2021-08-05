package com.upgradeinc.extremecamp.booking.service;

import java.util.Date;

import com.upgradeinc.extremecamp.booking.dto.BookingAvailabilityForCampSiteDateRangeRequestDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingAvailabilityForCampSiteDateRangeResponseDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingCRUDResponseDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingDTO;
import com.upgradeinc.extremecamp.booking.dto.BookingListResponseDTO;

public interface BookingService {
	
	public BookingCRUDResponseDTO create(BookingDTO dto);
	public BookingCRUDResponseDTO update(BookingDTO dto);
	public BookingCRUDResponseDTO delete(BookingDTO dto);
	public BookingListResponseDTO findAll();
	public BookingListResponseDTO findById(Long id);
	public BookingListResponseDTO findByBookingCode(String bookingCode);
	public BookingAvailabilityForCampSiteDateRangeResponseDTO validateBookingAvailabilityForCampSiteDateRange(BookingAvailabilityForCampSiteDateRangeRequestDTO dto);

}
