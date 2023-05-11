package com.example.java_demo_test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 1.import javax.persistence.Entity:資料持久化
@Table(name = "register")
public class Register {

	@Id // 資料表的PK有打勾
	@Column(name = "account")
	private String account;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "reg_time")
	private LocalDateTime regTime = LocalDateTime.now();

	@Column(name = "active")
	private boolean active;

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Register(String account, String pwd) {
		super();
		this.account = account;
		this.pwd = pwd;
	}

	public Register(String account, String pwd, LocalDateTime regTime) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.regTime = regTime;
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

	public LocalDateTime getRegTime() {
		return regTime;
	}

	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
