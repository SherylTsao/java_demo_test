package com.example.java_demo_test.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Login;


@Repository
public interface LoginDao extends JpaRepository<Login, String> {
	
	public Login findByAccountAndPassword (String account, String password);
	
	public Login findByAccountAndPasswordAndIsActive (String account, String password,boolean isActive);

	public List<Login> findByCityOrderByAge(String city);
	//居住在某城市的使用者搜尋,**Response不包含密碼**,含排序，照年齡
	//自定義方法的:Asc是預設可以不用寫
}
