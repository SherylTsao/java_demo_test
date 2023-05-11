package com.example.java_demo_test.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.constants.RtnCode;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.responsitory.PersonInfoDao;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;// �n�q��Ʈw�����

	@Override
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList) {
		/*
		 * <�s�W�ӤH��T����k> 1.���b:�ˬd�Ѽ� 1-1.�ˬd�O�_��null(�]��foreach�����null)
		 * 1-2.CollectionUtils.isEmpty(personInfoList):���X�ONull��Empty�Ū�
		 * 1-3.id/name����NULL�Ŧr��ť�:StringUtils.hasText 1-4.city����ťթΪŦr��:isBlank
		 * 2.Detail���b ~~�ˬdList���Ҧ�����T<PersonInfo>~~
		 * 
		 * 2-3.�ˬdid�O�_����: 2-3-1.�s�W��Ʈw�ݩ�PersonInfoDao��@Autowired:�]���n���Ʈw����Ƥ��
		 * 2-3-2.�s��List��Ҧ�id�� 2-3-3.Dao.findAllById:��ids�h���Ʈw���S�� 3.���Ʈw
		 * 
		 */
		// ��q�L���b���Ҧ�id��,�����n��id�̦��S������
		List<String> ids = new ArrayList<>();
		if (CollectionUtils.isEmpty(personInfoList)) {// personInfoList == null
			return new PersonInfoResponse("�s�W���~!��T���i����");// �^�����غc��k
		}

		for (PersonInfo personInfoEachItem : personInfoList) {

			if (!StringUtils.hasText(personInfoEachItem.getId()) || !StringUtils.hasText(personInfoEachItem.getName())
					|| personInfoEachItem.getAge() < 0 || personInfoEachItem.getCity().isBlank()) {
				return new PersonInfoResponse("�s�W��T���~");
			}

			// �ˬdid
//			if(!StringUtils.hasText(personInfoEachItem.getId())) {
//				return new PersonInfoResponse("�s�W��T���~");
//			}
//			//�ˬdname
//			if(!StringUtils.hasText(personInfoEachItem.getName())) {
//				return new PersonInfoResponse("�s�W��T���~");
//			}//�ˬdage
//			if(personInfoEachItem.getAge()< 0) {
//				return new PersonInfoResponse("�s�W�~�ֿ��~");
//			}//�ˬdcity
//			if(personInfoEachItem.getCity().isBlank()) {//���O�ťթΪŦr��
//				return new PersonInfoResponse("�s�W�������~");
//			}
//			
//		}
			// ���bid
			// �]���n���id�W�٦��S������,�ҥH���Xid��,�����nPK
			ids.add(personInfoEachItem.getId());

		}
		// �e���nautowired"Dao"�]���n�θ�Ʈw���F��
		// findAllById:�q��Ʈw��̭����S���@�˪�id,���F�N�|��i�h�t�@��List��(���O�s�iDao)
		// �񭫽ƪ�id(list)
		List<PersonInfo> res = personInfoDao.findAllById(ids);
		if (res.size() == personInfoList.size()) {// ���ƪ�(����)=�쥻��J��(����)!!!!
			return new PersonInfoResponse("�����s�W����");// �p�G���Ǧ��\,���ǥ��ѫ��??
		}

		// �������:�p�G�ӭȤw�g������,�N��w�g���ȤF(���ƤF)
		// �p�G�S������,�N���Ʈw�̧䤣��,�Y�i�s�Wid
		List<PersonInfo> saveNewIds = new ArrayList<>();// �s�J��Ʈw���M��(�s��&���T��id)
		if (res.size() > 0) {// res.contains(personInfoList)
//			return new PersonInfoResponse("�s�W��T���~");
			List<String> resIdsDouble = new ArrayList<>();// �񭫽ƪ�id�M��
			for (PersonInfo item : res) {
				resIdsDouble.add(item.getId());
				// ���ƪ�id��i�h����id�M��
			}
			// List<PersonInfo> resPersonInfo = new ArrayList<>();
			for (PersonInfo item : personInfoList) {
				if (!resIdsDouble.contains(item.getId())) {// ��J���ƪ����
					saveNewIds.add(item);// �S�����ƪ��s�i�s��list
				}

			}
//         <lamb foreach�Ϊk(�P82~87�Ϊk)*�٨S��>
//			List<PersonInfo> saveNewIds2 = personInfoList.stream()
//					.filter(item -> resIdsDouble.contains(item.getId()))
//					.collect(Collectors.toList());
//
			// �i�H�u�Ʀ��̤W����
//			if(saveNewIds.size()==0) {//saveNewIds.isEmplty,�M�榳���ץN���s��id,�S�����ץN��s�W����  
//				return new PersonInfoResponse("�s�W����");
//			}

			personInfoDao.saveAll(saveNewIds);// ��i�h(�i�ౡ�p2)
			return new PersonInfoResponse(personInfoList.size() - res.size(), "�s�W���\����");

		}
		personInfoDao.saveAll(personInfoList);// �@���s�J(�i�ౡ�p1)

		return new PersonInfoResponse(personInfoList, "�s�W���\");
	}

	// ==========================0419�@�~=====================================
	// ���o"�Ҧ�"�ӤH��T
	@Override
	public GetPersonInfoResponse getPersonInfo() {
		// �n�q��Ʈw��"�Ҧ�"�����
		List<PersonInfo> info = personInfoDao.findAll();

		return new GetPersonInfoResponse(info, "�s�W���\");
	}

	// ======================================================================
	// ���z�L id ���o�������ӤH��T
	/*
	 * 1.Response�^�Ǫ��F��,(�n�D���F��)
	 * 
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoById(String id) {
		// ���b
		if (!StringUtils.hasText(id)) {
			return new GetPersonInfoResponse("id���o����");
		}
		// findById�Ojpa�ۭq����k,�n�^��optional���A
		Optional<PersonInfo> find = personInfoDao.findById(id);
		if (!find.isPresent()) {
			return new GetPersonInfoResponse("��Ƥ��s�b");

		}
		// PersonInfo PersonInfo = find.get();
		// return new GetPersonInfoResponse(PersonInfo, "�s�W���\");
		// ����W�����
		return new GetPersonInfoResponse(find.get(), "�s�W���\");
	}

	// ======================================================================
	/*
	 * �D��:����X�~���j���J���󪺩Ҧ��ӤH��T ���b: 1.int���ਾnull,�]���t�η|�۰��ഫ��0
	 * 2.��integer,�]���w�]��null,���Jnull�ɤ~�i�H���b 3.integer�n����null�A��0!!,�]���Ynull�����0���,�|����!
	 * �@�k: 1.�q��Ʈw�λy�k��X��������� 2.��foreach��X�䤤���C�@���~�� 3.�A�ΨC�@���~�֩M��J���~�֤��
	 * 4.��@��list�i�h�ŦX���󪺸����Ҧ���T
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThanOrderByAgeAsc(Integer age) {
		if (age == null || age == 0) {
			return new GetPersonInfoResponse("�~�֤��o��0�Ϊť�");
		}
		List<PersonInfo> allInfo = personInfoDao.findAll();
		List<PersonInfo> ageUp = new ArrayList<>();
		for (PersonInfo item : allInfo) {// �q��Ʈw��쪺�Ҧ������,�@���@������
			if (item.getAge() > age) {// ���C�@���~��
				ageUp.add(item);// ��Ҧ��~�֪��Ҧ��Ӹ��i�h�s��list
			}
		}

		return new GetPersonInfoResponse(ageUp, "���~���j���J���󪺩Ҧ��ӤH��T");
	}

//====================================================================================
	/*
	 * �D��:��X�~���p�󵥩��J���󪺩Ҧ��ӤH��T(�~�ֱq�p��j�Ƨ�)
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(Integer age) {
		if (age == null || age == 0) {
			return new GetPersonInfoResponse("�~�֤��o��0�Ϊť�");
		}
		// Dao�۩w�q���y�k�q�p��j�Ƨ�
		List<PersonInfo> allAge = personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
		List<PersonInfo> ageUp = new ArrayList<>();
		for (PersonInfo item : allAge) {// �q��Ʈw��쪺�Ҧ������,�@���@������
			if (item.getAge() > age) {// ���C�@���~��
				ageUp.add(item);// ��Ҧ��~�֪��Ҧ��Ӹ��i�h�s��list
			}
		}

		return new GetPersonInfoResponse(allAge, "��X�~���p�󵥩��J���󪺩Ҧ��ӤH��T");
	}

//	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(Integer age) {
//	if (age == null || age == 0) {
//		return new GetPersonInfoResponse("�~�֤��o��0�Ϊť�");
//	}
//	List<PersonInfo> allInfo = personInfoDao.findAll();
//	List<PersonInfo> ageUp = new ArrayList<>();
//	for (PersonInfo item : allInfo) {// �q��Ʈw��쪺�Ҧ������,�@���@������
//		if (item.getAge() <= age) {// ���C�@���~��
//			ageUp.add(item);// ��Ҧ��~�֪��Ҧ��Ӹ��i�h�s��list
//		}
//	}
//	ageUp.sort(.item.getAge().reverseOrder());
//	Arrays.sort();
//	return null;
//}
//}
//=====================================================================================
	/*
	 * �D��: 6.���~������2�ӼƦr(���]�t)��������T (�H�~�֥Ѥj��p�Ƨ� �u���e3�����)
	 *
	 * 
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeBetween(Integer age1, Integer age2) {
		// ���b
		if (age1 == null || age1 == 0) {
			return new GetPersonInfoResponse("�~�֤��o��0�Ϊť�");
		}
		if (age2 == null || age2 == 0) {
			return new GetPersonInfoResponse("�~�֤��o��0�Ϊť�");
		}
		List<PersonInfo> chooseAge = personInfoDao
				.findFirst3ByAgeGreaterThanEqualAndAgeLessThanEqualOrderByAgeDesc(age1, age2);
		return new GetPersonInfoResponse(chooseAge, "���~������2�ӼƦr(���]�t)��������T");
	}

//=====================================================================================
	/*
	 * �D��: 7. ���o city �]�t�Y�ӯS�w�r���Ҧ��ӤH��T
	 * 
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoContaining(String city) {
		// ���b
		if (city == null) {
			return new GetPersonInfoResponse("�������o����");
		}
		List<PersonInfo> conCity = personInfoDao.findByCityContaining(city);
		return new GetPersonInfoResponse(conCity, "���o city �]�t�Y�ӯS�w�r���Ҧ��ӤH��T");
	}

//=====================================================================================
	/*
	 * �D��:8.��X�~���j���J����H��city �]�t�Y�ӯS�w�r���Ҧ��H��T
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(Integer age, String city) {
		// ���b
		if (age == null || age == 0) {
			return new GetPersonInfoResponse("�~�֤��o��0�Ϊť�");
		}
		if (city == null) {
			return new GetPersonInfoResponse("�������o����");
		}
		List<PersonInfo> conCityAndBiggerAge = personInfoDao.findByAgeGreaterThanEqualAndCityContainingOrderByAgeDesc(age,city);
		
		return new GetPersonInfoResponse(conCityAndBiggerAge,"���o city �]�t�Y�ӯS�w�r���Ҧ��ӤH��T");
	}

	@Override
	public GetPersonInfoResponse searchByNameOrCity(String name, String city) {
		
		//���b
		if(StringUtils.hasText(name)||StringUtils.hasText(city)) {
			return new GetPersonInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		
		
		return new GetPersonInfoResponse();
	}
}
