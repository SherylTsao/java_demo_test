package com.example.java_demo_test.service.ifs;



import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {
	
	public LoginResponse register(LoginRequest request);
	// ���U

	public LoginResponse active(String account, String password);
    //�ͮıb��
	
	public LoginResponse login(String account, String password);
    //�n�J
	
	public LoginResponse citySearchUser(String city);
    //�~��b�Y�������ϥΪ̷j�M,Response ���]�t�K�X,�t�ƧǡA�Ӧ~��

}
