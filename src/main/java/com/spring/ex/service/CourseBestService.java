package com.spring.ex.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.CourseDTO;


@Service
public interface CourseBestService {
	
	// 이 주의 강의 목록
	public List<CourseDTO> thisweek_bestCourseList() throws Exception;
	
	// 신규 강의
	public List<CourseDTO> thisweek_newCourseList() throws Exception;
}
