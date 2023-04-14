package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.vo.BankResponse;

public interface BankService {
 
	public void addInfo(Bank bank);//定義方法要public,因為介面要被別人實作
	
	
	public Bank getAccountById(String id);
	
	public BankResponse deposit(Bank bank);//方法"存款"需要帳號,其三個屬性都在bank裡
	
	public BankResponse Withdraw(Bank bank);//方法"提款"
	
	
}
