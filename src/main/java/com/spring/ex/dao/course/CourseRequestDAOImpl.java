package com.spring.ex.dao.course;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseRequestDTO;

@Repository
public class CourseRequestDAOImpl implements CourseRequestDAO {

	@Inject
	private SqlSession sql;
	
	private String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public List<Map<String, Object>> selectListPendingCourseRequest() {
		return sql.selectList(namespace + ".selectListPendingCourseRequest");
	}

	@Override
	public List<CourseRequestDTO> selectListCourseRequestByOli_no(int oli_no) {
		return sql.selectList(namespace + ".selectCourseRequestListByOli_no", oli_no);
	}

	@Override
	public CourseRequestDTO selectOneCourseRequest(int olr_no) {
		return sql.selectOne(namespace + ".selectOneCourseRequest", olr_no);
	}
	
	@Override
	public int insertCourseRequest(CourseRequestDTO dto) {
		return sql.insert(namespace + ".insertCourseRequest", dto);
	}

	@Override
	public int updateCourseRequest(CourseRequestDTO dto) {
		return sql.update(namespace + ".updateCourseRequest", dto);
	}

	@Override
	public int deleteCourseRequest(int olr_no) {
		return sql.delete(namespace + ".deleteCourseRequest", olr_no);
	}
}
