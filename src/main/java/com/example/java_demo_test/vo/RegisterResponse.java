package com.example.java_demo_test.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse {

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("verify_code")
	private int verifyCode;

	private String message;

	private LocalDateTime regTime;

	public RegisterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterResponse(String message, LocalDateTime regTime) {
		super();
		this.message = message;
		this.regTime = regTime;
	}

	public RegisterResponse(String sessionId, int verifyCode, String message) {
		super();
		this.sessionId = sessionId;
		this.verifyCode = verifyCode;
		this.message = message;
	}

	public RegisterResponse(String message) {
		super();
		this.message = message;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

}
