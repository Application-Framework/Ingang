package com.spring.ex.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.service.TeacherService;

@Controller
public class TeacherController {
	
	@Inject
	private TeacherService teacherService;
	
	// 지식 공유 안내 페이지
	@RequestMapping("/open-knowledge")
	public String openKnowledge() {
		return "open-knowledge";
	}
	
	// 강사 신청
	public void applicationTeacher(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return;
		}
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String main_type_no = request.getParameter("main_type_no");
		String introduction = request.getParameter("introduction");
		String email = request.getParameter("email");
		
		TeacherDTO teacher = new TeacherDTO();
		teacher.setM_no(memberDTO.getM_no());
		teacher.setEmail(email);
		teacher.setName();
		teacher.setPhone();
		teacher.setMain_type_no();
		teacher.setIntroduction();
		teacher.setLink();
		teacher.setGrade();
		teacherService.insertCourseTeacher(teacher);
	}
}
