package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.spring.ex.admin.dao.AdminTeacherDAO;
import com.spring.ex.dao.TeacherRequestDAO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.TeacherRequestDTO;

@Repository
public class AdminTeacherServiceImpl implements AdminTeacherService {
	
	@Inject
	private AdminTeacherDAO adminTeacherDAO;

	@Inject
	private TeacherRequestDAO teacherRequestDAO;
	
	@Override
	public List<Map<String, Object>> getAdminTeacherBoard(String searchCategory, String searchKeyword, int nowPage,
			int pageSize) {
		return adminTeacherDAO.getAdminTeacherBoard(searchCategory, searchKeyword, nowPage, pageSize);
	}

	@Override
	public int getAdminTeacherTotalCount(String searchCategory, String searchKeyword) {
		return adminTeacherDAO.getAdminTeacherTotalCount(searchCategory, searchKeyword);
	}

	@Override
	public List<Map<String, Object>> getAdminPendingTeacherBoard(String searchCategory, String searchKeyword,
			int nowPage, int pageSize) {
		return adminTeacherDAO.getAdminPendingTeacherBoard(searchCategory, searchKeyword, nowPage, pageSize);
	}

	@Override
	public int getAdminPendingTeacherTotalCount(String searchCategory, String searchKeyword) {
		return adminTeacherDAO.getAdminPendingTeacherTotalCount(searchCategory, searchKeyword);
	}

	@Override
	public TeacherDTO getAdminTeacherInfo(int olt_no) {
		return adminTeacherDAO.getAdminTeacherInfo(olt_no);
	}

	@Override
	public TeacherRequestDTO getAdminPendingTeacherInfo(int oltr_no) {
		return adminTeacherDAO.getAdminPendingTeacherInfo(oltr_no);
	}

	@Override
	public int insertTeacher(TeacherDTO dto) {
		return adminTeacherDAO.insertTeacher(dto);
	}

	@Override
	public int updateTeacher(TeacherDTO dto) {
		return adminTeacherDAO.updateTeacher(dto);
	}

	@Override
	public int deleteTeacher(int olt_no) {
		return adminTeacherDAO.deleteTeacher(olt_no);
	}

	@Override
	public int approvalPendingTeacher(int oltr_no) {
		TeacherRequestDTO pendingTeacher = getAdminPendingTeacherInfo(oltr_no);
		pendingTeacher.setApproval_status(1);
		
		TeacherDTO teacher = new TeacherDTO();
		teacher.setM_no(pendingTeacher.getM_no());
		teacher.setEmail(pendingTeacher.getEmail());
		teacher.setName(pendingTeacher.getName());
		teacher.setPhone(pendingTeacher.getPhone());
		teacher.setMain_type_no(pendingTeacher.getMain_type_no());
		teacher.setIntroduction(pendingTeacher.getIntroduction());
		teacher.setLink(pendingTeacher.getLink());
		
		insertTeacher(teacher);
		teacherRequestDAO.updateTeacherRequest(pendingTeacher);
		System.out.println("강사 승인 : " + teacher);
		return 1;
	}

	@Override
	public int rejectPendingTeacher(int oltr_no, String rejection_message) {
		TeacherRequestDTO pendingTeacher = getAdminPendingTeacherInfo(oltr_no);
		pendingTeacher.setApproval_status(-1);
		pendingTeacher.setRejection_message(rejection_message);
		teacherRequestDAO.updateTeacherRequest(pendingTeacher);
		System.out.println("강사 승인 거절 : " + pendingTeacher);
		return 1;
	}  
}
