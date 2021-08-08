package com.upgradeinc.extremecamp.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class CreateBookingRequestDTO {
	
	@NotNull(message = "{validation.error.message.idcampsite.notempty}")
	private Long idCampSite;
	@NotNull(message = "{validation.error.message.idcustomer.notempty}")
	private Long idCustomer;
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
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
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
