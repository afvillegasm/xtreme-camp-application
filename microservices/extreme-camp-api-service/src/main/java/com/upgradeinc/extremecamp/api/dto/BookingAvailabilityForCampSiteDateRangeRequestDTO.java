package com.upgradeinc.extremecamp.api.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class BookingAvailabilityForCampSiteDateRangeRequestDTO {
	
	@NotNull(message = "{validation.error.message.idcampsite.notempty}")
	private Long idCampSite;
	@NotNull
	//@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "{validation.error.message.bookinginitdate.notempty}")
	@Pattern(regexp="\\d{2}-\\d{2}-\\d{4}", message = "{validation.error.message.bookinginitdate.invalidformat}")
	@ApiModelProperty(
			  value = "dateFormat: dd-MM-yyyy",
			  name = "bookingInitDate",
			  dataType = "String",
			  example = "31-12-1999")
	private String bookingInitDate;
	@NotNull(message = "{validation.error.message.bookingenddate.notempty}")
	@Pattern(regexp="\\d{2}-\\d{2}-\\d{4}", message = "{validation.error.message.bookingenddate.invalidformat}")
	@ApiModelProperty(
			  value = "dateFormat: dd-MM-yyyy",
			  name = "bookingEndDate",
			  dataType = "String",
			  example = "31-12-1999")
	private String bookingEndDate;
	
	public Long getIdCampSite() {
		return idCampSite;
	}
	public void setIdCampSite(Long idCampSite) {
		this.idCampSite = idCampSite;
	}
	public String getBookingInitDate() {
		return bookingInitDate;
	}
	public void setBookingInitDate(String bookingInitDate) {
		this.bookingInitDate = bookingInitDate;
	}
	public String getBookingEndDate() {
		return bookingEndDate;
	}
	public void setBookingEndDate(String bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

}
