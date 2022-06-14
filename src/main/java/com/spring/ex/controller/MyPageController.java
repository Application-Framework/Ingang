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
import com.spring.ex.dto.PurchaseCourseDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.OrderHistoryService;

@Controller
public class MyPageController {
	
	@Inject
	CommunityBoardService cbService;
	
	@Inject
	OrderHistoryService orderHistoryService;
	
	
	@RequestMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
	@RequestMapping("/courses_history")
	public String coursesHistory(HttpServletRequest request, Model model) throws Exception {
		
		List<PurchaseCourseDTO> purChaseList = null;
		List<PurchaseCourseDTO> interestList = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		purChaseList = orderHistoryService.myPurchaseCourseList(m_no);
		interestList = orderHistoryService.myInterestCourseList(m_no);
		
		model.addAttribute("ocList", purChaseList);
		model.addAttribute("itList", interestList);
		
		return "/mypage/courses_history";
	}
	
	@RequestMapping("/notes_history")
	public String notesHistory() throws Exception {
		
		
		
		return "/mypage/notes_history";
	}
	
	@RequestMapping(value = "/my_course", method = RequestMethod.GET)
	public String myCourse(HttpServletRequest request, Model model) throws Exception {
		
		String keyword = request.getParameter("keyword");
		keyword = (keyword == null) ? "" : keyword;
		
		String keywordParam = (keyword != "") ? "keyword="+keyword+"&" : "";
		
		List<PurchaseCourseDTO> list = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		list = orderHistoryService.searchMyPurcaseCourses(m_no, keyword);
		
		model.addAttribute("ocList", list);
		model.addAttribute("keyword", keyword);
		model.addAttribute("keywordParam", keywordParam);
		
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
		
		list = cbService.myPostList(m_no);
		
		model.addAttribute("cbList", list);
		
		return "/mypage/my_post";
	}
}
