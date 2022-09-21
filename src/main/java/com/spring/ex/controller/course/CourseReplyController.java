package com.spring.ex.controller.course;

import java.sql.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.service.CourseService;

@Controller
public class CourseReplyController {
	//--------------------------
	// 강의 수강평 관리 컨트롤러
	//--------------------------
	
	@Inject
	private CourseService courseService; 
	
	@RequestMapping("/course/submitReply")
	public String submitReply(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		String oli_no = request.getParameter("pageNo");
		String star_rating = request.getParameter("star_rating");
		String content = request.getParameter("content");
		
		if(memberDTO == null || oli_no == null || star_rating == "" || content == null) {
			return "error";
		}
		
		CourseReplyDTO courseReplyDTO = new CourseReplyDTO();
		courseReplyDTO.setOli_no(Integer.parseInt(oli_no));
		courseReplyDTO.setM_no(memberDTO.getM_no());
		courseReplyDTO.setStar_rating(Integer.parseInt(star_rating));
		courseReplyDTO.setContent(content);
		courseReplyDTO.setReg_date(new Date(System.currentTimeMillis()));
		
		courseService.submitReply(courseReplyDTO);
		return "redirect:" + request.getHeader("referer");
	}
}
