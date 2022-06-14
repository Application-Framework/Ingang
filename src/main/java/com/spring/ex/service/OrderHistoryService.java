package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.PurchaseCourseDTO;

@Service
public interface OrderHistoryService {
	
	// 강의 구매 내역
	public List<PurchaseCourseDTO> myPurchaseCourseList(Integer m_no) throws Exception;
	
}
