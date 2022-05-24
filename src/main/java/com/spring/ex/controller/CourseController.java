package com.spring.ex.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.dto.CourseDTO;
import com.spring.ex.dto.CourseReplyDTO;
import com.spring.ex.dto.CourseTagDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.PagingService;

@Controller
public class CourseController {
	private PagingService pagingService;
	
	@Inject
	private CourseService courseService; 
	
	@Inject
	private MemberService memberService;
	
	void showCourses(HttpServletRequest request, Model model, String tag) {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order");
				
		System.out.println(order);
		keyword = (keyword == null) ? "" : keyword;
		order = (order == null) ? "" : order;
		
		String keywordParam = (keyword != "") ? "keyword="+keyword+"&" : "";
		String orderParam = (order != "") ? "order="+order+"&" : "";
		
		final int pageSize = 12;
		
		HashMap<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("keyword", keyword);
		countMap.put("tag", tag);
		
		int totalCount = courseService.getCourseTotalCount(countMap);
		
		pagingService = new PagingService(request, totalCount, pageSize);
		
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("page", pagingService.getNowPage());
		pageMap.put("pageSize", pageSize);
		pageMap.put("order", order);
		pageMap.put("keyword", keyword);
		pageMap.put("tag", tag);
		
		model.addAttribute("paging", pagingService.getPaging()); 
		model.addAttribute("clist", courseService.getCoursePage(pageMap));
		model.addAttribute("nowURL", request.getServletPath());
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);
		model.addAttribute("keywordParam", keywordParam);
		model.addAttribute("orderParam", orderParam);
		
		System.out.println("pageNo : " + pagingService.getPaging().getPageNo());
	}
	
	@RequestMapping("/courses")
	public String courses(HttpServletRequest request, Model model) {
		showCourses(request, model, null);
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/web-dev")
	public String coursesWebDev(HttpServletRequest request, Model model) {
		showCourses(request, model, "웹 개발");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/front-end")
	public String coursesFrontEnd(HttpServletRequest request, Model model) {
		showCourses(request, model, "프론트엔드");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/back-end")
	public String coursesBackEnd(HttpServletRequest request, Model model) {
		showCourses(request, model, "백엔드");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/programming-lang")
	public String coursesProgrammingLang(HttpServletRequest request, Model model) {
		showCourses(request, model, "프로그래밍 언어");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/database-dev")
	public String coursesDatabaseDev(HttpServletRequest request, Model model) {
		showCourses(request, model, "데이터베이스");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/algorithm")
	public String coursesAlgorithm(HttpServletRequest request, Model model) {
		showCourses(request, model, "알고리즘");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/mobile-app")
	public String coursesMobileApp(HttpServletRequest request, Model model) {
		showCourses(request, model, "모바일 앱 개발");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/artificial-intelligence")
	public String coursesArtificialIntelligence(HttpServletRequest request, Model model) {
		showCourses(request, model, "AI");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/security")
	public String coursesSecurity(HttpServletRequest request, Model model) {
		showCourses(request, model, "보안");
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/{pageNo}")
	public String courses_detail(Model model, HttpServletRequest request, @PathVariable int pageNo) throws Exception {
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		TeacherDTO teachertDTO = courseService.getTeacherInfo(courseDTO.getOlt_no());
		List<CourseReplyDTO> replys = courseService.getCourseReplys(pageNo);
		List<CourseTagDTO> tags = courseService.getCourseTags(pageNo);
		int likeCnt = courseService.getCourseLikeCount(pageNo);
		int starAvg = 0;
		if(replys.size() != 0) {
			for(CourseReplyDTO reply : replys)
				 starAvg += reply.getStar_rating();
			starAvg /= replys.size();
		}
		int stdCnt = 0;
		boolean existLike; 
		if(courseService.existCourseLike(pageNo, 2) == 1) // m_no 임시
			existLike = true;
		else existLike= false;
		
		
		System.out.println("test: " + memberService.getNameByM_no(2));
		
		System.out.println("강의 상세 페이지 정보 출력");
		System.out.println(courseDTO);
		System.out.println(teachertDTO);
		System.out.println(replys);
		System.out.println(tags);
		System.out.println(likeCnt);
		
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", teachertDTO);
		model.addAttribute("replys", replys);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("memberSerivce", memberService);
		model.addAttribute("existLike", existLike);
		
		return "course/course_detail";
	}
	
	@RequestMapping("/course/courseClickedLike")
	public String clickedLikeInCourse(HttpServletRequest request) {
		System.out.println("받음");
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		//int m_no = Integer.parseInt(request.getParameter("m_no"));
		String status = request.getParameter("status");
		if(status.equals("true")) {
			courseService.insertCourseLike(oli_no, 2);
		}
		else {
			courseService.deleteCourseLike(oli_no, 2);
		}
		
		return "redirect:" + request.getHeader("referer");
	}
	
	//-----------------------
	// 작업중 mapper 작성 예정
	//-----------------------
	@RequestMapping("/course/writeCourse")
	public String writeCourse(HttpServletRequest request) {
		
		return "course/course_write";
	}
	/*
	@RequestMapping("/course/submitCourse")
	public String submitCourse(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("Member");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String img_path = request.getParameter("img_path");
		String price = request.getParameter("price");
		String[] tags = request.getParameterValues("tags");
		
		if(memberDTO == null || title == null || content == null || img_path == null || price == null) {
			return "error";
		}
		
		// 강의 등록
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setOlt_no(memberDTO.getM_no());
		courseDTO.setTitle(title);
		courseDTO.setContent(content);
		courseDTO.setImg_path(img_path);
		courseDTO.setPrice(Integer.parseInt(price));
		courseDTO.setReg_date(new Date(System.currentTimeMillis()));
		
		courseService.submitCourse(courseDTO);
		
		
		
		// 태그 등록
		for(String t : tags) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(courseDTO.getOli_no());
			courseTagDTO.setTag(t);
			
			courseService.submitTag(courseTagDTO);
		}
		
		return "index";
	}
	
	@RequestMapping("/course/submitReply")
	public String submitReply(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("Member");
		String oli_no = request.getParameter("oli_no");
		String star_rating = request.getParameter("star_rating");
		String content = request.getParameter("content");
		
		if(memberDTO == null || oli_no == null || star_rating == null || content == null) {
			return "error";
		}
		
		CourseReplyDTO courseReplyDTO = new CourseReplyDTO();
		courseReplyDTO.setOli_no(Integer.parseInt(oli_no));
		courseReplyDTO.setM_no(memberDTO.getM_no());
		courseReplyDTO.setStar_rating(Integer.parseInt(star_rating));
		courseReplyDTO.setContent(content);
		courseReplyDTO.setReg_date(new Date(System.currentTimeMillis()));
		
		courseService.submitReply(courseReplyDTO);
		return request.getHeader("referer");
	}*/
}
