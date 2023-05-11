 package com.example.java_demo_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.responsitory.PersonInfoDao;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.OrderResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class OrderTest {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PersonInfoDao personInfoDao;

	@Test
	public void AddmenusTest() {
		Menu menuSet1 = new Menu("beef", 100);
		Menu menuSet2 = new Menu("chicken", 200);
		List<Menu> list = new ArrayList<>(Arrays.asList(menuSet1, menuSet2));// 新增餐點
		OrderResponse response = orderService.addMenus(list);// 取出該方法箱子
		List<Menu> responseList = response.getMenuList();// 取出各自的值
		Assert.isTrue(responseList != null, "新增餐點錯誤");// Assert檢查方法有沒有漏掉的防呆

	}

	@Test
	public void a() {
		Object a =new String("1") ;
		Object b =new String("1");
		System.out.println(a.equals(b));
	}
	@Test
	public void Test() {
		List<PersonInfo> mu = personInfoDao.findFirst3ByAgeGreaterThanEqualAndAgeLessThanEqualOrderByAgeDesc(null,null);
		System.out.println(mu.size());
		for( PersonInfo item:mu) {
			System.out.println(item.getName());
		}
		
	}
}