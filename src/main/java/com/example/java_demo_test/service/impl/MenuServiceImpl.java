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

@Service // MenuServiceImpl要給Spring Service託管
public class MenuServiceImpl implements OrderService {

	@Autowired
	private MenuDao menuDao;

	Scanner scan = new Scanner(System.in);

	@Override
	public OrderResponse addMenus(List<Menu> menuList) {

		for (Menu menuItem : menuList) {// foreach檢查每筆資料
			if (CollectionUtils.isEmpty(menuList)) { // 長度不能為0
				// menuList == null||menuList.isEmpty()~排除null及空白
				return new OrderResponse("新增餐點錯誤");
			} 

			if (!StringUtils.hasText(menuItem.getName())) {// 因為一次新增多筆,若第二筆為空白,不可以
															// 餐點名稱!不[得為全空白:StringUtils]或空[有字串:hasText]
				return new OrderResponse("餐點名稱不能空白");// String message的建構方法
			}
			if (menuItem.getPrice() <= 0) {
				return new OrderResponse("價格不能為零");// String message的建構方法
			}
		}
		List<Menu> response = menuDao.saveAll(menuList);
		return new OrderResponse(response, "新增餐點成功");// 兩個參數的建構方法
	}

	// 防呆-回傳型態Menu,沒有OrderRwsponse
//	@Override
//	public Menu addMenus(List<Menu> menus) {
//		// 輸入同樣餐點名稱
//		for (int index = 0; index < menus.size(); index++) {
//			Optional<Menu> op = menuDao.findById(menus.get(index).getName());
//			// 價格不可null或0
//			if (menus.get(index).getPrice() < 0) {
//				System.out.println("價格不可為0");
//				return new Menu();
//			} else {
//				menuDao.save(menus.get(index));
//			}
//			if (op.isPresent()) {
//				System.out.println("餐點重複");
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
//===========================點餐:一次輸入多筆,一次存入資料庫==============================================
	@Override // 點餐用Map<名稱,數量>
	public OrderResponse order0(Map<String, Integer> orderMap) {
		// 要放餐點名稱的空間
		List<String> nameList = new ArrayList<>();
		// 檢查每筆資料
		for (Entry<String, Integer> orderItem : orderMap.entrySet()) {
			// 餐點數量不能為負數
			if (orderItem.getValue() < 0) {
				// 返回物
				return new OrderResponse("餐點數量不能為負數");
			}
			// 餐點名稱(Map的key)放進list
			nameList.add(orderItem.getKey());
		}
		// 餐點名稱存進資料庫,再用result參數接回每個餐點名稱
		List<Menu> result = menuDao.findAllById(nameList);
		// 為了帶入總共的餐點&數量
		Map<String, Integer> finalOrderMap = new HashMap<>();
		int totalPrice = 0;
		// 檢查餐點名稱是否正確
		// foreach帶入使用者輸入的餐點名稱
		for (Menu menu : result) {
			String item = menu.getName();
			int price = menu.getPrice();
			// 帶入餐點名稱跟數量
			for (Entry<String, Integer> map : orderMap.entrySet()) {

				// 取出餐點名稱
				String keyName = map.getKey();
				int value = map.getValue();
				if (item.equals(keyName)) {
					int singleTotalPrice = price * value;
					totalPrice += singleTotalPrice;
					// 為了帶入總共的餐點&數量
					finalOrderMap.put(keyName, value);
				}
			}
		}
		// 三元運算式大於500元打九折
		totalPrice = totalPrice > 500 ? (int) (totalPrice * 0.9) : totalPrice;
		// show出餐點名稱,數量,總價,點餐成功
		return new OrderResponse(finalOrderMap, totalPrice, "點餐成功");
	}

	// =======================法2:一筆一筆從資料庫取=================================
	// 0418HW 點餐用existById方法寫
	@Override // 點餐的參數用Map<名稱,數量>
	public OrderResponse order1(Map<String, Integer> orderMap) {
		// 要放餐點名稱的空間

		Map<String, Integer> finalOrderMap = new HashMap<>();
		boolean menuNameFind;
		int totalPrice = 0;
		// Map用foreach檢查每筆資料
		for (Entry<String, Integer> orderItem : orderMap.entrySet()) {
			// 用map可以取出餐點數量(value值):餐點數量不能為負數
			if (orderItem.getValue() < 0) {
				// 返回物:回應裡的的建構方法
				return new OrderResponse("餐點數量不能為負數");
			}
			// 從資料庫裡找是否從在此餐點名稱,如果有找到餐點名稱就可以用
			menuNameFind = menuDao.existsById(orderItem.getKey());
			// existsById回傳的是布林值true,false
			// 若有找到餐點名稱就可以點餐
			if (menuNameFind == true) {
				// 從資料庫找到主id欄位(餐點名稱),findById若要回傳則為optinal<Menu>型態
				// 再從key值找到該id的所有資料,再用Menu型態接
				Menu menu = menuDao.findById(orderItem.getKey()).get();
				// 從該id的欄位所有資料,再抓出其中的price
				int price = menu.getPrice();
				// 從該id的欄位所有資料,再抓出其中的value數量
				int value = orderItem.getValue();
				// 算出點餐的每筆單價
				int singleTotalPrice = price * value;
				// 加總單價=總價
				totalPrice += singleTotalPrice;
				// 將該資料的餐點名稱,每筆單價,放入新建的map
				finalOrderMap.put(menu.getName(), singleTotalPrice);
			}
		}
		// 計算總價如果超過500則打九折
		totalPrice = totalPrice > 500 ? (int) (totalPrice * 0.9) : totalPrice;
		// 回傳:回應建立的建構方法(Map裡的每一筆餐點名稱&單價,總價,一段訊息)
		return new OrderResponse(finalOrderMap, totalPrice, "點餐成功");
	}

	// =======================法3老師用的=============================================
	// 點餐的方法,參數用map放,因為要放使用者所點的餐點名稱,跟數量
	public OrderResponse order2(Map<String, Integer> orderMap) {
		// 放最後使用者正確點的餐點名稱,數量,再去算價格
		Map<String, Integer> finalOrderMap = new HashMap<>();
		int totalPrice = 0;

		// 從map裡一筆一筆的撈出使用者點的餐點跟數量,去對照點的名稱跟數量對不對
		for (Entry<String, Integer> orderItem : orderMap.entrySet()) {
			// 如果使用者點的數量為負數或零
			if (orderItem.getValue() <= 0) {
				continue;
			}
			// 從id(餐點名稱)去資料庫撈資料,不管有沒有撈到都會放進op
			Optional<Menu> op = menuDao.findById(orderItem.getKey());
			// 排除使用者亂點的餐點
			if (!op.isPresent()) {
				// 將亂點的餐,回上步
				continue;
			}
			// 從資料庫抓出價格
			int price = op.get().getPrice();
			// value數量要從map抓
			totalPrice += price * orderItem.getValue();
		}
		totalPrice = totalPrice > 500 ? (int) (totalPrice * 0.9) : totalPrice;
		return new OrderResponse(finalOrderMap, totalPrice, "點餐成功");
	}

	// =================================================================================

	@Override
	public OrderResponse getAllMenus() {

		return new OrderResponse(menuDao.findAll(), "取得所有餐點成功");
	}

	@Override
	public GetMenuResponse getMenuByName(String name) {
		if (!StringUtils.hasText(name)) {// name == null || name.isEmpty()
			return new GetMenuResponse("餐點名稱不能為空白");
		}
		Menu menu = menuDao.findByName(name);
		if (menu == null) {
			return new GetMenuResponse("餐點名稱錯誤");
		}
		return new GetMenuResponse(menu, "成功找到餐點");
	}

//======================================================================
	@Override // (List<Menu> menuList)一次輸入多筆菜單
	public GetMenuResponse updateMenuPrice(List<Menu> menuList) {
		// <菜單名稱,價格>把menuList放進menuMap
		// 只能修改舊菜單,不能新增新菜單
		List<Menu> menu = menuDao.findAll();
		List<Menu> newMenu = new ArrayList<>();

		if (CollectionUtils.isEmpty(menuList)) {// 若這個集合長度是空的,輸入的整筆皆為空

			return new GetMenuResponse("新增的餐點名稱不得為空白");

		}
		for (Menu item : menuList) {// foreach可以把list裡的資料一筆一筆拿出來
			if (!StringUtils.hasText(item.getName())) {// 第二筆以上的資料不能為空白或沒有字串

				return new GetMenuResponse("新增的餐點名稱不得為空白");
			}

			if (item.getPrice() <= 0) {

				return new GetMenuResponse("新增的餐點價格不得小於等於零元");
			}
			for (Menu itemDao : menu) {
				if (itemDao.getName().equals(item.getName())) {
					itemDao.setPrice(item.getPrice());
					newMenu.add(itemDao);

				}
			}
		}

		menu = menuDao.saveAll(newMenu);
		if (newMenu.size() != menuList.size()) {// 若原本長度不等於新的長度,代表有失敗的餐點名稱

			return new GetMenuResponse("失敗 : " + (menuList.size() - newMenu.size()) + " 成功 : " + newMenu.size(), menu);
		}

		return new GetMenuResponse("", menu);
	}

	// ================法2(較不推)=================================================
//	@Override // (List<Menu> menuList)一次輸入多筆菜單
//	public GetMenuResponse updateMenuPrice(List<Menu> menuList) {
//		List<Menu> updateMenu = new ArrayList<>();
//		for (Menu menu : menuList) {
//			boolean bool = menuDao.existsById(menu.getName());
//			if (menuDao.existsById(menu.getName())) {
//				updateMenu.add(menu);
//			}
//		}
//		if (updateMenu.size() == 0) {
//			return new GetMenuResponse("查無菜單");
//		}
//
//		return new GetMenuResponse(menuDao.saveAll(updateMenu), "successful");
//
//	}
//===================0417作業============================================

//	@Override
//	public GetMenuResponse getMenuByName(String name) {//OrderRequest的name
//		//防呆
//		if (!StringUtils.hasText(name)) {// name == null || name.isEmpty()
//			return new GetMenuResponse("餐點名稱不能為空白");
//		}
//		
//		
//        //使用者輸入的餐點名稱name去和資料庫比較,如果有找到,讓他等於該資料的所有detail
//		Optional<Menu> op = menuDao.findById(name);// 從資料庫找到餐點名稱,去等於整個Menu
//		if (!op.isPresent()) {// 沒有找到值,代表名稱錯誤
//			return new GetMenuResponse("餐點名稱錯誤");
//		} // 取出op(Menu)
//		Menu m = op.get();
//		return new GetMenuResponse(m, "成功找到餐點");

//	}
//====================================================================

}
