package com.example.java_demo_test.service.ifs;



import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {
	
	public LoginResponse register(LoginRequest request);
	// 註冊

	public LoginResponse active(String account, String password);
    //生效帳號
	
	public LoginResponse login(String account, String password);
    //登入
	
	public LoginResponse citySearchUser(String city);
    //居住在某城市的使用者搜尋,Response 不包含密碼,含排序，照年齡

}
