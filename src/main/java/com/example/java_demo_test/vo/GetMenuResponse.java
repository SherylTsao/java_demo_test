package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.entity.Menu;


public class GetMenuResponse {

	private Menu menu;//設屬性為整個Menu,可以用整個Menu的屬性方法,要回應給(外部)使用者輸入的
	
	private String message;//多設一個屬性要回應給(外部)使用者所輸入的
	
	List<Menu> menuList;//新增List屬性

	

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
