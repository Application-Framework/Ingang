// 김홍일 2022-06-16 수정
// insertCourseTeacher, deleteCourseTeacher, checkTeacherByM_no 추가

package com.spring.ex.dao;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TeacherDTO;

@Repository
public interface TeacherDAO {
	public TeacherDTO getTeacherInfo(int olt_no);
	public TeacherDTO getTeacherInfoByM_no(int m_no);
	public int insertCourseTeacher(TeacherDTO dto);
	public int deleteCourseTeacher(int olt_no);
	public int checkTeacherByM_no(int m_no);
}
