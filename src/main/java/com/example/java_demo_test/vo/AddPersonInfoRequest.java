package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;

public class AddPersonInfoRequest {

	private List<PersonInfo> personInfoList;
	private String id;

	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
