package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Entity:這裡的Bank交給資料庫連結
@Entity // 1.import javax.persistence.Entity:資料持久化
@Table(name = "bank") // 2.指定連結"bank"名稱的資料庫DB(DataBase)
public class Bank {

	@Id // 資料表的PK有打勾
	@Column(name = "account") // 資料表的"account"
	private String account;// String= MySQI的Verchar(資料型態)

	@Column(name = "pwd") // 資料表的"password"
	private String pwd;// password

	@Column(name = "amount") // // 資料表的"amount"
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
