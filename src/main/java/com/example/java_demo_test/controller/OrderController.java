package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderRequest;
import com.example.java_demo_test.vo.OrderResponse;

@RestController // 給controller託管
public class OrderController {// Controller聯絡外界連接內部窗口

	@Autowired // 指定託管類別
	private OrderService orderService;// 連結上一層Service
	
	@PostMapping(value = "order0") // 連結外部
	public OrderResponse order0(@RequestBody OrderRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return orderService.order0(request.getFinalOrderMap());// 連結內部

	}
	@PostMapping(value = "order1") // 連結外部
	public OrderResponse order1(@RequestBody OrderRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return orderService.order1(request.getFinalOrderMap());// 連結內部

	}
	@PostMapping(value = "order2") // 連結外部
	public OrderResponse order2(@RequestBody OrderRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return orderService.order2(request.getFinalOrderMap());// 連結內部

	}

	@PostMapping(value = "add_menus") // 連結外部
	public OrderResponse addMenus(@RequestBody OrderRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return orderService.addMenus(request.getMenuList());// 連結內部

	}

	@PostMapping(value = "get_All_Menus") // 連結外部
	public OrderResponse getAllMenus() {
		return orderService.getAllMenus();

	}

//MenuDao自定義的方法跟JPA本身有的方法名稱是一樣的,所以cotroller不變
//	@PostMapping(value = "get_Menu_By_Name") // 連結外部
//	public GetMenuResponse getMenuByName(@RequestBody OrderRequest request) {
//		// 通常跟Service方法一樣名稱,直接copy
//		return orderService.getMenuByName(request.getName());// 連結內部
//	}
	@PostMapping(value = "get_Menu_By_Name") // 連結外部
	public GetMenuResponse getMenuByName(@RequestBody OrderRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return orderService.getMenuByName(request.getName());// 連結內部
	}
	
	@PostMapping(value = "update_Menu_Price") // 連結外部
	public GetMenuResponse updateMenuPrice(@RequestBody OrderRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return orderService.updateMenuPrice(request.getMenuList());// 連結內部
	}
	
	
	
}
