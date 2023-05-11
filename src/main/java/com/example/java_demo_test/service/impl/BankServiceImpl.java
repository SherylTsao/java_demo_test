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
			System.out.println("��T���~");
			return false;// ���X(����)
		}
		return true;// ���X(����)

	}

	private void checkPattern(Bank bank) {// �n�[&&�ӥB�S�����u
		String pattern = "[\\w&&[^_]]{3,8}";// \\w:�Ʀr.�^��j�p�g.���u
		String pattern1 = "[\\w!@#$%^]{8,16}";// �K�X���]�t�S��r��
		if (!bank.getAccount().matches(pattern) || !bank.getPwd().matches(pattern1)) {// ( ){3,8}:()�A���̪��X�{3~8��
			System.out.println("��ƿ��~");// [^_]:�ư����u

		} else {
			System.out.println("�x�s���\");
			bankDao.save(bank);// save:�s�W��ƨ� ( bank )��ƪ�
		}
	}

	@Override
	public Bank getAccountById(String id) {
		// hasText:��null�Φ�����(�t�ť�)
		if (!StringUtils.hasText(id)) {
			// �Ǧ^�Ū�Bank
			return new Bank();
		}
		// �q��Ʈw�䦹id,Optional:null
		Optional<Bank> op = bankDao.findById(id);
		// If a value is present, returns the value, otherwise returns other.
		return op.orElse(new Bank());

		// isPressent:�p�G����,true
//         if(!op.isPresent()) {
//        	 return new Bank();
//         }
//        //getop��
//        op.get();

	}

	@Override
	public BankResponse deposit(Bank bank) {
		// ���b
		if (bank == null || // �Y�s��
				!StringUtils.hasText(bank.getAccount()) || // �Y�b��
				!StringUtils.hasText(bank.getPwd()) || // �Y�K�X
				bank.getAmount() <= 0) {// �l�B
			return new BankResponse(new Bank(), "�b���αK�X���~!!");
		}
		// �ˬdbank��id�b�����S���s�b

		// ���Xid account
//		Optional<Bank> op = bankDao.findById(bank.getAccount());
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		//���X�b��
//		Bank resBank = op.get();
//		if(!resBank.getPwd().equals(bank.getAccount())) {
//			return new Bank();
//		}
		// ���X�۩w�q����k
		Bank resBank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if (resBank == null) {
			return new BankResponse(new Bank(), "�b���αK�X���~!!");
		}
		// �쥻�b�᪺�l�B
		int oldAmount = resBank.getAmount();
		// �s�i�h����
		int depositAmount = bank.getAmount();
		// �l�B+�s����
		int newAmount = oldAmount + depositAmount;
		// �]�w�s�ګ᪺���B
		resBank.setAmount(newAmount);
		// �s�i�h��Ʈw
		bankDao.save(resBank);
		return new BankResponse(new Bank(), "�s�ڦ��\!!");
	}

	@Override
	public BankResponse Withdraw(Bank bank) {
		// ���b
		if (bank == null || // �Y����
				!StringUtils.hasText(bank.getAccount()) || // �Y�b��
				!StringUtils.hasText(bank.getPwd()) || // �Y�K�X
				bank.getAmount() < 0 // �l�B
		) {
			System.out.println("���ڿ��~");
			return new BankResponse(new Bank(), "�b���αK�X���~!!");
		}
		// ���X�ӤH���b���K�X
		Bank tebank = bankDao.findByAccountAndPwd(bank.getAccount(), bank.getPwd());
		if (tebank == null) {
			return new BankResponse(new Bank(), "��Ƥ��s�b!!");
		}
		if (tebank.getAmount() < bank.getAmount()) {// ���i�H���X�W�L�s�ڪ��B
			System.out.println("���ڿ��~");
			return new BankResponse(new Bank(), "�l�B����!!");
		}
		System.out.println(tebank.getAmount());
		// �쥻�b�᪺�l�B
		int oldAmount = tebank.getAmount();
		// ���X�h����
		int withdrawAmount = bank.getAmount();
		// �l�B-������
		int newAmount = oldAmount - withdrawAmount;
		// �]�w���ګ᪺���B
		tebank.setAmount(newAmount);
		// �s�i�h��Ʈw
		// bankDao.save(tebank);
		// System.out.println(tebank.getAmount());
		return new BankResponse(bankDao.save(tebank), "���ڦ��\");

	}

}
