package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;

public class OrderResponse {

	private List<Menu> menuList;// 一次新增多筆menu
	
	private Map<String,Integer> orderMap;//表示餐點名稱,總價/折價
	
	
	
	private int discountPrice;//滿五百打9折

	private String message;// 回傳對應的餐點名稱跟價格message&回傳名稱/價格錯誤

	public  OrderResponse() {
		super();
	}

	public OrderResponse(String message) {
		super();
		this.message = message;
	}

	public OrderResponse(List<Menu> menuList, String message) {
		super();
		this.menuList = menuList;
		this.message = message;
	}

	public List<Menu> getMenuList() {// getset自動產生
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Map<String, Integer> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<String, Integer> orderMap) {
		this.orderMap = orderMap;
	}

	public OrderResponse(Map<String, Integer> orderMap, int discountPrice, String message) {
		super();
		this.orderMap = orderMap;
		this.discountPrice = discountPrice;
		this.message = message;
	}

}
