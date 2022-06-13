package com.spring.ex.service;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.PurchaseCourseDTO;

@Service
public interface PurchaseCourseService {
	
	// 강의 구매
	public void purchaseCourse(PurchaseCourseDTO dto) throws Exception;
	
}
