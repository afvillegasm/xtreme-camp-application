package com.upgradeinc.extremecamp.api.dto;

public class FindBookingByBookingCodeResponseDTO {
	
	CampSiteInfoDTO campSiteInfo;
	CustomerDTO customerInfo;
	BookingInfoDTO bookingInfo;
	ErrorStatusDTO status;
	
	public CampSiteInfoDTO getCampSiteInfo() {
		return campSiteInfo;
	}
	public void setCampSiteInfo(CampSiteInfoDTO campSiteInfo) {
		this.campSiteInfo = campSiteInfo;
	}
	public CustomerDTO getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerDTO customerInfo) {
		this.customerInfo = customerInfo;
	}
	public BookingInfoDTO getBookingInfo() {
		return bookingInfo;
	}
	public void setBookingInfo(BookingInfoDTO bookingInfo) {
		this.bookingInfo = bookingInfo;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}
	
}
