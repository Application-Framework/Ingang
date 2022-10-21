package com.spring.ex.dao.course.request;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.request.RequestCourseDTO;

@Repository
public class RequestCourseDAOImpl implements RequestCourseDAO {

	@Inject
	private SqlSession sql;
	
	private String namespace = "com.spring.ex.RequestCourseMapper";
	
	@Override
	public int insertRequestCourse(RequestCourseDTO dto) {
		return sql.insert(namespace + ".insertRequestCourse", dto);
	}

	@Override
	public int updateRequestCourse(RequestCourseDTO dto) {
		return sql.update(namespace + ".updateRequestCourse", dto);
	}

	@Override
	public RequestCourseDTO selectOneRequestCourse(int olr_no) {
		return sql.selectOne(namespace + ".selectOneRequestCourse", olr_no);
	}

	@Override
	public List<RequestCourseDTO> selectListRequestCourse(int approval) {
		return sql.selectList(namespace + ".selectListRequestCourse", approval);
	}
	
}
