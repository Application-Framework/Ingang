package com.spring.ex.dao;

import java.util.HashMap;
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

	// 강의 관심 내역
	@Override
	public List<PurchaseCourseDTO> myInterestCourseList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myInterestCourses", m_no);
	}
	
	// 내 강의 목록
	@Override
	public List<PurchaseCourseDTO> searchMyPurcaseCourses(Integer m_no, String keyword) throws Exception {
			
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("m_no", m_no);
		data.put("keyword", keyword);
		
		return sqlSession.selectList(namespace + ".searchMyPurcaseCourses", data);
	}

	// 노트 구매 내역
	@Override
	public List<PurchaseCourseDTO> myPurchaseNoteList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myPurchaseNoteList", m_no);
	}
	
	// 노트 관심 내역
	@Override
	public List<PurchaseCourseDTO> myInterestNoteList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myPurchaseNoteList", m_no);
	}
}
