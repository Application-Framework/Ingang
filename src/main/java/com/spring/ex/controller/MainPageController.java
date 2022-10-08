package com.spring.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.PurchaseCourseDTO;
import com.spring.ex.dto.TagDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseLikeDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.CourseBestService;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.DropBoxService;
import com.spring.ex.service.LiveCourseReplyService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.NoteBestService;
import com.spring.ex.service.TagService;

@Controller
public class MainPageController {
	
	@Inject
	private CourseService courseService;
	
	@Inject
	private CourseBestService courseBestService; 
	
	@Inject
	private NoteBestService noteBestService; 
	
	@Inject
	TagService tagService;
	
	@Inject
	LiveCourseReplyService liveReplyService;
	
	@Inject
	MemberService memberService;
	
	@Inject
	DropBoxService dropService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(HttpServletRequest request, Model model, String tag, String courses, String notes) throws Exception {
		
		// 실시간 댓글 목록
		List<CourseReplyDTO> courseReplylist = null;
		courseReplylist = liveReplyService.live_replyList();
		
		// 태그 검색어 순위
		List<TagDTO> taglist = null;
		
		// 이 주의 강의 목록
		List<CourseDTO> crblist = null;
		crblist = courseBestService.thisweek_bestCourseList();
		
		// 신규 강의 목록
		List<CourseDTO> newlist = null;
		newlist = courseBestService.thisweek_newCourseList();
		
		// 이 주의 강의 목록
		List<NoteDTO> ntblist = null;
		ntblist = noteBestService.thisweek_bestNoteList();
				
		HttpSession session = request.getSession();
		TagDTO tagDTO = (TagDTO)session.getAttribute("tag");
		
		taglist = tagService.tagRanking(tagDTO);
		
		// 드롭박스 관심 강의 목록
		List<PurchaseCourseDTO> dropInterestedList = null;
		Integer dropInterestedListCount = null;
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
		
		if(memberDTO != null) {
			Integer m_no = memberDTO.getM_no();
			
			dropInterestedList = dropService.myInterestedCourseDropBox(m_no);	//관심 강의 목록
			dropInterestedListCount = dropService.myInterestedCourseCountDropBox(m_no);	//관심 강의 갯수
			
			session.setAttribute("dropInterList", dropInterestedList);
			session.setAttribute("dropInterListCnt", dropInterestedListCount);
		} else {
			session.invalidate();
		}
		
		courses = "courses";
		notes = "notes";
		request.setAttribute("courses", courses);
		request.setAttribute("notes", notes);
		model.addAttribute("courseReplylist", courseReplylist);
		model.addAttribute("tagList", taglist);
		model.addAttribute("crbList", crblist);
		model.addAttribute("newList", newlist);
		model.addAttribute("ntbList", ntblist);
		model.addAttribute("courseService", courseService);
		
		return "index";
	}
	
	@RequestMapping("/mainSearch")
	public String redirect(HttpServletRequest request, Model model) throws Exception {
		String keyword = request.getParameter("keyword");
		String select1 = request.getParameter("select1");
		String urlMove;
		
		System.out.println(keyword);
		System.out.println(select1);
		if(select1.equals("courses")) {
			urlMove = "redirect:/courses?";
			System.out.println(urlMove);
		} else {
			urlMove = "redirect:/notes?";
		}
		
		model.addAttribute("keyword", keyword);
		
		return urlMove;
	}
}
