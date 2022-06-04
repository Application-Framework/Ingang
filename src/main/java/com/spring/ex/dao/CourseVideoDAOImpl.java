package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseVideoDTO;

@Repository
public class CourseVideoDAOImpl implements CourseVideoDAO {

	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public int submitCourseVideo(CourseVideoDTO dto) {
		return sqlSession.insert(namespace + ".submitCourseVideo", dto);
	}

	@Override
	public List<CourseVideoDTO> getCourseVideoList(int oli_no) {
		return sqlSession.selectList(namespace + ".getCourseVideoList", oli_no);
	}

	@Override
	public CourseVideoDTO getCourseVideo(int olv_no) {
		return sqlSession.selectOne(namespace + ".getCourseVideo", olv_no);
	}

}
