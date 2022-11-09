package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.spring.ex.admin.dao.AdminCourseDAO;

@Repository
public class AdminCourseServiceImpl implements AdminCourseService {

	@Inject
	private AdminCourseDAO adminCourseDAO;

	// 강의 게시판 가져오기
	@Override
	public List<Map<String, Object>> getCourseBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize) {
		return adminCourseDAO.getCourseBoard(searchCategory, searchKeyword, nowPage, pageSize);
	}

	// 강의 게시물 총 개수 가져오기
	@Override
	public int getCoursePostCount(String searchCategory, String searchKeyword) {
		return adminCourseDAO.getCoursePostCount(searchCategory, searchKeyword);
	}
}
