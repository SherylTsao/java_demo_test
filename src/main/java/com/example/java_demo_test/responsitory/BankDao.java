package com.example.java_demo_test.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Bank;

@Repository
public interface BankDao extends JpaRepository<Bank, String> {
	// jpa���}�O�O�H�g�n�� //<class�W��,�ݩʸ�ƫ��A>

	public Bank findByAccountAndPwd(String account,String pwd);
	
	
}