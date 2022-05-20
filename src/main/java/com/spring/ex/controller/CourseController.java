package com.spring.ex.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.service.CourseService;
import com.spring.ex.service.PagingService;

@Controller
public class CourseController {
	PagingService pagingService;
	
	@Inject
	CourseService courseService; 
	
	void showCourses(HttpServletRequest request, Model model, String tag) {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order");
		
		System.out.println(order);
		keyword = (keyword == null) ? "" : keyword;
		order = (order == null) ? "" : order;
		
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
		
		System.out.println(request.getServletPath());
		System.out.println(request.getRequestURI());
	}
	
	@RequestMapping("/courses")
	public String courses(HttpServletRequest request, Model model) {
		showCourses(request, model, null);
		return "course/courses_search";
	}
	
	@RequestMapping("/courses/web-dev")
	public String coursesWebDev(HttpServletRequest request, Model model) {
		showCourses(request, model, "웹개발");
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
		showCourses(request, model, "프로그래밍언어");
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
		showCourses(request, model, "모바일앱개발");
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
	public String courses_detail(Model model, HttpServletRequest request, @PathVariable int pageNo) {
		
		
		return "course/course_detail";
	}
}
