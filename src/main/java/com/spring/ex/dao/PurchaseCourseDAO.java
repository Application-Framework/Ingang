package com.spring.ex.dao;


import org.springframework.stereotype.Repository;

import com.spring.ex.dto.PurchaseCourseDTO;

@Repository
public interface PurchaseCourseDAO {
	
	// 강의 구매
	public void purchaseCourse(PurchaseCourseDTO dto) throws Exception;	
}
