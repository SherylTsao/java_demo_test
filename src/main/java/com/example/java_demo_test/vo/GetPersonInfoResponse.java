package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class GetPersonInfoResponse {
	
	

	private String message;

	private PersonInfo personInfo;
	
	private Integer age;
	
	

	private List<PersonInfo> info;

	public GetPersonInfoResponse() {
		super();

	}
	

	


	public GetPersonInfoResponse(String message) {
		super();
		this.message = message;
	}

	public GetPersonInfoResponse(PersonInfo personInfo, String message) {
		super();
		this.personInfo = personInfo;
		this.message = message;
	}

	public GetPersonInfoResponse(List<PersonInfo> info, String message) {
		super();
		this.info = info;
		this.message = message;

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

	public List<PersonInfo> getInfo() {
		return info;
	}

	public void setInfo(List<PersonInfo> info) {
		this.info = info;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	

	
}
