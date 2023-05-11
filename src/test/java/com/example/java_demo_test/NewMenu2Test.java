package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.responsitory.NewMenu2Dao;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class NewMenu2Test {
	
	@Autowired
	private NewMenu2Dao newMenu2Dao;
	
	@Test
	public void addMenuTest() {
		NewMenu2 newMenu2 = new NewMenu2("123","456",120);
		newMenu2Dao.save(newMenu2);
		
	}
}
