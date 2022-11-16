package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.TeacherRequestDTO;

@Repository
public interface AdminTeacherService {
	// 강사 게시판 가져오기
	public List<Map<String, Object>> getAdminTeacherBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize);
	
	// 강사 목록 총 개수 가져오기
	public int getAdminTeacherTotalCount(String searchCategory, String searchKeyword);
	
	// 승인 대기 강사 게시판 가져오기
	public List<Map<String, Object>> getAdminPendingTeacherBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize);
	
	// 승인 대기 강사 목록 총 개수 가져오기
	public int getAdminPendingTeacherTotalCount(String searchCategory, String searchKeyword);
	
	// 강사 정보 가져오기
	public TeacherDTO getAdminTeacherInfo(int olt_no);
	
	// 승인 대기강사 정보 가져오기
	public TeacherRequestDTO getAdminPendingTeacherInfo(int oltr_no);
	
	// 강사 추가
	public int insertTeacher(TeacherDTO dto);
	
	// 강사 수정
	public int updateTeacher(TeacherDTO dto);
	
	// 강사 삭제
	public int deleteTeacher(int olt_no);
	
	// 승인 대기중인 강사 승인
	public int approvalPendingTeacher(int oltr_no);
	
	// 승인 대기중인 강사 거절
	public int rejectPendingTeacher(int oltr_no, String rejection_message);
}
