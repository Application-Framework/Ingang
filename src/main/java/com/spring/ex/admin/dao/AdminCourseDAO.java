package com.spring.ex.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseSubTypeDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;

@Repository
public interface AdminCourseDAO {
	// 강의 게시판 가져오기
	public List<Map<String, Object>> getCourseBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize);
	
	// 강의 게시물 총 개수 가져오기
	public int getCoursePostCount(String searchCategory, String searchKeyword);
	
	// 오늘부터 6일전까지의 주문내역 합계 가져오기
	public List<Map<String, Object>> getCourseOrderBy7Days();
}
