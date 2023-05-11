package com.example.java_demo_test.responsitory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.responsitory.BaseDao;

public class PersonInfoDaoImpl extends BaseDao{

	
	//������nDao�~��Dao?�]��BaseDao����k�~�i�H���h��Dao�~��
	//�NPersonInfoDao���L�ӹ�@
	public List<PersonInfo> doqueryByAge(int age){
		//��r��
		StringBuffer sb = new StringBuffer();
		//PersonInfo P:�s��Entity�W��
		sb.append("select P from PersonInfo P where p.age >= :age");
		Map<String, Object> params = new HashMap<>();
		//:key,()�Ѽ�int age
		params.put("age", age);
		return doQuery(sb.toString(),params,PersonInfo.class);
	}
	
	public List<PersonInfo> doqueryByAge(int age,int limitSize){
		//��r��
		StringBuffer sb = new StringBuffer();
		//PersonInfo P:�s��Entity�W��
		sb.append("select P from PersonInfo P where p.age >= :age");
		Map<String, Object> params = new HashMap<>();
		//:key,()�Ѽ�int age
		params.put("age", age);
		return doQuery(sb.toString(),params,PersonInfo.class,limitSize);
	}
	
	
	public List<PersonInfo> doqueryByAge(int age,int limitSize,int startPosition){
		//��r��
		StringBuffer sb = new StringBuffer();
		//PersonInfo P:�s��Entity�W��
		sb.append("select P from PersonInfo P where p.age >= :age");
		Map<String, Object> params = new HashMap<>();
		//:key,()�Ѽ�int age
		params.put("age", age);
		return doQuery(sb.toString(),params,PersonInfo.class,limitSize,startPosition);
	}
	
	public int updateAgeByName(int age,String name) {
		StringBuffer sb = new StringBuffer();
		//�g�y�k~PersonInfo:Entity
		sb.append("update PersonInfo p set p.age = :age where name = :name");
		//�a�Ѽ�
		Map<String, Object> params = new HashMap<>();
		params.put("age", age);
		params.put("name", name);
		//�^��BaseDao����k
		return doUpdate(sb.toString(),params);
		
	}
	
	
	
	
	
	
	
}
