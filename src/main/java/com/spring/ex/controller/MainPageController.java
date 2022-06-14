package com.spring.ex.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.CourseDTO;
import com.spring.ex.dto.CourseReplyDTO;
import com.spring.ex.dto.TagDTO;
import com.spring.ex.service.CourseBestService;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.LiveCourseReplyService;
import com.spring.ex.service.PagingService;
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
	public String mainPage(HttpServletRequest request, Model model, String tag) throws Exception {
		
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
		
		model.addAttribute("courseReplylist", courseReplylist);
		model.addAttribute("tagList", taglist);
		model.addAttribute("crbList", crblist);
		model.addAttribute("newList", newlist);
		
		return "index";
	}
}
