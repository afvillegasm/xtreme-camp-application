package com.upgradeinc.extremecamp.booking.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingAvailabilityForCampSiteDateRangeRequestDTO {
	
	@NotNull
	private Long idCampSite;
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date bookingInitDate;
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date bookingEndDate;
	
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

}
