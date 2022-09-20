package com.example.demo.app;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class userRegistrationForm {
	
	@NotBlank(message = "username is not blank")
	@Size(max = 20, message = "Please input 20characters or less")
	private String name;
	
	@NotBlank(message = "email is not blank")
	@Email(message = "Invalid E-mail Format")
	private String email;
	
	@Size(min = 6, message = "Please input min 6characters")
	private String password;

	public userRegistrationForm() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
