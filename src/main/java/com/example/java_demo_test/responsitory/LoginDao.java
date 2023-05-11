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
	//�~��b�Y�������ϥΪ̷j�M,**Response���]�t�K�X**,�t�ƧǡA�Ӧ~��
	//�۩w�q��k��:Asc�O�w�]�i�H���μg
}
