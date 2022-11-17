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
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.FileService;
import com.spring.ex.service.HistoryOrderService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.MyPageService;

@Controller
public class MyPageController {
	
	@Inject
	MemberService memberService;
	
	@Inject
	MyPageService myPageService;
	
	@Inject
	HistoryOrderService historyOrderService;
	
	@Inject
	private FileService fileUploadService;
	
	@Inject
	private CourseService courseService; 
	
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		
		Integer m_no = (Integer)session.getAttribute("m_no");
		
		int checkTeacher = myPageService.checkTeacher(m_no); 
		
		System.out.println("MyPageController.mypage() : " + checkTeacher);
		
		if(checkTeacher == 1) {
			System.out.println("강사 입니다");
			
			List<TeacherDTO> listTeacherIntro = null;
			List<TeacherDTO> listTeacher = null;
			List<CourseReplyDTO> listTeacherCourseReply = null;
			
			System.out.println("강사 정보 : " + myPageService.checkTeacherInfo(m_no));
			listTeacher = myPageService.checkTeacherInfo(m_no);
			listTeacherIntro = myPageService.teacherIntro(m_no);
			listTeacherCourseReply = myPageService.teacherCourseReply(m_no);
			
			System.out.println("수강평 : " + listTeacherCourseReply);
			
			int teacherCourseCount = myPageService.teacherCourseCount(m_no);
			
			model.addAttribute("listTeacherIntro", listTeacherIntro);
			model.addAttribute("listTeacher", listTeacher);
			model.addAttribute("teacherCourseCount", teacherCourseCount);
			model.addAttribute("listTeacherCourseReply", listTeacherCourseReply);
			
		} else {
			System.out.println("일반사용자 입니다");
		}
		
		
		int courseCnt = memberService.countMyCourse(m_no);
		int noteCnt = memberService.countMyNote(m_no);
		int postCnt = memberService.countMyPost(m_no);
		
		model.addAttribute("checkTeacher", checkTeacher);
		model.addAttribute("countMyCourse", courseCnt);
		model.addAttribute("countMyNote", noteCnt);
		model.addAttribute("countMyPost", postCnt);
		model.addAttribute("checkTeacher", checkTeacher);
		
		model.addAttribute("courseService", courseService);
		
		return "mypage";
	}
	
	// 회원 비밀번호 확인 get
	@RequestMapping(value = "/my_checkPW", method = RequestMethod.GET)
	public String getCheckMyPW() {
		return "/mypage/my_checkPW";
	}
	
	// 회원 정보수정 post
	@RequestMapping(value = "/my_checkPW", method = RequestMethod.POST)
	public String postCheckMyPW(HttpSession session, HttpServletRequest request) throws Exception {
		String m_pw = request.getParameter("m_pw");
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		memberDTO.getM_id();
		memberDTO.setM_pw(m_pw);
		
		System.out.println("아이디 : " + memberDTO.getM_id());
		System.out.println("비밀빈호 : " + memberDTO.getM_pw());
		MemberDTO pwCheck = memberService.pwCheck(memberDTO);

		int result = 0;
		
		//비밀번호 확인
		if (pwCheck != null) {
			result = 1;
		} else {
			result = 0;
		}

		if (result == 1) {
			return "/mypage/my_update";
		} else {
			return "redirect:/my_checkPW";
		}
		
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
		
		if(!profile.isEmpty()) { 
			img_path = fileUploadService.insertFileToLocalAndServer(profile, "/img/profile/uploaded_images");
		}
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setM_id(m_id);
		memberDTO.setM_pw(m_pw);
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
		
		List<HistoryOrderLectureDTO> purChaseList = null;
		List<HistoryOrderLectureDTO> interestList = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		purChaseList = historyOrderService.myPurchaseCourseList(m_no);
		interestList = historyOrderService.myInterestCourseList(m_no);
		
		model.addAttribute("ocList", purChaseList);
		model.addAttribute("itList", interestList);
		
		return "/mypage/courses_history";
	}
	
	@RequestMapping("/notes_history")
	public String notesHistory(HttpServletRequest request, Model model) throws Exception {
		
		List<HistoryOrderNoteDTO> purChaseList = null;
		List<HistoryOrderNoteDTO> interestList = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		purChaseList = historyOrderService.myPurchaseNoteList(m_no);
		interestList = historyOrderService.myInterestNoteList(m_no);
		
		model.addAttribute("ocList", purChaseList);
		model.addAttribute("itList", interestList);
		
		return "/mypage/notes_history";
	}
	
	@RequestMapping(value = "/my_course", method = RequestMethod.GET)
	public String myCourse(HttpServletRequest request, Model model) throws Exception {
		
		String keyword = request.getParameter("keyword");
		keyword = (keyword == null) ? "" : keyword;
		
		String keywordParam = (keyword != "") ? "keyword="+keyword+"&" : "";
		
		List<HistoryOrderLectureDTO> list = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		list = historyOrderService.searchMyPurcaseCourses(m_no, keyword);
		
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
		
		List<HistoryOrderNoteDTO> list = null;
		
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		Integer m_no = memberDTO.getM_no();
		
		list = historyOrderService.myPurchaseNoteList(m_no);
		
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
