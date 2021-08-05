package com.upgradeinc.extremecamp.booking.dto;

import java.util.List;

import com.upgradeinc.extremecamp.booking.dto.BookingDTO;
import com.upgradeinc.extremecamp.booking.dto.ErrorStatusDTO;

public class BookingListResponseDTO {
	
	List<BookingDTO> results;
	ErrorStatusDTO status;
	
	public List<BookingDTO> getResults() {
		return results;
	}
	public void setResults(List<BookingDTO> results) {
		this.results = results;
	}
	public ErrorStatusDTO getStatus() {
		return status;
	}
	public void setStatus(ErrorStatusDTO status) {
		this.status = status;
	}

}
