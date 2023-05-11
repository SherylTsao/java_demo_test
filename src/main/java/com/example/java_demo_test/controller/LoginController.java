package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.LoginService;

import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

import io.swagger.v3.oas.annotations.Hidden;

//import springfox.documentation.annotations.ApiIgnore;

@RestController
public class LoginController {

	@Autowired // 指定託管類別
	private LoginService loginService;

//	@PostMapping(value = "register") // 註冊:連結外部
//	public LoginResponse register(@RequestBody LoginRequest request) {
//		// 通常跟Service方法一樣名稱,直接copy
//		return loginService.register(request);
//		// 連結內部
//	}
//
//	@PostMapping(value = "active") // 生效:連結外部
//	public LoginResponse active(@RequestBody LoginRequest request) {
//		// 通常跟Service方法一樣名稱,直接copy
//		return loginService.active(request.getAccount(), request.getPassword());
//		// 連結內部
//	}
//    //@ApiIgnore //swagger 的隱藏方法
//	@Hidden  //open Api 的隱藏方法
//	@PostMapping(value = "login") // 登入:連結外部
//	public LoginResponse login(@RequestBody LoginRequest request) {
//		// 通常跟Service方法一樣名稱,直接copy
//		return loginService.login(request.getAccount(), request.getPassword());
//		// 連結內部
//	}
//	
//	@PostMapping(value = "citySearchUser") // 居住在某城市的使用者搜尋,Response 不包含密碼,含排序，照年齡:連結外部
//	public LoginResponse citySearchUser(@RequestBody LoginRequest request) {
//		// 通常跟Service方法一樣名稱,直接copy
//		return loginService.citySearchUser(request.getCity());
//		// 連結內部
//	}
}
