package com.upgradeinc.extremecamp.booking.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingAvailabilityForCampSiteDateRangeResponseDTO {
	
	private Long idCampSite;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date bookingInitDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date bookingEndDate;
	private boolean available;
	ErrorStatusDTO status;
	
	public Long getIdCampSite() {
		return idCampSite;
	}
	public void setIdCampSite(Long idCampSite) {
		this.idCampSite = idCampSite;
	}
	public Date getBookingInitDate() {
		return bookingInitDate;
	}
	public void setBookingInitDate(Date bookingInitDate) {
		this.bookingInitDate = bookingInitDate;
	}
	public Date getBookingEndDate() {
		return bookingEndDate;
	}
	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}

}
