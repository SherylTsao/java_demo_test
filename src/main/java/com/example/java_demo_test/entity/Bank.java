package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Entity:�o�̪�Bank�浹��Ʈw�s��
@Entity // 1.import javax.persistence.Entity:��ƫ��[��
@Table(name = "bank") // 2.���w�s��"bank"�W�٪���ƮwDB(DataBase)
public class Bank {

	@Id // ��ƪ�PK������
	@Column(name = "account") // ��ƪ�"account"
	private String account;// String= MySQI��Verchar(��ƫ��A)

	@Column(name = "pwd") // ��ƪ�"password"
	private String pwd;// password

	@Column(name = "amount") // // ��ƪ�"amount"
	private int amount;
	
	

	public Bank() {

	}

	public Bank(String account, String pwd) {
		super();
		this.account = account;
		this.pwd = pwd;
	}

	public Bank(String account, String pwd, int amount) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.amount = amount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
