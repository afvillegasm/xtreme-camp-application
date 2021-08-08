package com.upgradeinc.extremecamp.api.dto;

public class ValidateBookingAvailabilityForCampSiteDateRangeResponseDTO {
	
	private boolean availableDateRangeToBooking;
	ErrorStatusDTO status;
	
	public boolean isAvailableDateRangeToBooking() {
		return availableDateRangeToBooking;
	}
	public void setAvailableDateRangeToBooking(boolean availableDateRangeToBooking) {
		this.availableDateRangeToBooking = availableDateRangeToBooking;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}

}
