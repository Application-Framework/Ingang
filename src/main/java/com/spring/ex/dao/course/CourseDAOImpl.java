package com.spring.ex.dao.course;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseDTO;

@Repository
public class CourseDAOImpl implements CourseDAO {
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public List<CourseDTO> getCoursePage(HashMap<String, Object> map) {
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
	public int submitCourse(CourseDTO courseDTO) {
		return sqlSession.insert(namespace + ".submitCourse", courseDTO);
	}

	@Override
	public int updateCourse(CourseDTO courseDTO) {
		return sqlSession.update(namespace + ".updateCourse", courseDTO);
	}
	
	@Override
	public int deleteCourse(int oli_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteCourse", oli_no);
	}
}
