package com.example.java_demo_test.service.ifs;

import java.util.List;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

public interface PersonInfoService {

	public PersonInfoResponse addPersonInfo(List <PersonInfo> personInfoList);
	//建立addPersonInfo方法
	//一筆:帶入PersonInfo personInfo參數(帶入Entity整個class":一筆"個人資訊)
	//建立多筆:用List包住PersonInfo
	//回傳型態從void改成要回傳的東西,= 用完方法之後要回應的東西(ex.傳達的訊息...)
	
	public GetPersonInfoResponse  getPersonInfo();//取得所有個人資訊
	
	public GetPersonInfoResponse getPersonInfoById(String id);//為透過 id 取得對應的個人資訊
	
	//Asc(遞升)ascend
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThanOrderByAgeAsc(Integer age);//為找出年紀大於輸入條件的所有個人資訊 
	//PersonInfoResponse getPersonInfoByAgeGreaterThan(PersonInfoRequest personInfoRequest)

	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(Integer age);
	//找出年紀小於等於輸入條件的所有個人資訊

	//Between連續區間有包含 
	public GetPersonInfoResponse getPersonInfoByAgeBetween(Integer age1, Integer age2);
	// 找到年紀介於2個數字(有包含)之間的資訊
	
	public GetPersonInfoResponse getPersonInfoContaining(String city);
	//取得 city 包含某個特定字的所有個人資訊
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(Integer  age, String city);
	//找出年紀大於輸入條件以及city 包含某個特定字的所有人資訊
	
	//找到年紀小於輸入條件1或大於輸入條件2

   public GetPersonInfoResponse searchByNameOrCity(String name,String city);
	
}
