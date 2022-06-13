package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.PurchaseCourseDTO;

@Repository
public interface OrderHistoryDAO {
	
	// 강의 구매 내역
	public List<PurchaseCourseDTO> myPurchaseCourseList(Integer m_no) throws Exception;
	
}
