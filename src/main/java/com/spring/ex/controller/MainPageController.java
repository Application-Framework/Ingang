package com.spring.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.TagDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.service.CourseBestService;
import com.spring.ex.service.LiveCourseReplyService;
import com.spring.ex.service.TagService;

@Controller
public class MainPageController {
	
	@Inject
	private CourseBestService courseBestService; 
	
	@Inject
	TagService tagService;
	
	@Inject
	LiveCourseReplyService liveReplyService;
	
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
				
		HttpSession session = request.getSession();
		TagDTO tagDTO = (TagDTO)session.getAttribute("tag");
		
		taglist = tagService.tagRanking(tagDTO);
		
		courses = "courses";
		notes = "notes";
		request.setAttribute("courses", courses);
		request.setAttribute("notes", notes);
		model.addAttribute("courseReplylist", courseReplylist);
		model.addAttribute("tagList", taglist);
		model.addAttribute("crbList", crblist);
		model.addAttribute("newList", newlist);
		
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
