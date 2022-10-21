package com.spring.ex.dao.course.request;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.request.RequestCourseVideoDTO;

@Repository
public class RequestCourseVideoDAOImpl implements RequestCourseVideoDAO {

	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.RequestCourseMapper";
	
	@Override
	public int submitRequestCourseVideo(RequestCourseVideoDTO dto) {
		return sqlSession.insert(namespace + ".submitRequestCourseVideo", dto);
	}

	@Override
	public List<RequestCourseVideoDTO> getRequestCourseVideoList(int roli_no) {
		return sqlSession.selectList(namespace + ".getRequestCourseVideoList", roli_no);
	}

	@Override
	public RequestCourseVideoDTO getRequestCourseVideo(int rolv_no) {
		return sqlSession.selectOne(namespace + ".getRequestCourseVideo", rolv_no);
	}

	@Override
	public int deleteRequestCourseVideo(int roli_no) {
		return sqlSession.delete(namespace + ".deleteRequestCourseVideo", roli_no);
	}

}
