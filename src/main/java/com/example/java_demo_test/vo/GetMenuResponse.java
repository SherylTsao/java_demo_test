package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Menu;


public class GetMenuResponse {

	private Menu menu;//�]�ݩʬ����Menu,�i�H�ξ��Menu���ݩʤ�k,�n�^����(�~��)�ϥΪ̿�J��
	
	private String message;//�h�]�@���ݩʭn�^����(�~��)�ϥΪ̩ҿ�J��
	
	List<Menu> menuList;//�s�WList�ݩ�

	

	public GetMenuResponse(String message, List<Menu> menuList) {
		super();
		this.message = message;
		this.menuList = menuList;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public GetMenuResponse(List<Menu> menuList) {
		super();
		this.menuList = menuList;
	}

	public GetMenuResponse(Menu menu) {
		super();
		this.menu = menu;
	}

	public GetMenuResponse(String message) {
		this.message =message;
	}

	public GetMenuResponse() {
		
	}

	public GetMenuResponse(Menu menu, String message) {
		super();
		this.menu = menu;
		this.message = message;
	}

	

	public GetMenuResponse(List<Menu> menuList,String message ) {
		super();
		this.message = message;
		this.menuList = menuList;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
