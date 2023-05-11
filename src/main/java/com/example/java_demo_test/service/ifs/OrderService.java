package com.example.java_demo_test.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

public interface OrderService {

	public OrderResponse addMenus(List<Menu> menuList);// 新增餐點(方法)

	public OrderResponse order0(Map<String, Integer> orderMap);// 點餐(方法)
	public OrderResponse order1(Map<String, Integer> orderMap);// 點餐(方法)
	public OrderResponse order2(Map<String, Integer> orderMap);// 點餐(方法)
	
	public OrderResponse getAllMenus();
	
	public GetMenuResponse getMenuByName(String name);//餐點名稱(方法)
	
	/*
	 * 1.只能修改已存在的菜單價格(價格不能為負數)
	 * 1-1不能新增新菜單
	 * 2.返回修改後的菜單名稱及價格
	 */
	public GetMenuResponse updateMenuPrice(List<Menu> menuList);
}
