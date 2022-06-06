package com.spring.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.service.CommunityBoardService;

@Controller
public class MyPageController {
	
	@Inject
	CommunityBoardService cbService;
	
	@RequestMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
	@RequestMapping("/courses_history")
	public String coursesHistory() {
		return "/mypage/courses_history";
	}
	
	@RequestMapping("/notes_history")
	public String notesHistory() {
		return "/mypage/notes_history";
	}
	
	@RequestMapping("/my_course")
	public String myCourse() {
		return "/mypage/my_course";
	}
	
	@RequestMapping("/my_note")
	public String myNote() {
		return "/mypage/my_note";
	}
	
	@RequestMapping(value = "/my_post", method = RequestMethod.GET)
	public String myPost(HttpServletRequest request, Model model) throws Exception {
		
		List<CommunityBoardDTO> list = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		//list = cbService.myPostList(m_no);
		
		model.addAttribute("cbList", list);
		
		return "/mypage/my_post";
	}
}
