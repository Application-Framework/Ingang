package com.spring.ex.dao.course;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;

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

	@Override
	public CourseDTO getCourseDetail(int oli_no) {
		return sqlSession.selectOne(namespace + ".getCourseDetail", oli_no);
	}
	
	@Override
	public int submitCourse(CourseDTO dto) {
		return sqlSession.insert(namespace + ".submitCourse", dto);
	}
}
