package com.spring.ex.dao;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TeacherRequestDTO;

@Repository
public interface TeacherRequestDAO {
	public int insertTeacherRequest(TeacherRequestDTO dto);
	public int updateTeacherRequest(TeacherRequestDTO dto);
}
