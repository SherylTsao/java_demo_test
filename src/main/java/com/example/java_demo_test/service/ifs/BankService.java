package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.vo.BankResponse;

public interface BankService {
 
	public void addInfo(Bank bank);//�w�q��k�npublic,�]�������n�Q�O�H��@
	
	
	public Bank getAccountById(String id);
	
	public BankResponse deposit(Bank bank);//��k"�s��"�ݭn�b��,��T���ݩʳ��bbank��
	
	public BankResponse Withdraw(Bank bank);//��k"����"
	
	
}
