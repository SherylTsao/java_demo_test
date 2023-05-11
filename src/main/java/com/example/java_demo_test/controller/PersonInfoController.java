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

@RestController // ��controller�U��
public class PersonInfoController {

	@Autowired // ���w�U�����O
	private PersonInfoService personInfoService;// �s���W�@�hService

	@PostMapping(value = "addPersonInfo") // �s���~��
	public PersonInfoResponse addPersonInfo(@RequestBody AddPersonInfoRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return personInfoService.addPersonInfo(request.getPersonInfoList());// �s������
	}

	@PostMapping(value = "getPersonInfo") // �s���~��
	public GetPersonInfoResponse getPersonInfo() {
		// �q�`��Service��k�@�˦W��,����copy
		return personInfoService.getPersonInfo();// �s������
	}

	@PostMapping(value = "getPersonInfoById") // �s���~��
	public GetPersonInfoResponse getPersonInfoById(@RequestBody GetPersonInfoRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return personInfoService.getPersonInfoById(request.getId());// �s������
	}

	@PostMapping(value = "getPersonInfoByAgeGreaterThan") // �s���~��
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThan(@RequestBody GetPersonInfoRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return personInfoService.getPersonInfoByAgeGreaterThanOrderByAgeAsc(request.getAge());// �s������
	}
	@PostMapping(value = "getPersonInfoByAgeLessThanEqual") // �s���~��
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(@RequestBody GetPersonInfoRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return personInfoService.getPersonInfoByAgeLessThanEqual(request.getAge());// �s������
	}
	
	@PostMapping(value = "getPersonInfoByAgeBetween") // �s���~��
	public GetPersonInfoResponse getPersonInfoByAgeBetween(@RequestBody GetPersonInfoRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return personInfoService.getPersonInfoByAgeBetween(request.getAge(),request.getAge1());// �s������
	}
	@PostMapping(value = "getPersonInfoContaining") // �s���~��
	public GetPersonInfoResponse getPersonInfoContaining(@RequestBody GetPersonInfoRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return personInfoService.getPersonInfoContaining(request.getCity());// �s������
	}
	@PostMapping(value = "getPersonInfoByAgeAndCityContaining") // �s���~��
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(@RequestBody GetPersonInfoRequest request) {
		// �q�`��Service��k�@�˦W��,����copy
		return personInfoService.getPersonInfoByAgeAndCityContaining(request.getAge(),request.getCity());// �s������
	}
}