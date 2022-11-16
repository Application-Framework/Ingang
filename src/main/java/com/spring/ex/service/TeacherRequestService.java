package com.spring.ex.service;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.TeacherRequestDTO;

@Service
public interface TeacherRequestService {
	public int insertTeacherRequest(TeacherRequestDTO dto);
	public int updateTeacherRequest(TeacherRequestDTO dto);
}
