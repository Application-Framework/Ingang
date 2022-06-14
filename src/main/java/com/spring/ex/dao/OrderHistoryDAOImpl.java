package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.PurchaseCourseDTO;

@Repository
public class OrderHistoryDAOImpl implements OrderHistoryDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.orderHistoryMapper";
	
	// 강의 구매 내역
	@Override
	public List<PurchaseCourseDTO> myPurchaseCourseList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myPurcaseCourses", m_no);
	}
}
