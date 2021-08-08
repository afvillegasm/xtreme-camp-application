package com.upgradeinc.extremecamp.api.dto;

public class CancelBookingResponseDTO {
	
	private String bookingCode;
	ErrorStatusDTO status;
	
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
