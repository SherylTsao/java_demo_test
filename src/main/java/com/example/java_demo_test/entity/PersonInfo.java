package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//springBoot託管???為什麼不是資料庫
@Table(name = "person_info")//對應資料表的欄位名稱
public class PersonInfo {

	@Id
	@Column(name = "id")//不能NULL空字串空白
	public String id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")//不能為負數
	private int age;

	@Column(name = "city")
	private String city;

	public PersonInfo() {
		super();
	}
	

	public PersonInfo(String id, String name, int age, String city) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.city = city;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	

}
