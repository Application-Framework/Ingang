package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.TeacherDTO;

@Service
public interface TeacherService {
	public TeacherDTO getTeacherInfo(int olt_no);
	public TeacherDTO getTeacherInfoByM_no(int m_no);
	public int insertCourseTeacher(TeacherDTO dto);
	public int updateCourseTeacher(TeacherDTO dto);
	public int deleteCourseTeacher(int olt_no);
	public int checkTeacherByM_no(int m_no);
	public List<TeacherDTO> getTeacherList();
	public boolean isTeacherOfThisCourse(int oli_no, int m_no);
}
