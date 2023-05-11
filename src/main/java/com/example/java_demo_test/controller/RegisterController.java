package com.example.java_demo_test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.service.ifs.RegisterService;

import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@RestController
public class RegisterController {

	@Autowired // 指定託管類別
	private RegisterService registerService;

	@PostMapping(value = "register") // 連結外部
	public RegisterResponse register(@RequestBody RegisterRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return registerService.register(request.getAccount(), request.getPwd());// 連結內部
	}

	@PostMapping(value = "active") // 連結外部
	public RegisterResponse active(@RequestBody RegisterRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return registerService.active(request.getAccount(), request.getPwd());// 連結內部
	}

	@PostMapping(value = "login") // 連結外部 //session暫時儲存帳號密碼
	public RegisterResponse login(@RequestBody RegisterRequest request, HttpSession session) {
		// 通常跟Service方法一樣名稱,直接copy
		RegisterResponse res = registerService.login(request.getAccount(), request.getPwd());// 連結內部
		if (res.getMessage().equalsIgnoreCase("successful!!!")) {
			// 隨機產生驗證碼
			double ramdom = Math.random() * 1000;
			// 將小數點轉成整數
			int verifyCode = (int) Math.round(ramdom);
			// 暫存驗證碼,帳密,至session裡面
			session.setAttribute("verifyCode", verifyCode);
			session.setAttribute("Account", request.getAccount());
			session.setAttribute("Password", request.getPwd());
			//設定驗證碼可用的"秒數"
			session.setMaxInactiveInterval(60);
			//設定id,驗證碼準備回資料庫
			res.setSessionId(session.getId());
			res.setVerifyCode(verifyCode);

		}
		return res;
	}

	@PostMapping(value = "getRegTime") // 連結外部
	public RegisterResponse getRegTime(@RequestBody RegisterRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return registerService.getRegTime(request.getAccount(), request.getPwd());// 連結內部

	}
	@PostMapping(value = "getRegTime") // 連結外部
	public RegisterResponse getRegTime1(@RequestBody RegisterRequest request,HttpSession session) {
		//取出session帳密
		String account = (String)session.getAttribute("account");
		String pwd = (String)session.getAttribute("pwd");
		//帳密不得為空,當沒有login過,或是時效過期,則為空
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Please login!!");
		}
		//驗證碼不得為空,當沒有login過,或是時效過期,則為空
		Integer verifyCode =(Integer)session.getAttribute("verifyCode");
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("Verify code incorrect!!");
		}
		return registerService.getRegTime(account,pwd);// 連結內部

	}
	@PostMapping(value = "getRegTime2") // 連結外部
	public RegisterResponse getRegTime2(@RequestBody RegisterRequest request,HttpSession session) {
		//設定sesion參數
		String account = (String)session.getAttribute("account");
		String pwd = (String)session.getAttribute("pwd");
		Integer verifyCode =(Integer)session.getAttribute("verifyCode");
		// 通常跟Service方法一樣名稱,直接copy
		return registerService.getRegTime2(request,account, pwd,verifyCode);// 連結內部

	}
	
}
