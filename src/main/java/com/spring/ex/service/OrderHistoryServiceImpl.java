package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.OrderHistoryDAO;
import com.spring.ex.dto.PurchaseCourseDTO;
import com.spring.ex.dto.PurchaseNoteDTO;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
	
	@Inject
	private OrderHistoryDAO dao;
	
	// 강의 구매 내역
	@Override
	public List<PurchaseCourseDTO> myPurchaseCourseList(Integer m_no) throws Exception {
		return dao.myPurchaseCourseList(m_no);
	}
	
	// 강의 관심 내역
	@Override
	public List<PurchaseCourseDTO> myInterestCourseList(Integer m_no) throws Exception {
		return dao.myInterestCourseList(m_no);
	}

	// 내 강의 목록
	@Override
	public List<PurchaseCourseDTO> searchMyPurcaseCourses(Integer m_no, String keyword) throws Exception {
		return dao.searchMyPurcaseCourses(m_no, keyword);
	}

	// 노트 구매 내역
	@Override
	public List<PurchaseNoteDTO> myPurchaseNoteList(Integer m_no) throws Exception {
		return dao.myPurchaseNoteList(m_no);
	}
	
	// 노트 관심 내역
	@Override
	public List<PurchaseNoteDTO> myInterestNoteList(Integer m_no) throws Exception {
		return dao.myInterestNoteList(m_no);
	}
}
