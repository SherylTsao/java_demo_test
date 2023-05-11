package com.example.java_demo_test.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "new_menu")
public class NewMenu {

	/*
	 * IDENTITY:資料自動增長 若有刪除重複的資料,當新增流水號時,不會回頭補該流水號,除非刪除(drop table)整個資料表才會重新排流水號
	 * 若要刪除重複的資料,要輸入該指定的流水號
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 流水編號遞增
	@Column(name = "seq")
	public int seq;// 打建構方法可省略,因為系統自動遞增,所以自己輸入的無效

	@Column(name = "category")
	private String category;

	@Column(name = "cook_method")
	private String cookMethod;

	@Column(name = "price")
	private int price;

	@Type(type = "uuid-char") // type import hibe....(第二個!!)
	@Column(name = "uuid")
	private UUID uuid = UUID.randomUUID();	 
	/*資料庫的型態是VERCHAR
	 * = UUID.randomUUID() -- 給預設值(選第三個)
	 * UUID:不重複的值,類似seq流水號
	 * 功能:取代id(id會重複),為了欄位有PKey可以用(可以分資料)
	 */

	public NewMenu() {
		super();

	}

	public NewMenu(String category, String cookMethod, int price) {
		super();
		this.category = category;
		this.cookMethod = cookMethod;
		this.price = price;
	}

	public NewMenu(String category, String cookMethod, int price, UUID uuid) {
		super();
		this.category = category;
		this.cookMethod = cookMethod;
		this.price = price;
		this.uuid = uuid;
	}

	public int getSaq() {
		return seq;
	}

	public void setSaq(int saq) {
		this.seq = saq;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCookMethod() {
		return cookMethod;
	}

	public void setCookMethod(String cookMethod) {
		this.cookMethod = cookMethod;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
