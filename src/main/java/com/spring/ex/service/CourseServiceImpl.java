package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.CourseDAO;
import com.spring.ex.dao.CourseLikeDAO;
import com.spring.ex.dao.CourseReplyDAO;
import com.spring.ex.dao.CourseTagDAO;
import com.spring.ex.dao.CourseVideoDAO;
import com.spring.ex.dao.TeacherDAO;
import com.spring.ex.dto.CourseDTO;
import com.spring.ex.dto.CourseReplyDTO;
import com.spring.ex.dto.CourseTagDTO;
import com.spring.ex.dto.CourseVideoDTO;
import com.spring.ex.dto.TeacherDTO;

@Service
public class CourseServiceImpl implements CourseService {

	@Inject
	private CourseDAO courseDAO;
	
	@Inject
	private CourseReplyDAO courseReplyDAO;
	
	@Inject
	private CourseTagDAO courseTagDAO;
	
	@Inject
	private TeacherDAO teacherDAO;
	
	@Inject
	private CourseLikeDAO courseLikeDAO;
	
	@Inject
	private CourseVideoDAO courseVideoDAO;
	
	@Override
	public List<HashMap<String, Object>> getCoursePage(HashMap<String, Object> map) {
		return courseDAO.getCoursePage(map);
	}

	@Override
	public int getCourseTotalCount(HashMap<String, Object> map) {
		return courseDAO.getCourseTotalCount(map);
	}

	@Override
	public CourseDTO getCourseDetail(int pageNo) {
		return courseDAO.getCourseDetail(pageNo);
	}

	@Override
	public List<CourseReplyDTO> getCourseReplys(int oli_no) {
		return courseReplyDAO.getCourseReplys(oli_no);
	}

	@Override
	public List<CourseTagDTO> getCourseTags(int oli_no) {
		return courseTagDAO.getCourseTags(oli_no);
	}

	@Override
	public TeacherDTO getTeacherInfo(int olt_no) {
		return teacherDAO.getTeacherInfo(olt_no);
	}

	@Override
	public int getCourseLikeCount(int oli_no) {
		return courseLikeDAO.getCourseLikeCount(oli_no);
	}

	@Override
	public int insertCourseLike(int oli_no, int m_no) {
		return courseLikeDAO.insertCourseLike(oli_no, m_no);
	}

	@Override
	public int deleteCourseLike(int oli_no, int m_no) {
		return courseLikeDAO.deleteCourseLike(oli_no, m_no);
	}

	@Override
	public int existCourseLike(int oli_no, int m_no) {
		return courseLikeDAO.existCourseLike(oli_no, m_no);
	}

	@Override
	public int submitCourse(CourseDTO dto) {
		return courseDAO.submitCourse(dto);
	}

	@Override
	public int submitReply(CourseReplyDTO dto) {
		return courseReplyDAO.submitReply(dto);
	}

	@Override
	public int submitTag(CourseTagDTO dto) {
		return courseTagDAO.submitTag(dto);
	}

	@Override
	public int submitCourseVideo(CourseVideoDTO dto) {
		return courseVideoDAO.submitCourseVideo(dto);
	}

	@Override
	public int getOlt_noByM_no(int m_no) {
		return teacherDAO.getOlt_noByM_no(m_no);
	}
}
