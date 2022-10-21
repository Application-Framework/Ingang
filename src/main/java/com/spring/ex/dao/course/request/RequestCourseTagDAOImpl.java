package com.spring.ex.dao.course.request;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.request.RequestCourseTagDTO;

@Repository
public class RequestCourseTagDAOImpl implements RequestCourseTagDAO{
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.RequestCourseMapper";
	@Override
	public List<RequestCourseTagDTO> getRequestCourseTags(int roli_no) {
		return sqlSession.selectList(namespace + ".getRequestCourseTags", roli_no);
	}
	
	@Override
	public int submitRequestCourseTag(RequestCourseTagDTO dto) {
		return sqlSession.insert(namespace + ".submitRequestCourseTag", dto);
	}

	@Override
	public int deleteRequestCourseTag(int roli_no) {
		return sqlSession.delete(namespace + ".deleteRequestCourseTag", roli_no);
	}
}
