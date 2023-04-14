package com.example.java_demo_test;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.responsitory.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class BankTest {

	@Autowired
	private BankDao bankDao;

	@Autowired
	private BankService bankService;// interface要實作所以用Autowired

	@Test
	public void addBanInfo() {
		Bank bank = new Bank("AA99", "AA@", 2000);
		// save:新增資料到 ( bank )資料表
		bankDao.save(bank);
	}

	@Test
	public void addBanInfoTest() {
		Bank bank = new Bank("AA999", "AA123456@", 2000);
		// save:新增資料到 ( bank )資料表
		bankDao.save(bank);
	}

//	@Test
//	public void addInfo() {
//		Bank bank = new Bank("123", "12345678", -12);
//		bankService.addInfo(bank);
//		String pattern = "(\\w){3,8}[^_]";// 長度3-8
//
//		String str1 = "";
//		String str2 = " ";
//		String str3 = "AA";
//		String str4 = "AA ";
//		String str5 = "AA@";
//		String str6 = "AAAA";
//
//		System.out.println(str1.matches(pattern));
//		System.out.println(str2.matches(pattern));
//		System.out.println(str3.matches(pattern));
//		System.out.println(str4.matches(pattern));
//		System.out.println(str5.matches(pattern));
//		System.out.println(str6.matches(pattern));

//	}

//	@Test
//	public void getAmountByIdTest() {
//		Bank bank = bankService.getAccountById("ABC");
//		System.out.println("帳號:" + bank.getAccount() + "餘額:" + bank.getAmount());
//	}
	@Test
	public void depositTest() {
		// 將測試的假資料存到資料庫
		Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 2000));
		// 存款
		Bank newBank = new Bank("AA999", "AA123456@", 3000);
		// 存金額入資料庫
		BankResponse resBank = bankService.deposit(newBank);
		Bank bank = resBank.getBank();
				// 確認金額是否存入
		Assert.isTrue(bank.getAmount() == (oldBank.getAmount() + newBank.getAmount()), "存款錯誤");
		// 刪除測試資料
		// bankDao.delete(resBank);
	}

	@Test
	public void withdrawTest() {
		Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 2000));
		Bank newBank = new Bank("AA999", "AA123456@", 3000);
		// 提款
//		Bank withdrawAmount = new Bank("AA999", "AA123456@");
		// 存金額入資料庫
		BankResponse res = bankService.Withdraw(newBank);
//		Bank teBank = bankService.Withdraw(withdrawAmount, 100);
		// 提款之前的金額
		Bank response = res.getBank();
		// 確認金額是否存入
		Assert.isTrue(response.getAmount() == (oldBank.getAmount() - newBank.getAmount()), "提款錯誤");
		// 刪除測試資料
		// bankDao.delete(response);
	}
}
