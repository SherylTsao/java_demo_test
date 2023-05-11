package com.example.java_demo_test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "login")
@JsonInclude(JsonInclude.Include.NON_NULL)//Json的value為null時,不顯示key&value
public class Login {

	@Id
	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "city")
	private String city;

	/*
	 *  private Date regTime;
	 *           產生 Date ==> new Date(); 
	 *           產生當下的時間
	 *        
	 *  private LocalDateTime regTime
	 *           產生 LocalDateTime ==> LocalDateTime.now();
	 *           產生當下的日期跟時間
	 */
	@Column(name = "register_time")
	private LocalDateTime regTime;
	
	@Column(name = "active")
	private boolean isActive;//名稱通常會加"is"代表資料型態是boolean

	public Login() {
		super();

	}
	

	public Login(String account, String password, String name, int age, String city, LocalDateTime regTime) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.age = age;
		this.city = city;
		this.regTime = regTime;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isActive() {//boolean的get取得:用is
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

	

	

}
