package com.spring.ex.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseSubTypeDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;

@Repository
public class AdminCourseDAOImpl implements AdminCourseDAO {

	@Inject
	private SqlSession sql;
	
	private final String namespace = "com.spring.ex.AdminCourseMapper";
	
	// 강의 게시판 가져오기
	@Override
	public List<Map<String, Object>> getCourseBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		map.put("nowPage", nowPage);
		map.put("pageSize", pageSize);
		return sql.selectList(namespace + ".getCourseBoard", map);
	}

	// 강의 게시물 총 개수 가져오기
	@Override
	public int getCoursePostCount(String searchCategory, String searchKeyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		return sql.selectOne(namespace + ".getCoursePostCount", map);
	}

	// 강사의 강의 게시판 가져오기
	@Override
	public List<Map<String, Object>> getTeacherCourseBoard(int olt_no, String searchCategory, String searchKeyword, int nowPage, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("olt_no", olt_no);
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		map.put("nowPage", nowPage);
		map.put("pageSize", pageSize);
		return sql.selectList(namespace + ".getTeacherCourseBoard", map);
	}
	
	// 강사의 강의 게시물 총 개수 가져오기
	@Override
	public int getTeacherCoursePostCount(int olt_no, String searchCategory, String searchKeyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("olt_no", olt_no);
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		return sql.selectOne(namespace + ".getTeacherCoursePostCount", map);
	}
	
	@Override
	public List<CourseDTO> getAllCourseList() {
		return sql.selectList(namespace + ".getAllCourseList");
	}
}
