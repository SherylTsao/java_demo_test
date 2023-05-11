package com.example.java_demo_test.responsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.PersonInfo;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String> {
//<>裡面放的型態東西是JPA原本就有的,因為這個方法可以連結Entity,Entity的id型態~~~~~~~~~~~~~~

	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);
	// 自定義:依年齡由小到大排序
	// public List<PersonInfo>

	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(Integer fromAge, Integer toAge);

	public List<PersonInfo> findFirst3ByAgeGreaterThanEqualAndAgeLessThanEqualOrderByAgeDesc(Integer age1,
			Integer age2);
	// Between連續區間有包含 (fromAge,toAge)??
	// 找到年紀介於2個數字(有包含)之間的資訊(以年齡由大到小排序,只取前3筆資料)
	// 只取前3筆資料findTopBy,findFirstBy
	// 遞減Desc

	public List<PersonInfo> findByCityContaining(String city);
	// 取得 city 包含某個特定字的所有個人資訊

	public List<PersonInfo> findByAgeGreaterThanEqualAndCityContainingOrderByAgeDesc(Integer age, String city);
	// 自定義:依年齡由大到小排序,找出年紀大於輸入條件以及city 包含某個特定字的所有人資訊

	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(Integer age1, Integer age2);
	// 找到年紀小於輸入條件1或大於輸入條件2

	// 只要寫到update語法就要加transactional跟modifying
	// @Query(update 該Entity 簡寫為p 設置所有屬性 p.屬性名稱 = :自定義參數名稱)
	@Transactional
	@Modifying
	@Query("update PersonInfo p set p.id = :newId, p.name = :newName, p.age = :newAge, p.city = :newCity where p.id = :newId")
	public int updateNameById(@Param("newId") String inputId, @Param("newName") String inputName,
			@Param("newAge") int inputAge, @Param("newCity") String inputCity);

	@Transactional
	@Modifying
	@Query("update PersonInfo p set p.city = :newCity where p.id = :newId")
	public int updateNameById(@Param("newId") String inputId, @Param("newCity") String inputCity);

	public List<PersonInfo> doqueryByAge(int age);

	public List<PersonInfo> doqueryByAge(int age, int limitSize);

	public List<PersonInfo> doqueryByAge(int age, int limitSize, int startPosition);

	@Transactional
	public int updateAgeByName(int age, String name);

	
	@Query(value = "select * from person_info p where p.name like %:inputName% or p.city like %:inputCity%", nativeQuery = true)
	public List<PersonInfo> findByNameOrCity(@Param("inputName") String inputName, @Param("inputCity") String inputCity);

//	@Query("select * com.example.java_demo_test name like '%null%' or city like '%null%'")
//	public PersonInfo findByNameOrCity(@Param("name") String name, @Param("city") String city);

}