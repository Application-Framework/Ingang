package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseDTO;

@Repository
public interface CourseBestDAO {
	
	// 이 주의 강의 목록
	public List<CourseDTO> thisweek_bestCourseList() throws Exception;
	
	// 신규 강의
	public List<CourseDTO> thisweek_newCourseList() throws Exception;
}
