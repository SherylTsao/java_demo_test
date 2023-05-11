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
//<>�̭��񪺫��A�F��OJPA�쥻�N����,�]���o�Ӥ�k�i�H�s��Entity,Entity��id���A~~~~~~~~~~~~~~

	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);
	// �۩w�q:�̦~�֥Ѥp��j�Ƨ�
	// public List<PersonInfo>

	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(Integer fromAge, Integer toAge);

	public List<PersonInfo> findFirst3ByAgeGreaterThanEqualAndAgeLessThanEqualOrderByAgeDesc(Integer age1,
			Integer age2);
	// Between�s��϶����]�t (fromAge,toAge)??
	// ���~������2�ӼƦr(���]�t)��������T(�H�~�֥Ѥj��p�Ƨ�,�u���e3�����)
	// �u���e3�����findTopBy,findFirstBy
	// ����Desc

	public List<PersonInfo> findByCityContaining(String city);
	// ���o city �]�t�Y�ӯS�w�r���Ҧ��ӤH��T

	public List<PersonInfo> findByAgeGreaterThanEqualAndCityContainingOrderByAgeDesc(Integer age, String city);
	// �۩w�q:�̦~�֥Ѥj��p�Ƨ�,��X�~���j���J����H��city �]�t�Y�ӯS�w�r���Ҧ��H��T

	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(Integer age1, Integer age2);
	// ���~���p���J����1�Τj���J����2

	// �u�n�g��update�y�k�N�n�[transactional��modifying
	// @Query(update ��Entity ²�g��p �]�m�Ҧ��ݩ� p.�ݩʦW�� = :�۩w�q�ѼƦW��)
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