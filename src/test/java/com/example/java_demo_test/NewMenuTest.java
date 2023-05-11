package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.NewMenu;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class NewMenuTest {

	@Autowired
	private NewMenu newMenu;
	
	@Test
	public void addDataTest() {
		
	}
}
