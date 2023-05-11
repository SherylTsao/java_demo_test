package com.example.java_demo_test.vo;



public class LoginRequest {
	
	
	private String account;
	
	private String password;
	
	private String name;
	
	private String city;
	
	private int age;
	

	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginRequest(String account, String password, String name, String city, int age) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.city = city;
		this.age = age;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

}
