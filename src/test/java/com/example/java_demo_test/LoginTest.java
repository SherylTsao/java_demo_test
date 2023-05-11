package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.responsitory.LoginDao;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class LoginTest {

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private LoginService loginService;
	
	
	
	@Test
	public void registerTest() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.getAccount();
		
	}
	
	@Test
	public void regiTest() {
		LoginRequest request = new LoginRequest("1","2","3","4",2);
		LoginResponse res = loginService.register(request);
		System.out.println(res.getMessage());
		
	}
}
