package com.spring.ex.service;

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
}