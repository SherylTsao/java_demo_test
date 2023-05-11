package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "new_menu2")
@IdClass(value = NewMenu2Id.class)//另外放id的class名稱.class
public class NewMenu2 {

	/*
	 * 複合id(屬性用):
	 * 1.另外建一個class,copy two Id 
	 * 2.get&set產生!!建構方法生成
	 * 3.class 要implements Serializable{}
	 */
	@Id
	@Column(name = "category")
	private String category;

	@Id
	@Column(name = "cook_method")
	private String cook_method;

	@Column(name = "price")
	private int price;

	public NewMenu2() {
		super();

	}

	public NewMenu2(String category, String cook_method, int price) {
		super();
		this.category = category;
		this.cook_method = cook_method;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCook_method() {
		return cook_method;
	}

	public void setCook_method(String cook_method) {
		this.cook_method = cook_method;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
