package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CourseDTO;
import com.spring.ex.dto.CourseReplyDTO;
import com.spring.ex.dto.CourseTagDTO;

@Repository
public interface CourseDAO {
	
	// 페이지의 게시물 가져오기
	public List<HashMap<String, Object>> getCoursePage(HashMap<String, Object> map);
	
	// 페이지 개수 가져오기
	public int getCourseTotalCount(HashMap<String, Object> map);
	
	// 강의 상세 가져오기
	public CourseDTO getCourseDetail(int oli_no);
	
	// 강의 등록
	public int submitCourse(CourseDTO dto);
	
}
