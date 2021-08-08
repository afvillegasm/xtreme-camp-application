package com.upgradeinc.extremecamp.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateCustomerRequestDTO {
	
	@NotNull(message = "{validation.error.message.firstname.notempty}")
	@Size(min = 1, max = 100, message = "{validation.error.message.firstname.maxlength}")
	private String firstName;
	@NotNull(message = "{validation.error.message.lastname.notempty}")
	@Size(min = 1, max = 100, message = "{validation.error.message.lastname.maxlength}")
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
