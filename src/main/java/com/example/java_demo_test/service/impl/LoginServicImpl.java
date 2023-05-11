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
	 * 註冊(建立帳號) 帳號長度 3~8 碼，不能有任何空白 密碼長度 8~12 碼，至少要有一個特殊符號但不能有空白、Tab、定位、換行、換頁等符號
	 * 
	 * 防呆:帳號密碼名字不得null或空;帳號是否存在過 StringUtils.hasText:字串為null或空
	 * 帳號是否存在過:從資料庫撈資料,findById:在資料庫裡找該字串->轉成Optional型態->isPresent:若存在於資料庫,註冊失敗
	 * 
	 * 註冊成功: account存進資料庫,save(Optional.get())
	 * pwd&name:new出Login,再.setpwd&name進去*set是什麼?可以直接存進Login!?如果用建構方法比較好嗎??怎麼用
	 */
//	@Autowired
	private String patternAcc = "\\w{3,8}";
	
//	@Autowired ,屬性不能autowired 介面才要
	private String patternPwd = "[[\\w]&&[^\\s]]{8,12}";
	
//	@Autowired
//	private String patternPwd2 = "^(?=.*[\\p{Punct}])[^\\s]{8,12}$";
//	
//	@Autowired
//	private String patternPwd3 = "^(?=.*[\\s^\\w])[\\S]{8,12}$";
	/*
	 * ?= 用問號去搜尋讓?等於某範圍
	 * .* 1~多個
	 * ^()$ 從^開始~到$結束(要pattern比對時)
	 * [] 給定範圍
	 * \p 具備(表示比對的字元具備某種特性
	 * Punct 標點符號 
	 * 
	 * 
	 */
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public LoginResponse register(LoginRequest request) {

		// 防呆:null或空
		if (!StringUtils.hasText(request.getAccount()) || !StringUtils.hasText(request.getPassword())
				|| !StringUtils.hasText(request.getName())) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		} // 防呆:帳號 -- 帳號長度 3~8 碼，不能有任何空白
		
		if (!request.getAccount().matches(patternAcc)) {
			return new LoginResponse("帳號不符合規定!");
		} // 防呆:密碼 -- 密碼長度 8~12 碼，至少要有一個特殊符號，但不能有空白、Tab、定位、換行、換頁等符號
		
		boolean pattern = request.getPassword().contains("!") || request.getPassword().contains("@")
				|| request.getPassword().contains("#") || request.getPassword().contains("$")
				|| request.getPassword().contains("%") || request.getPassword().contains("^")
				|| request.getPassword().contains("&") || request.getPassword().contains("*")
				|| request.getPassword().contains("-") || request.getPassword().contains("+")
				|| request.getPassword().contains("=") || request.getPassword().contains("~");

		if (!request.getPassword().matches(patternPwd) && pattern == false) {
			return new LoginResponse(RtnCode.NOT_FOUND.getMessage());//"密碼不符合規定!"
		}
		boolean bool = loginDao.existsById(request.getAccount());
		if(!bool) {
			return new LoginResponse(RtnCode.ALREADY_EXISTS.getMessage());
		}
//		// 防呆:帳號是否存在
//		Optional<Login> op = loginDao.findById(request.getAccount());
//		if (op.isPresent()) {
//			return new LoginResponse("帳號已存在!");
//		}
		// password跟name存資料庫
		Login Login = new Login(request.getAccount(), request.getPassword(), request.getName(), request.getAge(),
				request.getCity(), LocalDateTime.now());
		// 帳號存入資料庫
		loginDao.save(Login);
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
	}

//======================================================================================

	/*
	 * 生效
	 */

	@Override
	public LoginResponse active(String account, String password) {
		// 防呆:不得為空或null
		if (!StringUtils.hasText(account) || !StringUtils.hasText(password)) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		}
		// 自定義方法出來
		Login login = loginDao.findByAccountAndPassword(account, password);
		login.setActive(true);
		loginDao.save(login);
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
//		// 防呆:帳號是否已註冊
//		Optional<Login> op = loginDao.findById(account);
//		if (!op.isPresent()) {
//			return new LoginResponse("查無帳號!");
//		}
//		Login login = op.get();
//		// 防呆:判斷帳號是否已生效過(p.s.在Postman的true:1,false:0)
//		if (login.isActive() == true) {
//			return new LoginResponse("此帳號已生效!");
//		}
//		// 防呆:密碼是否正確
//		if (login.getPassword().equals(password)) {
		// 生效成功時:將Active設為已生效

		// 存資料庫

//		}
//		return new LoginResponse("密碼錯誤!");
	}

//===================================================================================	
	/*
	 * 登入帳號、密碼、姓名 防呆:要怎麼確定該帳號已生效了,而不只是註冊而已?要看激活與否
	 */
	@Override
	public LoginResponse login(String account, String password) {
		// 防呆:不得為空或null
		if (!StringUtils.hasText(account) || !StringUtils.hasText(password)) {
			return new LoginResponse("登入資訊錯誤");
		}
		// 自定義方法:找到帳號密碼已生效
		Login login = loginDao.findByAccountAndPasswordAndIsActive(account, password, true);
		// optional才有isPresent&Empty
		if (login == null) {
			return new LoginResponse("登入資訊不得為空");
		}
		return new LoginResponse(RtnCode.SUCCESSFUL.getMessage());
	}
//		// 防呆:帳號是否已生效
//		Optional<Login> op = loginDao.findById(account);
//		if (!op.isPresent()) {
//			return new LoginResponse("查無帳號!");
//		}
//		// 防呆:密碼是否正確
//		if (op.get().getPassword().equals(password) && op.get().isActive() == true) {
//
//			return new LoginResponse("登入成功!");
//		}
//		if (op.get().isActive() == false) {
//			return new LoginResponse("帳號未生效!");
//		}

	// ===================================================================================
	/*
	 * 居住在某城市的使用者搜尋,Response 不包含密碼,含排序，照年齡 防呆:
	 */
	@Override
	public LoginResponse citySearchUser(String city) {
		// 防呆:不得為空或null
		if (!StringUtils.hasText(city)) {
			return new LoginResponse(RtnCode.CANNOT_ALLEMPTY.getMessage());
		}
		// 從資料庫查詢資料//Asc是預設可以不用寫
		List<Login> find = loginDao.findByCityOrderByAge(city);
		// 資料庫無此資料
		if (find.isEmpty()) {
			return new LoginResponse(RtnCode.DATA_ERROR.getMessage());
		}
		List<Login> data = new ArrayList<>();
		// 找到該資料,將所有資料的"每筆密碼"隱藏
		for (Login item : find) {
			// 設定密碼為null,並在Entity那@JsonInclude
			item.setPassword(null);
			data.add(item);
		}
		return new LoginResponse(data, RtnCode.SUCCESSFUL.getMessage());
	}

}
