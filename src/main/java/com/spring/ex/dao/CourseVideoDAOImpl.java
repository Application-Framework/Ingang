package com.spring.ex.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CourseVideoDTO;

@Repository
public class CourseVideoDAOImpl implements CourseVideoDAO {

	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public int submitCourseVideo(CourseVideoDTO dto) {
		return sqlSession.insert(namespace + ".submitCourseVideo", dto);
	}

}
