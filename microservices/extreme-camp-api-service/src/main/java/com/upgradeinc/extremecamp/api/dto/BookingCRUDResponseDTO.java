package com.upgradeinc.extremecamp.api.dto;

import com.upgradeinc.extremecamp.api.dto.ErrorStatusDTO;

public class BookingCRUDResponseDTO {
	
	private Long idBooking;
	private String bookingCode;
	ErrorStatusDTO status;
	
	public Long getIdBooking() {
		return idBooking;
	}
	public void setIdBooking(Long idBooking) {
		this.idBooking = idBooking;
	}
	public String getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}

}
