package com.upgradeinc.extremecamp.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateCustomerRequestDTO {
	
	@NotNull(message = "{validation.error.message.firstname.notempty}")
	@Size(min = 1, max = 100, message = "{validation.error.message.firstname.maxlength}")
	private String firstName;
	@NotNull(message = "{validation.error.message.lastname.notempty}")
	@Size(min = 1, max = 100, message = "{validation.error.message.lastname.maxlength}")
	private String lastName;
	@NotNull(message = "{validation.error.message.email.notempty}")
	@Email(message = "{validation.error.message.email.invalid}")
	private String email;
	
	public CreateCustomerRequestDTO() {
		
	}
	
	public CreateCustomerRequestDTO(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
