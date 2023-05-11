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
	 * IDENTITY:��Ʀ۰ʼW�� �Y���R�����ƪ����,��s�W�y������,���|�^�Y�ɸӬy����,���D�R��(drop table)��Ӹ�ƪ�~�|���s�Ƭy����
	 * �Y�n�R�����ƪ����,�n��J�ӫ��w���y����
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // �y���s�����W
	@Column(name = "seq")
	public int seq;// ���غc��k�i�ٲ�,�]���t�Φ۰ʻ��W,�ҥH�ۤv��J���L��

	@Column(name = "category")
	private String category;

	@Column(name = "cook_method")
	private String cookMethod;

	@Column(name = "price")
	private int price;

	@Type(type = "uuid-char") // type import hibe....(�ĤG��!!)
	@Column(name = "uuid")
	private UUID uuid = UUID.randomUUID();	 
	/*��Ʈw�����A�OVERCHAR
	 * = UUID.randomUUID() -- ���w�]��(��ĤT��)
	 * UUID:�����ƪ���,����seq�y����
	 * �\��:���Nid(id�|����),���F��즳PKey�i�H��(�i�H�����)
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
