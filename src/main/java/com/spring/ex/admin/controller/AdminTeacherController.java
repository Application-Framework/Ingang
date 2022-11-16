package com.spring.ex.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminTeacherController {
	
	// 강사 대시보드 페이지
	@RequestMapping("admin/teacher")
	public String adminTeacherDashboard(Model model) {
		int todayPendingTeacherRequestCount = 0;
		
		model.addAttribute("todayPendingTeacherRequestCount", todayPendingTeacherRequestCount);
		
		return "admin/teacher/teacher_dashboard";
	}
	
	// 승인 대기중인 강사들 관리 페이지
	@RequestMapping("admin/teacher/pending-teachers")
	public String pendingTeachersManagementPage() {
		
		return "admin/teacher/pending_teachers";
	}
	
	// 강사 상세 페이지
	@RequestMapping("admin/teacher/{olt_no}")
	public String adminTeacherDetailPage(@PathVariable("olt_no") int olt_no) {
		
		
		return "admin/teacher/teacher_detail";
	}
	
	// 강사 추가
	@ResponseBody
	@RequestMapping("admin/teacher/insertTeacher")
	public void insertTeacher() {
		
	}
	
	// 강사 수정
	@ResponseBody
	@RequestMapping("admin/teacher/updateTeacher")
	public void updateTeacher() {
		
	}
	
	// 강사 삭제
	@ResponseBody
	@RequestMapping("admin/teacher/deleteTeacher")
	public void deleteTeacher() {
		
	}
	
	// 강사 승인
	@ResponseBody
	@RequestMapping("admin/teacher/approvalTeacher")
	public void approvalTeacher() {
		
	}
	
	// 강사 거절
	@ResponseBody
	@RequestMapping("admin/teacher/rejectTeacher")
	public void rejectTeacher() {
		
	}
}
