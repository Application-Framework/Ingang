package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CourseDTO;


@Repository
public class CourseBestDAOImpl implements CourseBestDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.indexCourseMapper";
	
	// 이 주의 강의 목록
	@Override
	public List<CourseDTO> thisweek_bestCourseList() throws Exception {
		return sqlSession.selectList(namespace + ".thisweek_bestCourseList");
	}

	// 신규 강의
	@Override
	public List<CourseDTO> thisweek_newCourseList() throws Exception {
		return sqlSession.selectList(namespace + ".thisweek_newCourseList");
	}
}