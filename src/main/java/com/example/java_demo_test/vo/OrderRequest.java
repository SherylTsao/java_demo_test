package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {

	@JsonProperty("menu_list") // 對應Postman名稱
	private List<Menu> menuList;// List當作屬性

	private Map<String, Integer> finalOrderMap;

	private String name;// 使用者(外部)輸入(要求)的東西

	public Map<String, Integer> getFinalOrderMap() {
		return finalOrderMap;
	}

	public void setFinalOrderMap(Map<String, Integer> finalOrderMap) {
		this.finalOrderMap = finalOrderMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenuList() {// getset右鍵產生
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

}
