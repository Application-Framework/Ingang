package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseReplyDTO;

@Repository
public interface MyPageDAO {
	
	// 강사 자격 확인
	public int checkTeacher(int m_no) throws Exception;
	
	// 강사 소개
	public List<TeacherDTO> teacherIntro(int m_no) throws Exception;
	
	// 강사 정보(올린 강의)
	public List<TeacherDTO> checkTeacherInfo(int m_no) throws Exception;
	
	// 강사가 올린 강의 갯수
	public int teacherCourseCount(int m_no) throws Exception;
	
	// 강사 정보(수강평)
	public List<CourseReplyDTO> teacherCourseReply(int m_no) throws Exception;

	// 내가 쓴 게시글 목록
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception;
	
}
