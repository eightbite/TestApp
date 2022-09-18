package com.example.demo.app;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class userRegistrationForm {
	
	@NotNull(message = "username is not blank")
	@Size(min = 1, max = 20, message = "Please input 20characters or less")
	private String name;
	
	@NotNull(message = "email is not blank")
	@Email(message = "Invalid E-mail Format")
	private String email;
	
	@NotNull(message = "password is not blank")
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
