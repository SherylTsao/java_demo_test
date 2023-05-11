package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderRequest;
import com.example.java_demo_test.vo.OrderResponse;

@RestController // ��controller�U��
public class OrderController {// Controller�p���~�ɳs���������f

	@Autowired // ���w�U�����O
	private OrderService orderService;// �s���W�@�hService
	
	@PostMapping(value = "order0") // �s���~��
	public OrderResponse order0(@RequestBody OrderRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return orderService.order0(request.getFinalOrderMap());// �s������

	}
	@PostMapping(value = "order1") // �s���~��
	public OrderResponse order1(@RequestBody OrderRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return orderService.order1(request.getFinalOrderMap());// �s������

	}
	@PostMapping(value = "order2") // �s���~��
	public OrderResponse order2(@RequestBody OrderRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return orderService.order2(request.getFinalOrderMap());// �s������

	}

	@PostMapping(value = "add_menus") // �s���~��
	public OrderResponse addMenus(@RequestBody OrderRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return orderService.addMenus(request.getMenuList());// �s������

	}

	@PostMapping(value = "get_All_Menus") // �s���~��
	public OrderResponse getAllMenus() {
		return orderService.getAllMenus();

	}

//MenuDao�۩w�q����k��JPA����������k�W�٬O�@�˪�,�ҥHcotroller����
//	@PostMapping(value = "get_Menu_By_Name") // �s���~��
//	public GetMenuResponse getMenuByName(@RequestBody OrderRequest request) {
//		// �q�`��Service��k�@�˦W��,����copy
//		return orderService.getMenuByName(request.getName());// �s������
//	}
	@PostMapping(value = "get_Menu_By_Name") // �s���~��
	public GetMenuResponse getMenuByName(@RequestBody OrderRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return orderService.getMenuByName(request.getName());// �s������
	}
	
	@PostMapping(value = "update_Menu_Price") // �s���~��
	public GetMenuResponse updateMenuPrice(@RequestBody OrderRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return orderService.updateMenuPrice(request.getMenuList());// �s������
	}
	
	
	
}
