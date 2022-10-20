package com.spring.ex.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminCourseController {
	
	// 강의 관리 대시보드 페이지
	@RequestMapping("/admin/course")
	public String courseDashboard() {
		
		return "admin/course/course-dashboard";
	}
	
	// 승인 대기중인 강의들 관리 페이지
	@RequestMapping("/admin/course/pending-courses")
	public String pendingCourses() {
		
		return "admin/course/pending-courses";
	}
}
