package com.upgradeinc.extremecamp.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class CampSiteInfoDTO {

	private Long id;
	@NotNull(message = "{validation.error.message.name.notempty}")
	@Size(min = 1, max = 100, message = "{validation.error.message.name.maxlength}")
	private String name;
	@NotNull(message = "{validation.error.message.description.notempty}")
	@Size(min = 1, max = 300, message = "{validation.error.message.description.maxlength}")
	private String description;
	@NotNull(message = "{validation.error.message.foundationdate.notempty}")
	@Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "{validation.error.message.foundationdate.invalidformat}")
	@ApiModelProperty(
			  value = "dateFormat: dd-MM-yyyy",
			  name = "foundationDate",
			  dataType = "String",
			  example = "31-12-1999")
	private String foundationDate;

	public CampSiteInfoDTO() {
			
		}

	public CampSiteInfoDTO(Long id, String name, String description, String foundationDate) {
			
			this.id = id;
			this.name = name;
			this.description = description;
			this.foundationDate = foundationDate;
			
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

}
