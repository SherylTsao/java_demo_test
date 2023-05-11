package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

public interface PersonInfoService {

	public PersonInfoResponse addPersonInfo(List <PersonInfo> personInfoList);
	//�إ�addPersonInfo��k
	//�@��:�a�JPersonInfo personInfo�Ѽ�(�a�JEntity���class":�@��"�ӤH��T)
	//�إߦh��:��List�]��PersonInfo
	//�^�ǫ��A�qvoid�令�n�^�Ǫ��F��,= �Χ���k����n�^�����F��(ex.�ǹF���T��...)
	
	public GetPersonInfoResponse  getPersonInfo();//���o�Ҧ��ӤH��T
	
	public GetPersonInfoResponse getPersonInfoById(String id);//���z�L id ���o�������ӤH��T
	
	//Asc(����)ascend
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThanOrderByAgeAsc(Integer age);//����X�~���j���J���󪺩Ҧ��ӤH��T 
	//PersonInfoResponse getPersonInfoByAgeGreaterThan(PersonInfoRequest personInfoRequest)

	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(Integer age);
	//��X�~���p�󵥩��J���󪺩Ҧ��ӤH��T

	//Between�s��϶����]�t 
	public GetPersonInfoResponse getPersonInfoByAgeBetween(Integer age1, Integer age2);
	// ���~������2�ӼƦr(���]�t)��������T
	
	public GetPersonInfoResponse getPersonInfoContaining(String city);
	//���o city �]�t�Y�ӯS�w�r���Ҧ��ӤH��T
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(Integer  age, String city);
	//��X�~���j���J����H��city �]�t�Y�ӯS�w�r���Ҧ��H��T
	
	//���~���p���J����1�Τj���J����2

   public GetPersonInfoResponse searchByNameOrCity(String name,String city);
	
}
