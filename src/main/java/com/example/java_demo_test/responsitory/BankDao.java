package com.example.java_demo_test.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Bank;

@Repository
public interface BankDao extends JpaRepository<Bank, String> {
	// jpa網址是別人寫好的 //<class名稱,屬性資料型態>

	public Bank findByAccountAndPwd(String account,String pwd);
	
	
}