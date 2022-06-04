package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseTagDTO;

@Repository
public class CourseTagDAOImpl implements CourseTagDAO{
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.CourseMapper";
	@Override
	public List<CourseTagDTO> getCourseTags(int oli_no) {
		return sqlSession.selectList(namespace + ".getCourseTags", oli_no);
	}
	
	@Override
	public int submitTag(CourseTagDTO dto) {
		return sqlSession.insert(namespace + ".submitTag", dto);
	}
}
