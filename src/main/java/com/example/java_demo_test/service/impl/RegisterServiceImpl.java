package com.example.java_demo_test.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.responsitory.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterDao registerDao;

	@Override
	public RegisterResponse register(String account, String pwd) {
		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("incorrect");
		}
		
        //�ˬd�b���O�_�s�b
		if (registerDao.existsById(account)) {
			return new RegisterResponse("Not exist!");
		}
		//�Q�Ϋغc��k�N�浧��Ʀs�i��Ʈw
		registerDao.save(new Register(account, pwd));

		return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public RegisterResponse active(String account, String pwd) {

		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse(RtnCode.ALREADY_EXISTS.getMessage());
		}
		Register reg = registerDao.findByAccountAndPwd(account, pwd);
		if (reg == null) {
			return new RegisterResponse(RtnCode.ALREADY_EXISTS.getMessage());
		}
		// �O�_�ͮ�
		if (reg.isActive()) {
			return new RegisterResponse(RtnCode.DATA_ERROR.getMessage());

		}

		return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse(RtnCode.ALREADY_EXISTS.getMessage());
		}
		Register reg = registerDao.findByAccountAndPwd(account, pwd);
		if (reg == null) {
			return new RegisterResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		if (!reg.isActive()) {
			return new RegisterResponse(RtnCode.DATA_ERROR.getMessage());

		}

		return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public RegisterResponse getRegTime(String account, String pwd) {
		// ���b
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse(RtnCode.ALREADY_EXISTS.getMessage());
		}
		Register reg = registerDao.findByAccountAndPwd(account, pwd);

		if (reg == null) {
			return new RegisterResponse(RtnCode.ALREADY_EXISTS.getMessage());
		}
		LocalDateTime res = reg.getRegTime();

		return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage(), res);
	}

	@Override
	public RegisterResponse getRegTime2(RegisterRequest request, String account, String pwd, Integer verifyCode) {
		// �b�K���o����,��S��login�L,�άO�ɮĹL��,�h����
		if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
			return new RegisterResponse("Please login!!");
		}
		if (verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("Verify code incorrect!!");
		}
		Register reg = registerDao.findByAccountAndPwd(account, pwd);

		if (reg == null) {
			return new RegisterResponse(RtnCode.ALREADY_EXISTS.getMessage());
		}
		LocalDateTime res = reg.getRegTime();
		return new RegisterResponse(RtnCode.SUCCESSFUL.getMessage(), res);
	}

}
