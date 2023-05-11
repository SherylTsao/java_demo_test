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
	private PersonInfoDao personInfoDao;// 要從資料庫撈資料

	@Override
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfoList) {
		/*
		 * <新增個人資訊的方法> 1.防呆:檢查參數 1-1.檢查是否為null(因為foreach不能放null)
		 * 1-2.CollectionUtils.isEmpty(personInfoList):集合是Null或Empty空的
		 * 1-3.id/name不能NULL空字串空白:StringUtils.hasText 1-4.city不能空白或空字串:isBlank
		 * 2.Detail防呆 ~~檢查List中所有的資訊<PersonInfo>~~
		 * 
		 * 2-3.檢查id是否重複: 2-3-1.新增資料庫屬性PersonInfoDao並@Autowired:因為要抓資料庫的資料比對
		 * 2-3-2.新建List放所有id們 2-3-3.Dao.findAllById:用ids去找資料庫有沒有 3.放資料庫
		 * 
		 */
		// 放通過防呆的所有id們,等等要看id們有沒有重複
		List<String> ids = new ArrayList<>();
		if (CollectionUtils.isEmpty(personInfoList)) {// personInfoList == null
			return new PersonInfoResponse("新增錯誤!資訊不可為空");// 回應的建構方法
		}

		for (PersonInfo personInfoEachItem : personInfoList) {

			if (!StringUtils.hasText(personInfoEachItem.getId()) || !StringUtils.hasText(personInfoEachItem.getName())
					|| personInfoEachItem.getAge() < 0 || personInfoEachItem.getCity().isBlank()) {
				return new PersonInfoResponse("新增資訊錯誤");
			}

			// 檢查id
//			if(!StringUtils.hasText(personInfoEachItem.getId())) {
//				return new PersonInfoResponse("新增資訊錯誤");
//			}
//			//檢查name
//			if(!StringUtils.hasText(personInfoEachItem.getName())) {
//				return new PersonInfoResponse("新增資訊錯誤");
//			}//檢查age
//			if(personInfoEachItem.getAge()< 0) {
//				return new PersonInfoResponse("新增年齡錯誤");
//			}//檢查city
//			if(personInfoEachItem.getCity().isBlank()) {//不是空白或空字串
//				return new PersonInfoResponse("新增城市錯誤");
//			}
//			
//		}
			// 防呆id
			// 因為要比對id名稱有沒有重複,所以集合id們,等等要PK
			ids.add(personInfoEachItem.getId());

		}
		// 前面要autowired"Dao"因為要用資料庫的東西
		// findAllById:從資料庫找裡面有沒有一樣的id,找到了就會放進去另一個List裡(不是存進Dao)
		// 放重複的id(list)
		List<PersonInfo> res = personInfoDao.findAllById(ids);
		if (res.size() == personInfoList.size()) {// 重複的(長度)=原本輸入的(長度)!!!!
			return new PersonInfoResponse("全部新增失敗");// 如果有些成功,有些失敗怎辦??
		}

		// 比較長度:如果該值已經有長度,代表已經有值了(重複了)
		// 如果沒有長度,代表資料庫裡找不到,即可新增id
		List<PersonInfo> saveNewIds = new ArrayList<>();// 存入資料庫的清單(新的&正確的id)
		if (res.size() > 0) {// res.contains(personInfoList)
//			return new PersonInfoResponse("新增資訊錯誤");
			List<String> resIdsDouble = new ArrayList<>();// 放重複的id清單
			for (PersonInfo item : res) {
				resIdsDouble.add(item.getId());
				// 重複的id放進去重複id清單
			}
			// List<PersonInfo> resPersonInfo = new ArrayList<>();
			for (PersonInfo item : personInfoList) {
				if (!resIdsDouble.contains(item.getId())) {// 輸入重複的比較
					saveNewIds.add(item);// 沒有重複的存進新的list
				}

			}
//         <lamb foreach用法(同82~87用法)*還沒懂>
//			List<PersonInfo> saveNewIds2 = personInfoList.stream()
//					.filter(item -> resIdsDouble.contains(item.getId()))
//					.collect(Collectors.toList());
//
			// 可以優化成最上面的
//			if(saveNewIds.size()==0) {//saveNewIds.isEmplty,清單有長度代表有新的id,沒有長度代表新增失敗  
//				return new PersonInfoResponse("新增失敗");
//			}

			personInfoDao.saveAll(saveNewIds);// 放進去(可能情況2)
			return new PersonInfoResponse(personInfoList.size() - res.size(), "新增成功次數");

		}
		personInfoDao.saveAll(personInfoList);// 一次存入(可能情況1)

		return new PersonInfoResponse(personInfoList, "新增成功");
	}

	// ==========================0419作業=====================================
	// 取得"所有"個人資訊
	@Override
	public GetPersonInfoResponse getPersonInfo() {
		// 要從資料庫撈"所有"的資料
		List<PersonInfo> info = personInfoDao.findAll();

		return new GetPersonInfoResponse(info, "新增成功");
	}

	// ======================================================================
	// 為透過 id 取得對應的個人資訊
	/*
	 * 1.Response回傳的東西,(要求的東西)
	 * 
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoById(String id) {
		// 防呆
		if (!StringUtils.hasText(id)) {
			return new GetPersonInfoResponse("id不得為空");
		}
		// findById是jpa自訂的方法,要回傳optional型態
		Optional<PersonInfo> find = personInfoDao.findById(id);
		if (!find.isPresent()) {
			return new GetPersonInfoResponse("資料不存在");

		}
		// PersonInfo PersonInfo = find.get();
		// return new GetPersonInfoResponse(PersonInfo, "新增成功");
		// 等於上面兩行
		return new GetPersonInfoResponse(find.get(), "新增成功");
	}

	// ======================================================================
	/*
	 * 題目:為找出年紀大於輸入條件的所有個人資訊 防呆: 1.int不能防null,因為系統會自動轉換成0
	 * 2.用integer,因為預設值null,當輸入null時才可以防呆 3.integer要先防null再防0!!,因為若null不能跟0比較,會報錯!
	 * 作法: 1.從資料庫用語法抓出全部的資料 2.用foreach抓出其中的每一筆年齡 3.再用每一筆年齡和輸入的年齡比較
	 * 4.放一個list進去符合條件的該欄位所有資訊
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeGreaterThanOrderByAgeAsc(Integer age) {
		if (age == null || age == 0) {
			return new GetPersonInfoResponse("年齡不得為0或空白");
		}
		List<PersonInfo> allInfo = personInfoDao.findAll();
		List<PersonInfo> ageUp = new ArrayList<>();
		for (PersonInfo item : allInfo) {// 從資料庫找到的所有的資料,一筆一筆的找
			if (item.getAge() > age) {// 找到每一筆年齡
				ageUp.add(item);// 把所有年齡的所有個資放進去新的list
			}
		}

		return new GetPersonInfoResponse(ageUp, "找到年紀大於輸入條件的所有個人資訊");
	}

//====================================================================================
	/*
	 * 題目:找出年紀小於等於輸入條件的所有個人資訊(年齡從小到大排序)
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(Integer age) {
		if (age == null || age == 0) {
			return new GetPersonInfoResponse("年齡不得為0或空白");
		}
		// Dao自定義的語法從小到大排序
		List<PersonInfo> allAge = personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
		List<PersonInfo> ageUp = new ArrayList<>();
		for (PersonInfo item : allAge) {// 從資料庫找到的所有的資料,一筆一筆的找
			if (item.getAge() > age) {// 找到每一筆年齡
				ageUp.add(item);// 把所有年齡的所有個資放進去新的list
			}
		}

		return new GetPersonInfoResponse(allAge, "找出年紀小於等於輸入條件的所有個人資訊");
	}

//	public GetPersonInfoResponse getPersonInfoByAgeLessThanEqual(Integer age) {
//	if (age == null || age == 0) {
//		return new GetPersonInfoResponse("年齡不得為0或空白");
//	}
//	List<PersonInfo> allInfo = personInfoDao.findAll();
//	List<PersonInfo> ageUp = new ArrayList<>();
//	for (PersonInfo item : allInfo) {// 從資料庫找到的所有的資料,一筆一筆的找
//		if (item.getAge() <= age) {// 找到每一筆年齡
//			ageUp.add(item);// 把所有年齡的所有個資放進去新的list
//		}
//	}
//	ageUp.sort(.item.getAge().reverseOrder());
//	Arrays.sort();
//	return null;
//}
//}
//=====================================================================================
	/*
	 * 題目: 6.找到年紀介於2個數字(有包含)之間的資訊 (以年齡由大到小排序 只取前3筆資料)
	 *
	 * 
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeBetween(Integer age1, Integer age2) {
		// 防呆
		if (age1 == null || age1 == 0) {
			return new GetPersonInfoResponse("年齡不得為0或空白");
		}
		if (age2 == null || age2 == 0) {
			return new GetPersonInfoResponse("年齡不得為0或空白");
		}
		List<PersonInfo> chooseAge = personInfoDao
				.findFirst3ByAgeGreaterThanEqualAndAgeLessThanEqualOrderByAgeDesc(age1, age2);
		return new GetPersonInfoResponse(chooseAge, "找到年紀介於2個數字(有包含)之間的資訊");
	}

//=====================================================================================
	/*
	 * 題目: 7. 取得 city 包含某個特定字的所有個人資訊
	 * 
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoContaining(String city) {
		// 防呆
		if (city == null) {
			return new GetPersonInfoResponse("城市不得為空");
		}
		List<PersonInfo> conCity = personInfoDao.findByCityContaining(city);
		return new GetPersonInfoResponse(conCity, "取得 city 包含某個特定字的所有個人資訊");
	}

//=====================================================================================
	/*
	 * 題目:8.找出年紀大於輸入條件以及city 包含某個特定字的所有人資訊
	 */
	@Override
	public GetPersonInfoResponse getPersonInfoByAgeAndCityContaining(Integer age, String city) {
		// 防呆
		if (age == null || age == 0) {
			return new GetPersonInfoResponse("年齡不得為0或空白");
		}
		if (city == null) {
			return new GetPersonInfoResponse("城市不得為空");
		}
		List<PersonInfo> conCityAndBiggerAge = personInfoDao.findByAgeGreaterThanEqualAndCityContainingOrderByAgeDesc(age,city);
		
		return new GetPersonInfoResponse(conCityAndBiggerAge,"取得 city 包含某個特定字的所有個人資訊");
	}

	@Override
	public GetPersonInfoResponse searchByNameOrCity(String name, String city) {
		
		//防呆
		if(StringUtils.hasText(name)||StringUtils.hasText(city)) {
			return new GetPersonInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		
		
		return new GetPersonInfoResponse();
	}
}
