package com.example.java_demo_test.responsitory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.responsitory.BaseDao;

public class PersonInfoDaoImpl extends BaseDao{

	
	//ぐ或璶Dao膥┯Dao?BaseDaoよ猭琵Dao膥┯
	//盢PersonInfoDao筁ㄓ龟
	public List<PersonInfo> doqueryByAge(int age){
		//﹃﹃
		StringBuffer sb = new StringBuffer();
		//PersonInfo P:硈钡Entity嘿
		sb.append("select P from PersonInfo P where p.age >= :age");
		Map<String, Object> params = new HashMap<>();
		//:key,()把计int age
		params.put("age", age);
		return doQuery(sb.toString(),params,PersonInfo.class);
	}
	
	public List<PersonInfo> doqueryByAge(int age,int limitSize){
		//﹃﹃
		StringBuffer sb = new StringBuffer();
		//PersonInfo P:硈钡Entity嘿
		sb.append("select P from PersonInfo P where p.age >= :age");
		Map<String, Object> params = new HashMap<>();
		//:key,()把计int age
		params.put("age", age);
		return doQuery(sb.toString(),params,PersonInfo.class,limitSize);
	}
	
	
	public List<PersonInfo> doqueryByAge(int age,int limitSize,int startPosition){
		//﹃﹃
		StringBuffer sb = new StringBuffer();
		//PersonInfo P:硈钡Entity嘿
		sb.append("select P from PersonInfo P where p.age >= :age");
		Map<String, Object> params = new HashMap<>();
		//:key,()把计int age
		params.put("age", age);
		return doQuery(sb.toString(),params,PersonInfo.class,limitSize,startPosition);
	}
	
	public int updateAgeByName(int age,String name) {
		StringBuffer sb = new StringBuffer();
		//糶粂猭~PersonInfo:Entity
		sb.append("update PersonInfo p set p.age = :age where name = :name");
		//盿把计
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		params.put("name", name);
		//肚BaseDaoよ猭
		return doUpdate(sb.toString(),params);
		
	}
	
	
	
	
	
	
	
}
