package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Login;

public class LoginResponse {
	List<Login> data;

	private String message;

	public LoginResponse() {
		super();

	}

	public LoginResponse(String message) {
		super();
		this.message = message;
	}

	public LoginResponse(List<Login> data, String message) {
		super();
		this.data = data;
		this.message = message;
	}

	public List<Login> getData() {
		return data;
	}

	public void setData(List<Login> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
