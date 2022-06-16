package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.PurchaseCourseDTO;
import com.spring.ex.dto.PurchaseNoteDTO;

@Repository
public interface OrderHistoryDAO {
	
	// 강의 구매 내역
	public List<PurchaseCourseDTO> myPurchaseCourseList(Integer m_no) throws Exception;
	
	// 강의 관심 내역
	public List<PurchaseCourseDTO> myInterestCourseList(Integer m_no) throws Exception;
	
	// 내 강의 목록
	public List<PurchaseCourseDTO> searchMyPurcaseCourses(Integer m_no, String keyword) throws Exception;
	
	// 노트 구매 내역
	public List<PurchaseNoteDTO> myPurchaseNoteList(Integer m_no) throws Exception;
		
	// 노트 관심 내역
	public List<PurchaseNoteDTO> myInterestNoteList(Integer m_no) throws Exception;
	
}
