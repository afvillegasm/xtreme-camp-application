package com.upgradeinc.extremecamp.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookingInfoDTO {
	
	@Size(min = 1, max = 64, message = "{validation.error.message.bookingcode.maxlength}")
	private String bookingCode;
	@NotNull(message = "{validation.error.message.bookinginitdate.notempty}")
	@Pattern(regexp="\\d{2}-\\d{2}-\\d{4}", message = "{validation.error.message.bookinginitdate.invalidformat}")
	private String bookingInitDate;
	@NotNull(message = "{validation.error.message.bookingenddate.notempty}")
	@Pattern(regexp="\\d{2}-\\d{2}-\\d{4}", message = "{validation.error.message.bookingenddate.invalidformat}")
	private String bookingEndDate;
	
	public BookingInfoDTO() {

	}
	
	public BookingInfoDTO(String bookingCode, String bookingInitDate, String bookingEndDate) {
		this.bookingCode = bookingCode;
		this.bookingInitDate = bookingInitDate;
		this.bookingEndDate = bookingEndDate;
	}


	public String getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
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
