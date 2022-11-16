package com.spring.ex.admin.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.admin.service.AdminTeacherService;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.TeacherRequestDTO;
import com.spring.ex.service.PagingService;

@Controller
public class AdminTeacherController {
	
	@Inject
	private AdminTeacherService adminTeacherService;
	
	private PagingService pagingService;
	
	// 강사 대시보드 페이지
	@RequestMapping("admin/teacher")
	public String adminTeacherDashboard(HttpServletRequest request, Model model) {
		int pendingTotalCount = adminTeacherService.getAdminPendingTeacherTotalCount(null, null);
		String searchCategory = request.getParameter("searchCategory");
		String searchKeyword = request.getParameter("searchKeyword");
		
		int teacherTotalCount = adminTeacherService.getAdminTeacherTotalCount(searchCategory, searchKeyword);
		final int pageSize = 10;
		pagingService = new PagingService(request, teacherTotalCount, pageSize);
		List<Map<String,Object>> teacherBoard =  adminTeacherService.getAdminTeacherBoard(searchCategory, searchKeyword, pagingService.getNowPage(), pageSize);
		
		model.addAttribute("pendingTotalCount", pendingTotalCount);
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("teacherBoard", teacherBoard);
		model.addAttribute("paging", pagingService.getPaging());
		
		return "admin/teacher/teacher_dashboard";
	}
	
	// 승인 대기중인 강사들 관리 페이지
	@RequestMapping("admin/teacher/pending-teachers")
	public String pendingTeachersManagementPage(HttpServletRequest request, Model model) {
		String searchCategory = request.getParameter("searchCategory");
		String searchKeyword = request.getParameter("searchKeyword");
		
		int pendingTotalCount = adminTeacherService.getAdminPendingTeacherTotalCount(searchCategory, searchKeyword);
		final int pageSize = 10;
		pagingService = new PagingService(request, pendingTotalCount, pageSize);
		
		List<Map<String,Object>> pendingBoard = adminTeacherService.getAdminPendingTeacherBoard(searchCategory, searchKeyword, pagingService.getNowPage(), pageSize);
		
		model.addAttribute("pendingTotalCount", pendingTotalCount);
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("pendingBoard", pendingBoard);
		model.addAttribute("paging", pagingService.getPaging());
		
		return "admin/teacher/pending_teachers";
	}
	
	// 강사 상세 페이지
	@RequestMapping("admin/teacher/teacherDetail")
	public String adminTeacherDetailPage(HttpServletRequest request, Model model) {
		int olt_no = Integer.parseInt(request.getParameter("olt_no"));
		TeacherDTO teacher = adminTeacherService.getAdminTeacherInfo(olt_no);
		
		model.addAttribute("teacher", teacher);
		
		return "admin/teacher/teacher_detail";
	}
	
	// 승인 대기중인 강사 상세 페이지
	@RequestMapping("admin/teacher/pendingTeacherDetail")
	public String adminPendingTeacherDetailPage(HttpServletRequest request, Model model) {
		int oltr_no = Integer.parseInt(request.getParameter("oltr_no"));
		TeacherRequestDTO pendingTeacher = adminTeacherService.getAdminPendingTeacherInfo(oltr_no); 
		
		model.addAttribute("pendingTeacher", pendingTeacher);
		
		return "admin/teacher/pending_teacher_detail";
	}
	
	// 강사 추가
	@ResponseBody
	@RequestMapping("admin/teacher/insertTeacher")
	public void insertTeacher(HttpServletRequest request) {
		int m_no = Integer.parseInt(request.getParameter("m_no"));
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		int main_type_no = Integer.parseInt(request.getParameter("main_type_no"));
		String introduction = request.getParameter("introduction");
		String link = request.getParameter("link");
		
		TeacherDTO teacher = new TeacherDTO();
		teacher.setM_no(m_no);
		teacher.setEmail(email);
		teacher.setName(name);
		teacher.setPhone(phone);
		teacher.setMain_type_no(main_type_no);
		teacher.setIntroduction(introduction);
		teacher.setLink(link);
		
		adminTeacherService.insertTeacher(teacher);
		System.out.println("강사 추가 : " + teacher);
	}
	
	// 강사 수정
	@ResponseBody
	@RequestMapping("admin/teacher/updateTeacher")
	public void updateTeacher(HttpServletRequest request) {
		int olt_no = Integer.parseInt(request.getParameter("olt_no"));
		int m_no = Integer.parseInt(request.getParameter("m_no"));
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		int main_type_no = Integer.parseInt(request.getParameter("main_type_no"));
		String introduction = request.getParameter("introduction");
		String link = request.getParameter("link");
		
		TeacherDTO teacher = adminTeacherService.getAdminTeacherInfo(olt_no);
		teacher.setM_no(m_no);
		teacher.setEmail(email);
		teacher.setName(name);
		teacher.setPhone(phone);
		teacher.setMain_type_no(main_type_no);
		teacher.setIntroduction(introduction);
		teacher.setLink(link);
		
		adminTeacherService.updateTeacher(teacher);
		System.out.println("강사 수정 : " + teacher);
	}
	
	// 강사 삭제
	@ResponseBody
	@RequestMapping("admin/teacher/deleteTeacher")
	public void deleteTeacher(HttpServletRequest request) {
		int olt_no = Integer.parseInt(request.getParameter("olt_no"));
		adminTeacherService.deleteTeacher(olt_no);
		System.out.println("강사 삭제 : " + olt_no);
	}
	
	// 강사 승인
	@ResponseBody
	@RequestMapping("admin/teacher/approvalTeacher")
	public void approvalTeacher(HttpServletRequest request) {
		int oltr_no = Integer.parseInt(request.getParameter("oltr_no"));
		adminTeacherService.approvalPendingTeacher(oltr_no);
		System.out.println("강사 승인 : " + oltr_no);
	}
	
	// 강사 거절
	@ResponseBody
	@RequestMapping("admin/teacher/rejectTeacher")
	public void rejectTeacher(HttpServletRequest request) {
		int oltr_no = Integer.parseInt(request.getParameter("oltr_no"));
		String rejection_message = request.getParameter("rejection_message");
		adminTeacherService.rejectPendingTeacher(oltr_no, rejection_message);
		System.out.println("강사 거절 : " + oltr_no + "\n거절 메시지 : " + rejection_message);
	}
}
