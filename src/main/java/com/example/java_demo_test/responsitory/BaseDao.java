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

	@PersistenceContext // JPA�M��������
	private EntityManager entityManager;

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
		// ���F�����private��L���i�H
		// �^�ǫ��A:<EntityType> List<EntityType>--�x��<>�̪��r�S���N�q,�O�۩w�q��
		// �۩w�q��k�W��:doQuery
		// �P�W����foreach map��k
//		for(Parameter p: query.getParameters()) {
//			query.setParameter(p, params.get(p.getName()));
//		}

		return doQuery(sql, params, clazz, -1, -1);
	}
	/*
	 * ����^�ǵ���
	 */

//	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitSize) {
		// ���F�����private��L���i�H
		// �^�ǫ��A:<EntityType> List<EntityType>--�x��<>�̪��r�S���N�q,�O�۩w�q��
		// �۩w�q��k�W��:doQuery
		return doQuery(sql, params, clazz, limitSize, -1);
	}

	/*
	 * limitSize:����^�ǵ��� limitSize:�C���_�l��m
	 */

	@SuppressWarnings("unchecked")
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int limitSize, int startPosition) {
		// ���F�����private��L���i�H
		// �^�ǫ��A:<EntityType> List<EntityType>--�x��<>�̪��r�S���N�q,�O�۩w�q��
		// �۩w�q��k�W��:doQuery
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

	// update �^�ǵ��G�u��0/1 ���\�򥢱�
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
		// ���F�����private��L���i�H
		// �^�ǫ��A:<EntityType> List<EntityType>--�x��<>�̪��r�S���N�q,�O�۩w�q��
		// �۩w�q��k�W��:doQuery
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
