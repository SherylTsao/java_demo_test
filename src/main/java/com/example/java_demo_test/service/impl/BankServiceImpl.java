package com.example.java_demo_test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.responsitory.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@Service
public class BankServiceImpl implements BankService {	

	@Autowired
	private BankDao bankDao;

//	@Override
//	public void addInfo(Bank bank) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public Bank getAccountById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public BankResponse deposit(Bank bank) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public BankResponse Withdraw(Bank bank) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void addInfo(Bank bank) {

		boolean boo = checkAccount(bank);
		if (boo == false) {
			return;
		}
		checkPattern(bank);

	}

	private boolean checkAccount(Bank bank) {
		if (bank == null || bank.getAccount() == null || bank.getPwd() == null || bank.getAmount() < 0) {
			System.out.println("資訊錯誤");
			return false;// 跳出(停止)
		}
		return true;// 跳出(停止)

	}

	private void checkPattern(Bank bank) {// 要加&&而且沒有底線
		String pattern = "[\\w&&[^_]]{3,8}";// \\w:數字.英文大小寫.底線
		String pattern1 = "[\\w!@#$%^]{8,16}";// 密碼不包含特殊字元
		if (!bank.getAccount().matches(pattern) || !bank.getPwd().matches(pattern1)) {// ( ){3,8}:()括號裡的出現3~8次
			System.out.println("資料錯誤");// [^_]:排除底線

		} else {
			System.out.println("儲存成功");
			bankDao.save(bank);// save:新增資料到 ( bank )資料表
		}
	}

	@Override
	public Bank getAccountById(String id) {
		// hasText:有null或有長度(含空白)
		if (!StringUtils.hasText(id)) {
			// 傳回空的Bank
			return new Bank();
		}
		// 從資料庫找此id,Optional:null
		Optional<Bank> op = bankDao.findById(id);
		// If a value is present, returns the value, otherwise returns other.
		return op.orElse(new Bank());

		// isPressent:如果有值,true
//         if(!op.isPresent()) {
//        	 return new Bank();
//         }
//        //getop值
//        op.get();

	}

	@Override
	public BankResponse deposit(Bank bank) {
		// 防呆
		if (bank == null || // 若存款
				!StringUtils.hasText(bank.getAccount()) || // 若帳號
				!StringUtils.hasText(bank.getPwd()) || // 若密碼
				bank.getAmount() <= 0) {// 餘額
			return new BankResponse(new Bank(), "帳號或密碼錯誤!!");
		}
		// 檢查bank的id帳號有沒有存在

		// 取出id account
//		Optional<Bank> op = bankDao.findById(bank.getAccount());
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		//取出帳號
//		Bank resBank = op.get();
//		if(!resBank.getPwd().equals(bank.getAccount())) {
//			return new Bank();
//		}
		// 取出自定義的方法
		Bank resBank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if (resBank == null) {
			return new BankResponse(new Bank(), "帳號或密碼錯誤!!");
		}
		// 原本帳戶的餘額
		int oldAmount = resBank.getAmount();
		// 存進去的錢
		int depositAmount = bank.getAmount();
		// 餘額+存的錢
		int newAmount = oldAmount + depositAmount;
		// 設定存款後的金額
		resBank.setAmount(newAmount);
		// 存進去資料庫
		bankDao.save(resBank);
		return new BankResponse(new Bank(), "存款成功!!");
	}

	@Override
	public BankResponse Withdraw(Bank bank) {
		// 防呆
		if (bank == null || // 若提款
				!StringUtils.hasText(bank.getAccount()) || // 若帳號
				!StringUtils.hasText(bank.getPwd()) || // 若密碼
				bank.getAmount() < 0 // 餘額
		) {
			System.out.println("提款錯誤");
			return new BankResponse(new Bank(), "帳號或密碼錯誤!!");
		}
		// 取出該人的帳號密碼
		Bank tebank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if (tebank == null) {
			return new BankResponse(new Bank(), "資料不存在!!");
		}
		if (tebank.getAmount() < bank.getAmount()) {// 不可以提出超過存款金額
			System.out.println("提款錯誤");
			return new BankResponse(new Bank(), "餘額不足!!");
		}
		System.out.println(tebank.getAmount());
		// 原本帳戶的餘額
		int oldAmount = tebank.getAmount();
		// 提出去的錢
		int withdrawAmount = bank.getAmount();
		// 餘額-提的錢
		int newAmount = oldAmount - withdrawAmount;
		// 設定提款後的金額
		tebank.setAmount(newAmount);
		// 存進去資料庫
		// bankDao.save(tebank);
		// System.out.println(tebank.getAmount());
		return new BankResponse(bankDao.save(tebank), "提款成功");

	}

}
