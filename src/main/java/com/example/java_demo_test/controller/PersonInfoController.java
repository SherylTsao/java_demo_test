package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.AddPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoRequest;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@RestController // 給controller託管
public class PersonInfoController {

	@Autowired // 指定託管類別
	private PersonInfoService personInfoService;// 連結上一層Service

	@PostMapping(value = "addPersonInfo") // 連結外部
	public PersonInfoResponse addPersonInfo(@RequestBody AddPersonInfoRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return personInfoService.addPersonInfo(request.getPersonInfoList());// 連結內部
	}

	@PostMapping(value = "getPersonInfo") // 連結外部
	public GetPersonInfoResponse getPersonInfo() {
		// 通常跟Service方法一樣名稱,直接copy
		return personInfoService.getPersonInfo();// 連結內部
	}

	@PostMapping(value = "getPersonInfoById") // 連結外部
	public GetPersonInfoResponse getPersonInfoById(@RequestBody GetPersonInfoRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return personInfoService.getPersonInfoById(request.getId());// 連結內部
	}

	@PostMapping(value = "getPersonInfoByAgeGreaterThan") // 連結外部
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(@RequestBody GetPersonInfoRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return personInfoService.getPersonInfoByAgeGreaterThanOrderByAgeAsc(request.getAge());// 連結內部
	}
	@PostMapping(value = "getPersonInfoByAgeLessThanEqual") // 連結外部
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(@RequestBody GetPersonInfoRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return personInfoService.getPersonInfoByAgeLessThanEqual(request.getAge());// 連結內部
	}
	
	@PostMapping(value = "getPersonInfoByAgeBetween") // 連結外部
	public GetPersonInfoResponse getPersonInfoByAgeBetween(@RequestBody GetPersonInfoRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return personInfoService.getPersonInfoByAgeBetween(request.getAge(),request.getAge1());// 連結內部
	}
	@PostMapping(value = "getPersonInfoContaining") // 連結外部
	public GetPersonInfoResponse getPersonInfoContaining(@RequestBody GetPersonInfoRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return personInfoService.getPersonInfoContaining(request.getCity());// 連結內部
	}
	@PostMapping(value = "getPersonInfoByAgeAndCityContaining") // 連結外部
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(@RequestBody GetPersonInfoRequest request) {
		// 通常跟Service方法一樣名稱,直接copy
		return personInfoService.getPersonInfoByAgeAndCityContaining(request.getAge(),request.getCity());// 連結內部
	}
}