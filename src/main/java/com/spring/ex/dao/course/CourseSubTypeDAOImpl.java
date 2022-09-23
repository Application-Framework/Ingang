package com.spring.ex.dao.course;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseSubTypeDTO;

@Repository
public class CourseSubTypeDAOImpl implements CourseSubTypeDAO {

	@Inject
	private SqlSession sql;
	
	private String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public List<CourseSubTypeDTO> getCourseSubTypeList(int oli_no) {
		return sql.selectList(namespace + ".getCourseSubTypeList", oli_no);
	}

	@Override
	public int insertCourseSubType(CourseSubTypeDTO dto) {
		return sql.insert(namespace + ".insertCourseSubType", dto);
	}

	@Override
	public int deleteCourseSubType(int oli_no) {
		return sql.delete(namespace + ".deleteCourseSubType", oli_no);
	}

}
