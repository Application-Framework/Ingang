package com.spring.ex.service;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.TeacherRequestDAO;
import com.spring.ex.dto.TeacherRequestDTO;

@Service
public class TeacherRequestServiceImpl implements TeacherRequestService {
	private TeacherRequestDAO teacherRequestDAO;

	@Override
	public int insertTeacherRequest(TeacherRequestDTO dto) {
		return teacherRequestDAO.insertTeacherRequest(dto);
	}

	@Override
	public int updateTeacherRequest(TeacherRequestDTO dto) {
		return teacherRequestDAO.updateTeacherRequest(dto);
	}
}
