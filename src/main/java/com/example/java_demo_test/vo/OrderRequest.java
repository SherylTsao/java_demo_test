package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {

	@JsonProperty("menu_list") // ����Postman�W��
	private List<Menu> menuList;// List��@�ݩ�

	private Map<String, Integer> finalOrderMap;

	private String name;// �ϥΪ�(�~��)��J(�n�D)���F��

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

	public List<Menu> getMenuList() {// getset�k�䲣��
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

}
