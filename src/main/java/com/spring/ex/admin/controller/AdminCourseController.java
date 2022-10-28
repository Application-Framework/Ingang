package com.spring.ex.admin.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.service.CourseService;

@Controller
public class AdminCourseController {
	
	
	@Inject
	private CourseService courseService;
	
	// 강의 관리 대시보드 페이지
	@RequestMapping("/admin/course")
	public String courseDashboard() {
		
		return "admin/course/course-dashboard";
	}
	
	// 승인 대기중인 강의들 관리 페이지
	@RequestMapping("/admin/course/pending-courses")
	public String pendingCourses(HttpServletRequest request, Model model) {
		model.addAttribute("courseRequestList", courseService.getPendingCourseRequestList());
		
		return "admin/course/pending-courses";
	}
	
	@ResponseBody
	@RequestMapping("/approvalCourseRequest")
	public void approvalCourseRequest(HttpServletRequest request) {
		/*MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member.getM_authority() != 1) { 
			System.err.println("회원이 아닌 사용자가 approvalCourseRequest에 접근했습니다.");
			return;
		}*/
		System.out.println("approvalCourseRequest");
		String olr_no = request.getParameter("olr_no");
		courseService.approveCourse(Integer.parseInt(olr_no));
	}
	
	@ResponseBody
	@RequestMapping("/rejectCourseRequest")
	public void rejectCourseRequest(HttpServletRequest request) {
		/*MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member.getM_authority() != 1) { 
			System.err.println("회원이 아닌 사용자가 approvalCourseRequest에 접근했습니다.");
			return;
		}*/
		System.out.println("rejectCourseRequest");
		String olr_no = request.getParameter("olr_no");
		String rejection_message = request.getParameter("rejection_message");
		courseService.rejectCourse(Integer.parseInt(olr_no), rejection_message);
	}
}
