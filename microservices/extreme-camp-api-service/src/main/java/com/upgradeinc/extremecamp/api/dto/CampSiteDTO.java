package com.upgradeinc.extremecamp.api.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampSiteDTO {
	
	private Long id;
	@NotNull(message = "{validation.error.message.name.notempty}")
	@Size(min = 1, max = 100, message = "{validation.error.message.name.maxlength}")
	private String name;
	@NotNull(message = "{validation.error.message.description.notempty}")
	@Size(min = 1, max = 300, message = "{validation.error.message.description.maxlength}")
	private String description;
	@NotNull(message = "{validation.error.message.foundationdate.notempty}")
	@Pattern(regexp="\\d{2}-\\d{2}-\\d{4}", message = "{validation.error.message.foundationdate.invalidformat}")
	private String foundationDate;
	@NotNull(message = "{validation.error.message.maxnumreservationsperday.notempty}")
	@Min(value = 1, message = "{validation.error.message.maxnumreservationsperday.minlength}")
	private Integer maxNumReservationsPerDay;
	@NotNull(message = "{validation.error.message.maxnumdaysincludedindaterange.notempty}")
	@Min(value = 1, message = "{validation.error.message.maxnumdaysincludedindaterange.minlength}")
	private Integer maxNumDaysIncludedInDateRange;
	
	public CampSiteDTO() {
		
	}
	
	public CampSiteDTO(Long id, String name, String description, String foundationDate, Integer maxNumReservationsPerDay, Integer maxNumDaysIncludedInDateRange) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.foundationDate = foundationDate;
		this.maxNumReservationsPerDay = maxNumReservationsPerDay;
		this.maxNumDaysIncludedInDateRange = maxNumDaysIncludedInDateRange;
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFoundationDate() {
		return foundationDate;
	}
	public void setFoundationDate(String foundationDate) {
		this.foundationDate = foundationDate;
	}
	public Integer getMaxNumReservationsPerDay() {
		return maxNumReservationsPerDay;
	}
	public void setMaxNumReservationsPerDay(Integer maxNumReservationsPerDay) {
		this.maxNumReservationsPerDay = maxNumReservationsPerDay;
	}
	public Integer getMaxNumDaysIncludedInDateRange() {
		return maxNumDaysIncludedInDateRange;
	}
	public void setMaxNumDaysIncludedInDateRange(Integer maxNumDaysIncludedInDateRange) {
		this.maxNumDaysIncludedInDateRange = maxNumDaysIncludedInDateRange;
	}

}
