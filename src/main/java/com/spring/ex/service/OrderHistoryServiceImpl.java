package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.OrderHistoryDAO;
import com.spring.ex.dto.PurchaseCourseDTO;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
	
	@Inject
	private OrderHistoryDAO dao;
	
	// 강의 구매 내역
	@Override
	public List<PurchaseCourseDTO> myPurchaseCourseList(Integer m_no) throws Exception {
		return dao.myPurchaseCourseList(m_no);
	}

}
