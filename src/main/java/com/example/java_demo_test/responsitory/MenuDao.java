package com.example.java_demo_test.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Menu;

@Repository // MenuDao���L�U��,�Ω�аO��Ʀs���h�A�аO�󤶭��h
public interface MenuDao extends JpaRepository<Menu, String> {
//����MenuDao�~��Jpa���ݩ�&��k

	public Menu findByName(String name);
	
	
}
