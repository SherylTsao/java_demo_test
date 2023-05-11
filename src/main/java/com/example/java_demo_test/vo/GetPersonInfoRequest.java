package com.example.java_demo_test.vo;

public class GetPersonInfoRequest {
	

	private String name;
	
	private String city;
	
	private String id;
	
	private Integer age;
	
	private Integer age1;
	
	

	public GetPersonInfoRequest(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge1() {
		return age1;
	}

	public void setAge1(Integer age1) {
		this.age1 = age1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
