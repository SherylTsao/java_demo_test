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

	@Autowired // ���w�U�����O
	private RegisterService registerService;

	@PostMapping(value = "register") // �s���~��
	public RegisterResponse register(@RequestBody RegisterRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return registerService.register(request.getAccount(), request.getPwd());// �s������
	}

	@PostMapping(value = "active") // �s���~��
	public RegisterResponse active(@RequestBody RegisterRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return registerService.active(request.getAccount(), request.getPwd());// �s������
	}

	@PostMapping(value = "login") // �s���~�� //session�Ȯ��x�s�b���K�X
	public RegisterResponse login(@RequestBody RegisterRequest request, HttpSession session) {
		// �q�`��Service��k�@�˦W��,����copy
		RegisterResponse res = registerService.login(request.getAccount(), request.getPwd());// �s������
		if (res.getMessage().equalsIgnoreCase("successful!!!")) {
			// �H���������ҽX
			double ramdom = Math.random() * 1000;
			// �N�p���I�ন���
			int verifyCode = (int) Math.round(ramdom);
			// �Ȧs���ҽX,�b�K,��session�̭�
			session.setAttribute("verifyCode", verifyCode);
			session.setAttribute("Account", request.getAccount());
			session.setAttribute("Password", request.getPwd());
			//�]�w���ҽX�i�Ϊ�"���"
			session.setMaxInactiveInterval(60);
			//�]�wid,���ҽX�ǳƦ^��Ʈw
			res.setSessionId(session.getId());
			res.setVerifyCode(verifyCode);

		}
		return res;
	}

	@PostMapping(value = "getRegTime") // �s���~��
	public RegisterResponse getRegTime(@RequestBody RegisterRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return registerService.getRegTime(request.getAccount(), request.getPwd());// �s������

	}
	@PostMapping(value = "getRegTime") // �s���~��
	public RegisterResponse getRegTime1(@RequestBody RegisterRequest request,HttpSession session) {
		//���Xsession�b�K
		String account = (String)session.getAttribute("account");
		String pwd = (String)session.getAttribute("pwd");
		//�b�K���o����,��S��login�L,�άO�ɮĹL��,�h����
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Please login!!");
		}
		//���ҽX���o����,��S��login�L,�άO�ɮĹL��,�h����
		Integer verifyCode =(Integer)session.getAttribute("verifyCode");
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("Verify code incorrect!!");
		}
		return registerService.getRegTime(account,pwd);// �s������

	}
	@PostMapping(value = "getRegTime2") // �s���~��
	public RegisterResponse getRegTime2(@RequestBody RegisterRequest request,HttpSession session) {
		//�]�wsesion�Ѽ�
		String account = (String)session.getAttribute("account");
		String pwd = (String)session.getAttribute("pwd");
		Integer verifyCode =(Integer)session.getAttribute("verifyCode");
		// �q�`��Service��k�@�˦W��,����copy
		return registerService.getRegTime2(request,account, pwd,verifyCode);// �s������

	}
	
}
