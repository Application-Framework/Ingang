package com.spring.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
	@RequestMapping("/courses")
	public String courses() {
		return "course/courses_search";
	}
	
	@RequestMapping("/courses_detail")
	public String courses_detail() {
		return "course/course_detail";
	}
}
