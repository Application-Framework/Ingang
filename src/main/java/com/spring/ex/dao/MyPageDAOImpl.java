package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseReplyDTO;

@Repository
public class MyPageDAOImpl implements MyPageDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.myPageMapper";
	
	// 강사 자격 확인
	public int checkTeacher(int m_no) throws Exception {
		return sqlSession.selectOne(namespace + ".checkTeacher", m_no);
	}
	
	// 강사 소개
	@Override
	public List<TeacherDTO> teacherIntro(int m_no) throws Exception {
		return sqlSession.selectList(namespace + ".teacherIntro", m_no);
	}
	
	// 강사 정보(강사 소개, 올린 강의, 수강평)
	@Override
	public List<TeacherDTO> checkTeacherInfo(int m_no) throws Exception {
		return sqlSession.selectList(namespace + ".checkTeacherInfo", m_no);
	}
	
	// 강사가 올린 강의 갯수
	public int teacherCourseCount(int m_no) throws Exception {
		return sqlSession.selectOne(namespace + ".teacherCourseCount", m_no);
	}
	
	// 수강평
	public List<CourseReplyDTO> teacherCourseReply(int m_no) throws Exception {
		return sqlSession.selectList(namespace + ".teacherCourseReply", m_no);
	}
	
	// 내가 쓴 게시글 목록
	@Override
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myPostList", m_no);
	}

}
