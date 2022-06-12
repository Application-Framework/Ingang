package com.spring.ex.dao.course;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseReplyDTO;

@Repository
public class CourseReplyDAOImpl implements CourseReplyDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.CourseMapper"; 
	
	@Override
	public List<CourseReplyDTO> getCourseReplys(int oli_no) {
		return sqlSession.selectList(namespace + ".getCourseReplys", oli_no);
	}

	@Override
	public int submitReply(CourseReplyDTO dto) {
		return sqlSession.insert(namespace + ".submitReply", dto);
	}
}
