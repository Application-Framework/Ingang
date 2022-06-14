package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.PurchaseCourseDTO;

@Service
public interface OrderHistoryService {
	
	// 강의 구매 내역
	public List<PurchaseCourseDTO> myPurchaseCourseList(Integer m_no) throws Exception;
	
	// 강의 관심 내역
	public List<PurchaseCourseDTO> myInterestCourseList(Integer m_no) throws Exception;
	
	// 내 강의 목록
	public List<PurchaseCourseDTO> searchMyPurcaseCourses(Integer m_no, String keyword) throws Exception;
	
}
