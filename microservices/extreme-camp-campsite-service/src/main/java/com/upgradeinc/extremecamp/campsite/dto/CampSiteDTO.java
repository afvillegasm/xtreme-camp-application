package com.upgradeinc.extremecamp.campsite.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampSiteDTO {
	
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull
	private Date foundationDate;
	@NotNull
	private Integer maxNumReservationsPerDay;
	
	private String username;
	
	public CampSiteDTO() {
		
	}
	
	public CampSiteDTO(Long id, String name, String description, Date foundationDate, Integer maxNumReservationsPerDay, String username) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.foundationDate = foundationDate;
		this.maxNumReservationsPerDay = maxNumReservationsPerDay;
		this.username = username;
		
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
	public Date getFoundationDate() {
		return foundationDate;
	}
	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}
	public Integer getMaxNumReservationsPerDay() {
		return maxNumReservationsPerDay;
	}
	public void setMaxNumReservationsPerDay(Integer maxNumReservationsPerDay) {
		this.maxNumReservationsPerDay = maxNumReservationsPerDay;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
