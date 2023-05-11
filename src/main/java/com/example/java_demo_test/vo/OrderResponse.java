package com.example.java_demo_test.vo;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;

public class OrderResponse {

	private List<Menu> menuList;// �@���s�W�h��menu
	
	private Map<String,Integer> orderMap;//����\�I�W��,�`��/���
	
	
	
	private int discountPrice;//�����ʥ�9��

	private String message;// �^�ǹ������\�I�W�ٸ����message&�^�ǦW��/������~

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

	public List<Menu> getMenuList() {// getset�۰ʲ���
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
