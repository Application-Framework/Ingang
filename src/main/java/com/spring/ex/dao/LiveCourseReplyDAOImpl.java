package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CourseReplyDTO;

@Repository
public class LiveCourseReplyDAOImpl implements LiveCourseReplyDAO {

	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.mappers.LiveCourseReplyMapper";
	
	// 실시간 댓글
	@Override
	public List<CourseReplyDTO> live_replyList() throws Exception {
		return sqlSession.selectList(namespace + ".live_replyList");
	}
}
