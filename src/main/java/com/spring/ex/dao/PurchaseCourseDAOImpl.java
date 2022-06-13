package com.spring.ex.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.PurchaseCourseDTO;

@Repository
public class PurchaseCourseDAOImpl implements PurchaseCourseDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.spring.mappers.purchaseMapper";
	
	// 강의 구매
	@Override
	public void purchaseCourse(PurchaseCourseDTO dto) throws Exception {
		sqlSession.insert(namespace + ".purchaseCourse", dto);
	}

}
