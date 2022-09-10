package com.spring.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.PurchaseCourseDTO;
import com.spring.ex.dto.PurchaseNoteDTO;
import com.spring.ex.service.FileService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.MyPageService;
import com.spring.ex.service.OrderHistoryService;

@Controller
public class MyPageController {
	
	@Inject
	MemberService memberService;
	
	@Inject
	MyPageService myPageService;
	
	@Inject
	OrderHistoryService orderHistoryService;
	
	@Inject
	private FileService fileUploadService;
	
	@RequestMapping("/mypage" )
	public String mypage(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		
		Integer m_no = (Integer)session.getAttribute("m_no");
		
		int courseCnt = memberService.countMyCourse(m_no);
		int noteCnt = memberService.countMyNote(m_no);
		int postCnt = memberService.countMyPost(m_no);
		
		model.addAttribute("countMyCourse", courseCnt);
		model.addAttribute("countMyNote", noteCnt);
		model.addAttribute("countMyPost", postCnt);
		
		return "mypage";
	}
	
	// 회원 정보수정 get
	@RequestMapping(value = "/my_update", method = RequestMethod.GET)
	public String getMyPageUpdate() {
		return "/mypage/my_update";
	}
	
	// 회원 정보수정 post
	@RequestMapping(value = "/my_update", method = RequestMethod.POST)
	public String postMyPageUpdate(HttpSession session, HttpServletRequest request, @RequestParam("profile") MultipartFile profile) throws Exception {
		
		String img_path = null;
		String m_id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		String m_phone = request.getParameter("m_phone");
		
		if(!profile.isEmpty()) { 
			img_path = fileUploadService.uploadFile(profile, "/img/profile/uploaded_images");
		}
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setM_id(m_id);
		memberDTO.setM_pw(m_pw);
		memberDTO.setM_phone(m_phone);
		memberDTO.setImg_path(img_path);
		
		// update 후 session 만료
		memberService.update(memberDTO);
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원 탈퇴 get
	@RequestMapping(value = "/my_delete", method = RequestMethod.GET)
	public String getMyPageDelete() {
		return "/mypage/my_delete";
	}
	
	// 회원 탈퇴 post
	@RequestMapping(value = "/my_delete", method = RequestMethod.POST)
	public String postMyPageDelete(HttpSession session, HttpServletRequest request) throws Exception {
		
		String m_id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		
		System.out.println("id : " + m_id);
		System.out.println("pw : " + m_pw);
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setM_id(m_id);
		memberDTO.setM_pw(m_pw);
		
		memberService.delete(memberDTO);
		session.invalidate();
		
		return "redirect:/";
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
	public String notesHistory(HttpServletRequest request, Model model) throws Exception {
		
		List<PurchaseNoteDTO> purChaseList = null;
		List<PurchaseNoteDTO> interestList = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		purChaseList = orderHistoryService.myPurchaseNoteList(m_no);
		interestList = orderHistoryService.myInterestNoteList(m_no);
		
		model.addAttribute("ocList", purChaseList);
		model.addAttribute("itList", interestList);
		
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
	
	@RequestMapping(value = "/my_note", method = RequestMethod.GET)
	public String myNote(HttpServletRequest request, Model model) throws Exception {
		
		String keyword = request.getParameter("keyword");
		keyword = (keyword == null) ? "" : keyword;
		
		String keywordParam = (keyword != "") ? "keyword="+keyword+"&" : "";
		
		List<PurchaseNoteDTO> list = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		list = orderHistoryService.myPurchaseNoteList(m_no);
		
		model.addAttribute("ocList", list);
		model.addAttribute("keyword", keyword);
		model.addAttribute("keywordParam", keywordParam);
		model.addAttribute("memberService", memberService);
		
		return "/mypage/my_note";
	}
	
	@RequestMapping(value = "/my_post", method = RequestMethod.GET)
	public String myPost(HttpServletRequest request, Model model) throws Exception {
		
		List<CommunityBoardDTO> list = null;
		
		HttpSession session = request.getSession(); 
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		list = myPageService.myPostList(m_no); 
		
		System.out.println("리스트 : " + list);
		 
		model.addAttribute("cbList", list);
		
		return "/mypage/my_post";
	}
}
