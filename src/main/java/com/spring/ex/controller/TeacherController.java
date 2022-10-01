package com.spring.ex.controller;

import java.sql.Date;

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
	
	// 강사 지원
	@RequestMapping("/applyForTeacher")
	public String applyForTeacher(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "redirect://";
		}
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String main_type_no = request.getParameter("main_type_no");
		String introduction = request.getParameter("introduction");
		String link = request.getParameter("link");
		
		if(email == null || name == null || phone == null || main_type_no == null || introduction == null || link == null) {
			System.out.println("강사 신청 필드에 빈칸이 있습니다.");
			return "error";
		}
		
		TeacherDTO teacher = new TeacherDTO();
		teacher.setM_no(memberDTO.getM_no());
		teacher.setEmail(email);
		teacher.setName(name);
		teacher.setPhone(phone);
		teacher.setMain_type_no(Integer.parseInt(main_type_no));
		teacher.setIntroduction(introduction);
		teacher.setLink(link);
		teacher.setGrade(0);
		teacher.setReg_date(new Date(System.currentTimeMillis()));
		teacherService.insertCourseTeacher(teacher);
		return "mypage";
	}
	
	
}
