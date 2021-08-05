package com.upgradeinc.extremecamp.booking.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingDTO {
	
	private Long id;
	@NotNull
	private Long idCampSite;
	@NotNull
	private Long idCustomer;
	
	private String bookingCode;
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date bookingInitDate;
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date bookingEndDate;
	
	public BookingDTO() {

	}
	
	public BookingDTO(Long id, Long idCampSite, Long idCustomer, String bookingCode, Date bookingInitDate,
			Date bookingEndDate) {
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
