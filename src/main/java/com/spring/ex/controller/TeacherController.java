package com.spring.ex.controller;

import java.sql.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.service.TeacherService;
import com.spring.ex.service.TypeService;

@Controller
public class TeacherController {
	
	@Inject
	private TeacherService teacherService;
	
	@Inject
	private TypeService typeService;
	
	// 지식 공유 안내 페이지
	@RequestMapping("/open-knowledge")
	public String openKnowledge(HttpServletRequest request, Model model) {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		
		if(member != null && teacherService.getTeacherInfoByM_no(member.getM_no()) == null) {
			model.addAttribute("isTeacher", false);
		}
		
		return "open-knowledge";
	}
	
	// 강사 지원
	@RequestMapping("/submitOpenKnowledgeApplication")
	public String submitOpenKnowledgeApplication(HttpServletRequest request) {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return "redirect:/loginPageView";
		}
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String main_type_no = request.getParameter("hope_main_category");
		String introduction = request.getParameter("introduction");
		String link = request.getParameter("link");
		
		if(email == null || name == null || phone == null || main_type_no == null || introduction == null || link == null) {
			System.out.println("강사 신청 필드에 빈칸이 있습니다.");
			return "error";
		}
		
		if(teacherService.getTeacherInfoByM_no(member.getM_no()) != null) {
			System.err.println("강사 신청 기록이 있습니다.");
			return "error";
		}
		
		TeacherDTO teacher = new TeacherDTO();
		teacher.setM_no(member.getM_no());
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
	
	// 지식 공유 안내 양식 보내기
	@RequestMapping(value="/getOpenKnowledgeGuideForm", method=RequestMethod.POST)
	public String getOpenKnowledgeGuideForm(HttpServletRequest request, Model model) {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		model.addAttribute("member", member);
		
		return "modal/open_knowledge_guide_form";
	}
	
	// 지식 공유 신청서 양식 보내기
	@RequestMapping(value="/getOpenKnowledgeApplicationForm", method=RequestMethod.POST)
	public String getOpenKnowledgeApplicationForm(HttpServletRequest request, Model model) {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		System.out.println(typeService.getMainTypeList()); 
		model.addAttribute("member", member);
		model.addAttribute("mainCategoryList", typeService.getMainTypeList());
		return "modal/open_knowledge_application_form";
	}
}
