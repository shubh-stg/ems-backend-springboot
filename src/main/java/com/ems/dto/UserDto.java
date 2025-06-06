package com.ems.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;



public class UserDto {
	
	@NotEmpty(message="Cannot be blank")
    private String username;
	@NotEmpty(message="Cannot be blank")
    private String password;
	@NotEmpty(message="Cannot be blank")
    private String name;
    
    
    @Email
    @NotEmpty(message="Email should not be empty")
    private String email;
    
    private Set<String> roles;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(String username, String password, String name, String email, Set<String> roles) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
    
}
