package com.spring.ex.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.TeacherDAO;
import com.spring.ex.dto.TeacherDTO;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Inject
	private TeacherDAO teacherDAO;
	
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
}
