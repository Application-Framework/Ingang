package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.MyPageDAO;
import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseReplyDTO;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Inject
	private MyPageDAO dao;
	
	// 강사 자격 확인
	public int checkTeacher(int m_no) throws Exception {
		return dao.checkTeacher(m_no);
	}
	
	// 강사 소개
	@Override
	public List<TeacherDTO> teacherIntro(int m_no) throws Exception {
		return dao.teacherIntro(m_no);
	}
	
	// 강사 정보(강사 소개, 올린 강의, 수강평)
	@Override
	public List<TeacherDTO> checkTeacherInfo(int m_no) throws Exception {
		return dao.checkTeacherInfo(m_no);
	}
	
	// 강사가 올린 강의 갯수
	public int teacherCourseCount(int m_no) throws Exception {
		return dao.teacherCourseCount(m_no);
	}
	
	// 수강평
	public List<CourseReplyDTO> teacherCourseReply(int m_no) throws Exception {
		return dao.teacherCourseReply(m_no);
	}
	
	// 내가 쓴 게시글 목록
	@Override
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception {
		return dao.myPostList(m_no);
	}

}
