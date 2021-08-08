package com.upgradeinc.extremecamp.booking.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingDTO {
	
	private Long id;
	@NotNull(message = "{validation.error.message.idcampsite.notempty}")
	private Long idCampSite;
	@NotNull(message = "{validation.error.message.idcustomer.notempty}")
	private Long idCustomer;
	@Size(min = 1, max = 64, message = "{validation.error.message.bookingcode.maxlength}")
	private String bookingCode;
	@NotNull(message = "{validation.error.message.bookinginitdate.notempty}")
	@Pattern(regexp="\\d{2}-\\d{2}-\\d{4}", message = "{validation.error.message.bookinginitdate.invalidformat}")
	private String bookingInitDate;
	@NotNull(message = "{validation.error.message.bookingenddate.notempty}")
	@Pattern(regexp="\\d{2}-\\d{2}-\\d{4}", message = "{validation.error.message.bookingenddate.invalidformat}")
	private String bookingEndDate;
	
	public BookingDTO() {

	}
	
	public BookingDTO(Long id, Long idCampSite, Long idCustomer, String bookingCode, String bookingInitDate,
			String bookingEndDate) {
		super();
		this.id = id;
		this.idCampSite = idCampSite;
		this.idCustomer = idCustomer;
		this.bookingCode = bookingCode;
		this.bookingInitDate = bookingInitDate;
		this.bookingEndDate = bookingEndDate;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCampSite() {
		return idCampSite;
	}
	public void setIdCampSite(Long idCampSite) {
		this.idCampSite = idCampSite;
	}
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
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
