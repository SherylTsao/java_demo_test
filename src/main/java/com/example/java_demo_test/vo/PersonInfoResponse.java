package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class PersonInfoResponse {
	
	private int successful;

	private String message;

	private List<PersonInfo> resPersonInfo;

	private PersonInfo personInfo;

	public PersonInfoResponse() {
	}
	

	public PersonInfoResponse(int successful, String message) {
		super();
		this.successful = successful;
		this.message = message;
	}


	public PersonInfoResponse(PersonInfo personInfo, String message) {
		this.personInfo = personInfo;
		this.message = message;
	}

	public PersonInfoResponse(String message) {
		super();
		this.message = message;
	}

	public PersonInfoResponse(List<PersonInfo> saveNewIds, String string) {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public List<PersonInfo> getResPersonInfo() {
		return resPersonInfo;
	}

	public void setResPersonInfo(List<PersonInfo> resPersonInfo) {
		this.resPersonInfo = resPersonInfo;
	}

	public int getSuccessful() {
		return successful;
	}

	public void setSuccessful(int successful) {
		this.successful = successful;
	}

}
