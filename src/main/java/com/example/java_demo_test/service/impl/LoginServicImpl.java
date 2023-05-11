package com.example.java_demo_test.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.responsitory.LoginDao;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

@Service
public class LoginServicImpl implements LoginService {

	/*
	 * ���U(�إ߱b��) �b������ 3~8 �X�A���঳����ť� �K�X���� 8~12 �X�A�ܤ֭n���@�ӯS��Ÿ������঳�ťաBTab�B�w��B����B�������Ÿ�
	 * 
	 * ���b:�b���K�X�W�r���onull�Ϊ�;�b���O�_�s�b�L StringUtils.hasText:�r�ꬰnull�Ϊ�
	 * �b���O�_�s�b�L:�q��Ʈw�����,findById:�b��Ʈw�̧�Ӧr��->�নOptional���A->isPresent:�Y�s�b���Ʈw,���U����
	 * 
	 * ���U���\: account�s�i��Ʈw,save(Optional.get())
	 * pwd&name:new�XLogin,�A.setpwd&name�i�h*set�O����?�i�H�����s�iLogin!?�p�G�Ϋغc��k����n��??����
	 */
//	@Autowired
	private String patternAcc = "\\w{3,8}";
	
//	@Autowired ,�ݩʤ���autowired �����~�n
	private String patternPwd = "[[\\w]&&[^\\s]]{8,12}";
	
//	@Autowired
//	private String patternPwd2 = "^(?=.*[\\p{Punct}])[^\\s]{8,12}$";
//	
//	@Autowired
//	private String patternPwd3 = "^(?=.*[\\s^\\w])[\\S]{8,12}$";
	/*
	 * ?= �ΰݸ��h�j�M��?����Y�d��
	 * .* 1~�h��
	 * ^()$ �q^�}�l~��$����(�npattern����)
	 * [] ���w�d��
	 * \p ���(��ܤ�諸�r����ƬY�دS��
	 * Punct ���I�Ÿ� 
	 * 
	 * 
	 */
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public LoginResponse register(LoginRequest request) {

		// ���b:null�Ϊ�
		if (!StringUtils.hasText(request.getAccount()) || !StringUtils.hasText(request.getPassword())
				|| !StringUtils.hasText(request.getName())) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		} // ���b:�b�� -- �b������ 3~8 �X�A���঳����ť�
		
		if (!request.getAccount().matches(patternAcc)) {
			return new LoginResponse("�b�����ŦX�W�w!");
		} // ���b:�K�X -- �K�X���� 8~12 �X�A�ܤ֭n���@�ӯS��Ÿ��A�����঳�ťաBTab�B�w��B����B�������Ÿ�
		
		boolean pattern = request.getPassword().contains("!") || request.getPassword().contains("@")
				|| request.getPassword().contains("#") || request.getPassword().contains("$")
				|| request.getPassword().contains("%") || request.getPassword().contains("^")
				|| request.getPassword().contains("&") || request.getPassword().contains("*")
				|| request.getPassword().contains("-") || request.getPassword().contains("+")
				|| request.getPassword().contains("=") || request.getPassword().contains("~");

		if (!request.getPassword().matches(patternPwd) && pattern == false) {
			return new LoginResponse(RtnCode.NOT_FOUND.getMessage());//"�K�X���ŦX�W�w!"
		}
		boolean bool = loginDao.existsById(request.getAccount());
		if(!bool) {
			return new LoginResponse(RtnCode.ALREADY_EXISTS.getMessage());
		}
//		// ���b:�b���O�_�s�b
//		Optional<Login> op = loginDao.findById(request.getAccount());
//		if (op.isPresent()) {
//			return new LoginResponse("�b���w�s�b!");
//		}
		// password��name�s��Ʈw
		Login Login = new Login(request.getAccount(), request.getPassword(), request.getName(), request.getAge(),
				request.getCity(), LocalDateTime.now());
		// �b���s�J��Ʈw
		loginDao.save(Login);
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
	}

//======================================================================================

	/*
	 * �ͮ�
	 */

	@Override
	public LoginResponse active(String account, String password) {
		// ���b:���o���ũ�null
		if (!StringUtils.hasText(account) || !StringUtils.hasText(password)) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		}
		// �۩w�q��k�X��
		Login login = loginDao.findByAccountAndPassword(account, password);
		login.setActive(true);
		loginDao.save(login);
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
//		// ���b:�b���O�_�w���U
//		Optional<Login> op = loginDao.findById(account);
//		if (!op.isPresent()) {
//			return new LoginResponse("�d�L�b��!");
//		}
//		Login login = op.get();
//		// ���b:�P�_�b���O�_�w�ͮĹL(p.s.�bPostman��true:1,false:0)
//		if (login.isActive() == true) {
//			return new LoginResponse("���b���w�ͮ�!");
//		}
//		// ���b:�K�X�O�_���T
//		if (login.getPassword().equals(password)) {
		// �ͮĦ��\��:�NActive�]���w�ͮ�

		// �s��Ʈw

//		}
//		return new LoginResponse("�K�X���~!");
	}

//===================================================================================	
	/*
	 * �n�J�b���B�K�X�B�m�W ���b:�n���T�w�ӱb���w�ͮĤF,�Ӥ��u�O���U�Ӥw?�n�ݿE���P�_
	 */
	@Override
	public LoginResponse login(String account, String password) {
		// ���b:���o���ũ�null
		if (!StringUtils.hasText(account) || !StringUtils.hasText(password)) {
			return new LoginResponse("�n�J��T���~");
		}
		// �۩w�q��k:���b���K�X�w�ͮ�
		Login login = loginDao.findByAccountAndPasswordAndIsActive(account, password, true);
		// optional�~��isPresent&Empty
		if (login == null) {
			return new LoginResponse("�n�J��T���o����");
		}
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
	}
//		// ���b:�b���O�_�w�ͮ�
//		Optional<Login> op = loginDao.findById(account);
//		if (!op.isPresent()) {
//			return new LoginResponse("�d�L�b��!");
//		}
//		// ���b:�K�X�O�_���T
//		if (op.get().getPassword().equals(password) && op.get().isActive() == true) {
//
//			return new LoginResponse("�n�J���\!");
//		}
//		if (op.get().isActive() == false) {
//			return new LoginResponse("�b�����ͮ�!");
//		}

	// ===================================================================================
	/*
	 * �~��b�Y�������ϥΪ̷j�M,Response ���]�t�K�X,�t�ƧǡA�Ӧ~�� ���b:
	 */
	@Override
	public LoginResponse citySearchUser(String city) {
		// ���b:���o���ũ�null
		if (!StringUtils.hasText(city)) {
			return new LoginResponse(RtnCode.CANNOT_ALLEMPTY.getMessage());
		}
		// �q��Ʈw�d�߸��//Asc�O�w�]�i�H���μg
		List<Login> find = loginDao.findByCityOrderByAge(city);
		// ��Ʈw�L�����
		if (find.isEmpty()) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		}
		List<Login> data = new ArrayList<>();
		// ���Ӹ��,�N�Ҧ���ƪ�"�C���K�X"����
		for (Login item : find) {
			// �]�w�K�X��null,�æbEntity��@JsonInclude
			item.setPassword(null);
			data.add(item);
		}
		return new LoginResponse(data, RtnCode.SUCCESSFUL.getMessage());
	}

}
