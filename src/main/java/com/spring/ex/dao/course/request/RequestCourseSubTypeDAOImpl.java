package com.spring.ex.dao.course.request;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.request.RequestCourseSubTypeDTO;

@Repository
public class RequestCourseSubTypeDAOImpl implements RequestCourseSubTypeDAO {

	@Inject
	private SqlSession sql;
	
	private String namespace = "com.spring.ex.RequestCourseMapper";
	
	@Override
	public List<RequestCourseSubTypeDTO> getRequestCourseSubTypeList(int roli_no) {
		return sql.selectList(namespace + ".getRequestCourseSubTypeList", roli_no);
	}

	@Override
	public int insertRequestCourseSubType(RequestCourseSubTypeDTO dto) {
		return sql.insert(namespace + ".insertRequestCourseSubType", dto);
	}

	@Override
	public int deleteRequestCourseSubType(int roli_no) {
		return sql.delete(namespace + ".deleteRequestCourseSubType", roli_no);
	}

}
