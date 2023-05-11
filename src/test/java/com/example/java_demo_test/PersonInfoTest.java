package com.example.java_demo_test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.responsitory.PersonInfoDao;

@SpringBootTest
public class PersonInfoTest {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Test
	public void updateNameByIdTest() {
		int res = personInfoDao.updateNameById("1", "Cana", 2, "Canada");
		System.out.println(res);
	}

//	@Test
//	public void doqueryByAgeTest() {
//		List<PersonInfo> res = personInfoDao.doqueryByAge(30);
//		System.out.println(res);
//	}
	@Test
	public void updateAgeByNameTest() {
		int res = personInfoDao.updateAgeByName(20,"Cana");
		System.out.println(res);
	}

	@Test
	public void findByNameOrCity() {
//		
		 List<PersonInfo> res = personInfoDao.findByNameOrCity("1", "«n");
		 System.out.println(res.size()+"???????????");
	}
	
}
