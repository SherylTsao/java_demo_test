package com.example.java_demo_test.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Menu;

@Repository // MenuDao給他託管,用於標記資料存取層，標記於介面層
public interface MenuDao extends JpaRepository<Menu, String> {
//介面MenuDao繼承Jpa的屬性&方法

	public Menu findByName(String name);
	
	
}
