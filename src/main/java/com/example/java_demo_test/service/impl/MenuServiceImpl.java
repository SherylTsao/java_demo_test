package com.example.java_demo_test.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import com.example.java_demo_test.entity.Menu;

import com.example.java_demo_test.responsitory.MenuDao;

import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.GetMenuResponse;
import com.example.java_demo_test.vo.OrderResponse;

@Service // MenuServiceImpl�n��Spring Service�U��
public class MenuServiceImpl implements OrderService {

	@Autowired
	private MenuDao menuDao;

	Scanner scan = new Scanner(System.in);

	@Override
	public OrderResponse addMenus(List<Menu> menuList) {

		for (Menu menuItem : menuList) {// foreach�ˬd�C�����
			if (CollectionUtils.isEmpty(menuList)) { // ���פ��ର0
				// menuList == null||menuList.isEmpty()~�ư�null�Ϊť�
				return new OrderResponse("�s�W�\�I���~");
			} 

			if (!StringUtils.hasText(menuItem.getName())) {// �]���@���s�W�h��,�Y�ĤG�����ť�,���i�H
															// �\�I�W��!��[�o�����ť�:StringUtils]�Ϊ�[���r��:hasText]
				return new OrderResponse("�\�I�W�٤���ť�");// String message���غc��k
			}
			if (menuItem.getPrice() <= 0) {
				return new OrderResponse("���椣�ର�s");// String message���غc��k
			}
		}
		List<Menu> response = menuDao.saveAll(menuList);
		return new OrderResponse(response, "�s�W�\�I���\");// ��ӰѼƪ��غc��k
	}

	// ���b-�^�ǫ��AMenu,�S��OrderRwsponse
//	@Override
//	public Menu addMenus(List<Menu> menus) {
//		// ��J�P���\�I�W��
//		for (int index = 0; index < menus.size(); index++) {
//			Optional<Menu> op = menuDao.findById(menus.get(index).getName());
//			// ���椣�inull��0
//			if (menus.get(index).getPrice() < 0) {
//				System.out.println("���椣�i��0");
//				return new Menu();
//			} else {
//				menuDao.save(menus.get(index));
//			}
//			if (op.isPresent()) {
//				System.out.println("�\�I����");
//				return new Menu();
//			} else {
//				menuDao.save(menus.get(index));
//				return op.orElse(new Menu());
//
//			}
//
//		}
//		return null;
//
//	}
//===========================�I�\:�@����J�h��,�@���s�J��Ʈw==============================================
	@Override // �I�\��Map<�W��,�ƶq>
	public OrderResponse order0(Map<String, Integer> orderMap) {
		// �n���\�I�W�٪��Ŷ�
		List<String> nameList = new ArrayList<>();
		// �ˬd�C�����
		for (Entry<String, Integer> orderItem : orderMap.entrySet()) {
			// �\�I�ƶq���ର�t��
			if (orderItem.getValue() < 0) {
				// ��^��
				return new OrderResponse("�\�I�ƶq���ର�t��");
			}
			// �\�I�W��(Map��key)��ilist
			nameList.add(orderItem.getKey());
		}
		// �\�I�W�٦s�i��Ʈw,�A��result�ѼƱ��^�C���\�I�W��
		List<Menu> result = menuDao.findAllById(nameList);
		// ���F�a�J�`�@���\�I&�ƶq
		Map<String, Integer> finalOrderMap = new HashMap<>();
		int totalPrice = 0;
		// �ˬd�\�I�W�٬O�_���T
		// foreach�a�J�ϥΪ̿�J���\�I�W��
		for (Menu menu : result) {
			String item = menu.getName();
			int price = menu.getPrice();
			// �a�J�\�I�W�ٸ�ƶq
			for (Entry<String, Integer> map : orderMap.entrySet()) {

				// ���X�\�I�W��
				String keyName = map.getKey();
				int value = map.getValue();
				if (item.equals(keyName)) {
					int singleTotalPrice = price * value;
					totalPrice += singleTotalPrice;
					// ���F�a�J�`�@���\�I&�ƶq
					finalOrderMap.put(keyName, value);
				}
			}
		}
		// �T���B�⦡�j��500�����E��
		totalPrice = totalPrice > 500 ? (int) (totalPrice * 0.9) : totalPrice;
		// show�X�\�I�W��,�ƶq,�`��,�I�\���\
		return new OrderResponse(finalOrderMap, totalPrice, "�I�\���\");
	}

	// =======================�k2:�@���@���q��Ʈw��=================================
	// 0418HW �I�\��existById��k�g
	@Override // �I�\���Ѽƥ�Map<�W��,�ƶq>
	public OrderResponse order1(Map<String, Integer> orderMap) {
		// �n���\�I�W�٪��Ŷ�

		Map<String, Integer> finalOrderMap = new HashMap<>();
		boolean menuNameFind;
		int totalPrice = 0;
		// Map��foreach�ˬd�C�����
		for (Entry<String, Integer> orderItem : orderMap.entrySet()) {
			// ��map�i�H���X�\�I�ƶq(value��):�\�I�ƶq���ର�t��
			if (orderItem.getValue() < 0) {
				// ��^��:�^���̪����غc��k
				return new OrderResponse("�\�I�ƶq���ର�t��");
			}
			// �q��Ʈw�̧�O�_�q�b���\�I�W��,�p�G������\�I�W�ٴN�i�H��
			menuNameFind = menuDao.existsById(orderItem.getKey());
			// existsById�^�Ǫ��O���L��true,false
			// �Y������\�I�W�ٴN�i�H�I�\
			if (menuNameFind == true) {
				// �q��Ʈw���Did���(�\�I�W��),findById�Y�n�^�ǫh��optinal<Menu>���A
				// �A�qkey�ȧ���id���Ҧ����,�A��Menu���A��
				Menu menu = menuDao.findById(orderItem.getKey()).get();
				// �q��id�����Ҧ����,�A��X�䤤��price
				int price = menu.getPrice();
				// �q��id�����Ҧ����,�A��X�䤤��value�ƶq
				int value = orderItem.getValue();
				// ��X�I�\���C�����
				int singleTotalPrice = price * value;
				// �[�`���=�`��
				totalPrice += singleTotalPrice;
				// �N�Ӹ�ƪ��\�I�W��,�C�����,��J�s�ت�map
				finalOrderMap.put(menu.getName(), singleTotalPrice);
			}
		}
		// �p���`���p�G�W�L500�h���E��
		totalPrice = totalPrice > 500 ? (int) (totalPrice * 0.9) : totalPrice;
		// �^��:�^���إߪ��غc��k(Map�̪��C�@���\�I�W��&���,�`��,�@�q�T��)
		return new OrderResponse(finalOrderMap, totalPrice, "�I�\���\");
	}

	// =======================�k3�Ѯv�Ϊ�=============================================
	// �I�\����k,�Ѽƥ�map��,�]���n��ϥΪ̩��I���\�I�W��,��ƶq
	public OrderResponse order2(Map<String, Integer> orderMap) {
		// ��̫�ϥΪ̥��T�I���\�I�W��,�ƶq,�A�h�����
		Map<String, Integer> finalOrderMap = new HashMap<>();
		int totalPrice = 0;

		// �qmap�̤@���@�������X�ϥΪ��I���\�I��ƶq,�h����I���W�ٸ�ƶq�藍��
		for (Entry<String, Integer> orderItem : orderMap.entrySet()) {
			// �p�G�ϥΪ��I���ƶq���t�Ʃιs
			if (orderItem.getValue() <= 0) {
				continue;
			}
			// �qid(�\�I�W��)�h��Ʈw�����,���ަ��S�����쳣�|��iop
			Optional<Menu> op = menuDao.findById(orderItem.getKey());
			// �ư��ϥΪ̶��I���\�I
			if (!op.isPresent()) {
				// �N���I���\,�^�W�B
				continue;
			}
			// �q��Ʈw��X����
			int price = op.get().getPrice();
			// value�ƶq�n�qmap��
			totalPrice += price * orderItem.getValue();
		}
		totalPrice = totalPrice > 500 ? (int) (totalPrice * 0.9) : totalPrice;
		return new OrderResponse(finalOrderMap, totalPrice, "�I�\���\");
	}

	// =================================================================================

	@Override
	public OrderResponse getAllMenus() {

		return new OrderResponse(menuDao.findAll(), "���o�Ҧ��\�I���\");
	}

	@Override
	public GetMenuResponse getMenuByName(String name) {
		if (!StringUtils.hasText(name)) {// name == null || name.isEmpty()
			return new GetMenuResponse("�\�I�W�٤��ର�ť�");
		}
		Menu menu = menuDao.findByName(name);
		if (menu == null) {
			return new GetMenuResponse("�\�I�W�ٿ��~");
		}
		return new GetMenuResponse(menu, "���\����\�I");
	}

//======================================================================
	@Override // (List<Menu> menuList)�@����J�h�����
	public GetMenuResponse updateMenuPrice(List<Menu> menuList) {
		// <���W��,����>��menuList��imenuMap
		// �u��ק��µ��,����s�W�s���
		List<Menu> menu = menuDao.findAll();
		List<Menu> newMenu = new ArrayList<>();

		if (CollectionUtils.isEmpty(menuList)) {// �Y�o�Ӷ��X���׬O�Ū�,��J���㵧�Ҭ���

			return new GetMenuResponse("�s�W���\�I�W�٤��o���ť�");

		}
		for (Menu item : menuList) {// foreach�i�H��list�̪���Ƥ@���@�����X��
			if (!StringUtils.hasText(item.getName())) {// �ĤG���H�W����Ƥ��ର�ťթΨS���r��

				return new GetMenuResponse("�s�W���\�I�W�٤��o���ť�");
			}

			if (item.getPrice() <= 0) {

				return new GetMenuResponse("�s�W���\�I���椣�o�p�󵥩�s��");
			}
			for (Menu itemDao : menu) {
				if (itemDao.getName().equals(item.getName())) {
					itemDao.setPrice(item.getPrice());
					newMenu.add(itemDao);

				}
			}
		}

		menu = menuDao.saveAll(newMenu);
		if (newMenu.size() != menuList.size()) {// �Y�쥻���פ�����s������,�N�����Ѫ��\�I�W��

			return new GetMenuResponse("���� : " + (menuList.size() - newMenu.size()) + " ���\ : " + newMenu.size(), menu);
		}

		return new GetMenuResponse("", menu);
	}

	// ================�k2(������)=================================================
//	@Override // (List<Menu> menuList)�@����J�h�����
//	public GetMenuResponse updateMenuPrice(List<Menu> menuList) {
//		List<Menu> updateMenu = new ArrayList<>();
//		for (Menu menu : menuList) {
//			boolean bool = menuDao.existsById(menu.getName());
//			if (menuDao.existsById(menu.getName())) {
//				updateMenu.add(menu);
//			}
//		}
//		if (updateMenu.size() == 0) {
//			return new GetMenuResponse("�d�L���");
//		}
//
//		return new GetMenuResponse(menuDao.saveAll(updateMenu), "successful");
//
//	}
//===================0417�@�~============================================

//	@Override
//	public GetMenuResponse getMenuByName(String name) {//OrderRequest��name
//		//���b
//		if (!StringUtils.hasText(name)) {// name == null || name.isEmpty()
//			return new GetMenuResponse("�\�I�W�٤��ର�ť�");
//		}
//		
//		
//        //�ϥΪ̿�J���\�I�W��name�h�M��Ʈw���,�p�G�����,���L����Ӹ�ƪ��Ҧ�detail
//		Optional<Menu> op = menuDao.findById(name);// �q��Ʈw����\�I�W��,�h������Menu
//		if (!op.isPresent()) {// �S������,�N��W�ٿ��~
//			return new GetMenuResponse("�\�I�W�ٿ��~");
//		} // ���Xop(Menu)
//		Menu m = op.get();
//		return new GetMenuResponse(m, "���\����\�I");

//	}
//====================================================================

}
