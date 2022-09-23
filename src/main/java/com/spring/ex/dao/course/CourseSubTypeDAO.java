package com.spring.ex.dao.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseSubTypeDTO;

@Repository
public interface CourseSubTypeDAO {
	// 강좌의 카테고리 가져오기
	public List<CourseSubTypeDTO> getCourseSubTypeList(int oli_no);
	
	// 강의에 카테고리 등록
	public int insertCourseSubType(CourseSubTypeDTO dto);
	
	// 강의 카테고리 삭제
	public int deleteCourseSubType(int oli_no);
}
