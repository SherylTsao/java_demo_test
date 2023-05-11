package com.example.java_demo_test.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {

	public OrderResponse addMenus(List<Menu> menuList);// �s�W�\�I(��k)

	public OrderResponse order0(Map<String, Integer> orderMap);// �I�\(��k)
	public OrderResponse order1(Map<String, Integer> orderMap);// �I�\(��k)
	public OrderResponse order2(Map<String, Integer> orderMap);// �I�\(��k)
	
	public OrderResponse getAllMenus();
	
	public GetMenuResponse getMenuByName(String name);//�\�I�W��(��k)
	
	/*
	 * 1.�u��ק�w�s�b��������(���椣�ର�t��)
	 * 1-1����s�W�s���
	 * 2.��^�ק�᪺���W�٤λ���
	 */
	public GetMenuResponse updateMenuPrice(List<Menu> menuList);
}
