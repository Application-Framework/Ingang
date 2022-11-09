package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface AdminCourseService {
	// 강의 게시판 가져오기
	public List<Map<String, Object>> getCourseBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize);
	
	// 강의 게시물 총 개수 가져오기
	public int getCoursePostCount(String searchCategory, String searchKeyword);
}
