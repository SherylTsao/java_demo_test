package com.example.java_demo_test.entity;

import java.io.Serializable;

public class NewMenu2Id implements Serializable{
	/*
	 * �ƦXid(�ݩʥ�):
	 * 1.�t�~�ؤ@��class,copy two Id 
	 * 2.get&set����!!�غc��k�ͦ�
	 * 3.class �nimplements Serializable{}
	 */
	private static final long serialVersionUID = 1L;

	private String category;

	private String cook_method;

	public NewMenu2Id() {
		super();

	}

	public NewMenu2Id(String category, String cook_method) {
		super();
		this.category = category;
		this.cook_method = cook_method;
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

}
