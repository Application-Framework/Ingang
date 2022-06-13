package com.spring.ex.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.PurchaseCourseDAO;
import com.spring.ex.dto.PurchaseCourseDTO;

@Service
public class PurchaseCourseServiceImpl implements PurchaseCourseService {
	
	@Inject
	private PurchaseCourseDAO dao;
	
	// 강의 구매
	@Override
	public void purchaseCourse(PurchaseCourseDTO dto) throws Exception {
		dao.purchaseCourse(dto);
	}

}
