package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.MemberDAO;
import com.spring.ex.dao.TeacherDAO;
import com.spring.ex.dao.course.CourseDAO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Inject
	private TeacherDAO teacherDAO;
	
	@Inject
	private CourseDAO courseDAO;
	
	@Inject
	private MemberDAO memberDAO;
	
	@Override
	public TeacherDTO getTeacherInfo(int olt_no) {
		return teacherDAO.getTeacherInfo(olt_no);
	}
	
	@Override
	public TeacherDTO getTeacherInfoByM_no(int m_no) {
		return teacherDAO.getTeacherInfoByM_no(m_no);
	}
	
	@Override
	public int insertCourseTeacher(TeacherDTO dto) {
		return teacherDAO.insertCourseTeacher(dto);
	}

	@Override
	public int updateCourseTeacher(TeacherDTO dto) {
		return teacherDAO.updateCourseTeacher(dto);
	}
	
	@Override
	public int deleteCourseTeacher(int olt_no) {
		return teacherDAO.deleteCourseTeacher(olt_no);
	}

	@Override
	public int checkTeacherByM_no(int m_no) {
		return teacherDAO.checkTeacherByM_no(m_no);
	}

	@Override
	public List<TeacherDTO> getTeacherList() {
		return teacherDAO.getTeacherList();
	}

	@Override
	public boolean isTeacherOfThisCourse(int oli_no, int m_no) {
		MemberDTO member = memberDAO.getMemberByM_no(m_no);
		if(member == null) {
			return false;
		}
		CourseDTO course = courseDAO.getCourseDetail(oli_no);
		if(course == null) {
			return false;
		}
		
		TeacherDTO teacher = teacherDAO.getTeacherInfoByM_no(m_no);
		if(teacher == null) {
			return false;
		}
		
		if(teacher.getOlt_no() == course.getOlt_no())
			return true;
		else return false;
	}
}
