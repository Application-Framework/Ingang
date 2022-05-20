package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseDAO {
	
	// 페이지의 게시물 가져오기
	public List<HashMap<String, Object>> getCoursePage(HashMap<String, Object> map);
	
	// 페이지 개수 가져오기
	public int getCourseTotalCount(HashMap<String, Object> map);
}
