package com.example.java_demo_test.responsitory;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	@PersistenceContext // JPA專有的註釋
	private EntityManager entityManager;

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
		// 除了不能用private其他都可以
		// 回傳型態:<EntityType> List<EntityType>--泛型<>裡的字沒有意義,是自定義的
		// 自定義方法名稱:doQuery
		// 同上面的foreach map方法
//		for(Parameter p: query.getParameters()) {
//			query.setParameter(p, params.get(p.getName()));
//		}

		return doQuery(sql, params, clazz, -1, -1);
	}
	/*
	 * 限制回傳筆數
	 */

//	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitSize) {
		// 除了不能用private其他都可以
		// 回傳型態:<EntityType> List<EntityType>--泛型<>裡的字沒有意義,是自定義的
		// 自定義方法名稱:doQuery
		return doQuery(sql, params, clazz, limitSize, -1);
	}

	/*
	 * limitSize:限制回傳筆數 limitSize:每頁起始位置
	 */

	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitSize, int startPosition) {
		// 除了不能用private其他都可以
		// 回傳型態:<EntityType> List<EntityType>--泛型<>裡的字沒有意義,是自定義的
		// 自定義方法名稱:doQuery
		Query query = entityManager.createQuery(sql, clazz);

		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		if (limitSize > 0) {
			query.setMaxResults(limitSize);
		}
		if (startPosition >= 0) {
			query.setFirstResult(startPosition);
		}

		return query.getResultList();
	}

	// update 回傳結果只有0/1 成功跟失敗
	public int doUpdate(String sql, Map<String, Object> params) {
		Query query = entityManager.createQuery(sql);

		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}

		}
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doNativeQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitSize, int startPosition) {
		// 除了不能用private其他都可以
		// 回傳型態:<EntityType> List<EntityType>--泛型<>裡的字沒有意義,是自定義的
		// 自定義方法名稱:doQuery
		Query query = entityManager.createNativeQuery(sql, clazz);

		if (!CollectionUtils.isEmpty(params)) {
			for (Entry<String, Object> item : params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		if (limitSize > 0) {
			query.setMaxResults(limitSize);
		}
		if (startPosition >= 0) {
			query.setFirstResult(startPosition);
		}

		return query.getResultList();
	}
}
