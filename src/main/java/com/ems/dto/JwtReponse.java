package com.ems.dto;

public class JwtReponse {

	 private String token;

	public JwtReponse(String token) {
		super();
		this.token = token;
	}

	public JwtReponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtReponse [token=" + token + "]";
	}


}
