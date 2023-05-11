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

	@Autowired // ���w�U�����O
	private LoginService loginService;

//	@PostMapping(value = "register") // ���U:�s���~��
//	public LoginResponse register(@RequestBody LoginRequest request) {
//		// �q�`��Service��k�@�˦W��,����copy
//		return loginService.register(request);
//		// �s������
//	}
//
//	@PostMapping(value = "active") // �ͮ�:�s���~��
//	public LoginResponse active(@RequestBody LoginRequest request) {
//		// �q�`��Service��k�@�˦W��,����copy
//		return loginService.active(request.getAccount(), request.getPassword());
//		// �s������
//	}
//    //@ApiIgnore //swagger �����ä�k
//	@Hidden  //open Api �����ä�k
//	@PostMapping(value = "login") // �n�J:�s���~��
//	public LoginResponse login(@RequestBody LoginRequest request) {
//		// �q�`��Service��k�@�˦W��,����copy
//		return loginService.login(request.getAccount(), request.getPassword());
//		// �s������
//	}
//	
//	@PostMapping(value = "citySearchUser") // �~��b�Y�������ϥΪ̷j�M,Response ���]�t�K�X,�t�ƧǡA�Ӧ~��:�s���~��
//	public LoginResponse citySearchUser(@RequestBody LoginRequest request) {
//		// �q�`��Service��k�@�˦W��,����copy
//		return loginService.citySearchUser(request.getCity());
//		// �s������
//	}
}
