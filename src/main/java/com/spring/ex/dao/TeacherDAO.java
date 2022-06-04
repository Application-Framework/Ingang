package com.spring.ex.dao;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TeacherDTO;

@Repository
public interface TeacherDAO {
	public TeacherDTO getTeacherInfo(int olt_no);
	
	public int getOlt_noByM_no(int m_no);
}
