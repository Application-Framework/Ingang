package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.PurchaseCourseDTO;
import com.spring.ex.dto.course.CourseLikeDTO;

@Service
public interface DropBoxService {
	
	// 드롭박스 관심 강의 내역
	public List<PurchaseCourseDTO> myInterestedCourseDropBox(Integer m_no) throws Exception;
	
	// 드롭박스 관심 강의 갯수
	public Integer myInterestedCourseCountDropBox(Integer m_no) throws Exception;
}
