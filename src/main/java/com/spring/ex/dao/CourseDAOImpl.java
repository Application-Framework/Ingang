package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAOImpl implements CourseDAO {
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public List<HashMap<String, Object>> getCoursePage(HashMap<String, Object> map) {
		return sqlSession.selectList(namespace + ".getCoursePage", map);
	}

	@Override
	public int getCourseTotalCount(HashMap<String, Object> map) {
		return sqlSession.selectOne(namespace + ".getCourseTotalCount", map);
	}
	
}
