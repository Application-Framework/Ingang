package com.spring.ex.admin.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseSubTypeDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.HistoryOrderService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.PagingService;
import com.spring.ex.service.TeacherService;
import com.spring.ex.service.TypeService;
import com.spring.ex.service.t_TagService;

@Controller
public class AdminCourseController {
	
	
	@Inject
	private CourseService courseService;
	
	@Inject
	private TeacherService teacherService;
	
	@Inject
	private HistoryOrderService historyOrderService;
	
	@Inject
	private MemberService memberService;
	
	@Inject
	private TypeService typeService;
	
	@Inject
	private t_TagService tagService;
	
	// 강의 관리 대시보드 페이지
	@RequestMapping("/admin/course")
	public String courseDashboard(Model model) {
		int pendingCoursesCount = courseService.getPendingCoursesCount();
		int todaySellingPrice = 0;
		int todaySellingPriceCount = 0;
		model.addAttribute("pendingCoursesCount", pendingCoursesCount);
		model.addAttribute("todaySellingPrice", todaySellingPrice);
		model.addAttribute("todaySellingPriceCount", todaySellingPriceCount);
		
		return "admin/course/course_dashboard";
	}
	
	// 승인 대기중인 강의들 관리 페이지
	@RequestMapping("/admin/course/pending-courses")
	public String pendingCourses(HttpServletRequest request, Model model) {
		String searchCategory = request.getParameter("searchCategory");
		String search = request.getParameter("search");
		
		final int pageSize = 10;
		int totalCount = courseService.getPendingCoursesCount();
		PagingService pagingService = new PagingService(request, totalCount, pageSize);
		
		model.addAttribute("paging", pagingService.getPaging());
		model.addAttribute("courseRequestList", courseService.getPendingCourseRequestList(searchCategory, search, pagingService.getNowPage(), pageSize));
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("search", search);
		
		return "admin/course/pending_courses";
	}
	
	// 모든 강의 관리 페이지
	@RequestMapping("/admin/course/courses-management")
	public String coursesManagement(HttpServletRequest request, Model model) {
		
		/*
		String searchCategory = request.getParameter("searchCategory");
		
		String search = request.getParameter("search");
		
		final int pageSize = 10;
		int totalCount = courseService.getCourseTotalCount();
		PagingService pagingService = new PagingService(request, totalCount, pageSize);
		
		model.addAttribute("paging", pagingService.getPaging());
		model.addAttribute("courseList", courseService.getPendingCourseRequestList(searchCategory, search, pagingService.getNowPage(), pageSize));
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("search", search);
		*/
		return "admin/course/courses_management";
	}
	
	@RequestMapping("/admin/course/{oli_no}")
	public String coursesManagement(@PathVariable("oli_no") int oli_no, HttpServletRequest request, Model model) {
		CourseDTO course = courseService.getCourseDetail(oli_no);
		List<HistoryOrderLectureDTO> holList = historyOrderService.getHistoryOrderLectureListByOli_no(oli_no);
		
		List<CourseSubTypeDTO> myCategoryList = courseService.getCourseSubTypeList(oli_no);
		List<CourseTagDTO> myTagList = courseService.getCourseTags(oli_no);
		List<CourseVideoDTO> videoList = courseService.getCourseVideoList(oli_no);
		
		
		model.addAttribute("courseService", courseService);
		model.addAttribute("teacherService", teacherService);
		model.addAttribute("memberService", memberService);
		model.addAttribute("typeService", typeService);
		
		model.addAttribute("course", course);
		model.addAttribute("orderHistoryList", holList);
		model.addAttribute("allTagList", tagService.getTagList());
		model.addAttribute("allMainCategoryList", typeService.getMainTypeList());
		model.addAttribute("allSubCategoryList", typeService.getSubTypeListOfMainType(courseService.getMainTypeOfCourse(oli_no).getMain_type_no()));
		model.addAttribute("myCategoryList", myCategoryList);
		model.addAttribute("myTagList", myTagList);
		model.addAttribute("videoList", videoList);
		
		return "admin/course/course_detail";
	}
	
	@ResponseBody
	@RequestMapping("/approvalCourseRequest")
	public void approvalCourseRequest(HttpServletRequest request) {
		/*MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member.getM_authority() != 1) { 
			System.err.println("회원이 아닌 사용자가 approvalCourseRequest에 접근했습니다.");
			return;
		}*/
		System.out.println("approvalCourseRequest");
		String olr_no = request.getParameter("olr_no");
		courseService.approveCourse(Integer.parseInt(olr_no));
	}
	
	@ResponseBody
	@RequestMapping("/rejectCourseRequest")
	public void rejectCourseRequest(HttpServletRequest request) {
		/*MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member.getM_authority() != 1) { 
			System.err.println("회원이 아닌 사용자가 approvalCourseRequest에 접근했습니다.");
			return;
		}*/
		System.out.println("rejectCourseRequest");
		String olr_no = request.getParameter("olr_no");
		String rejection_message = request.getParameter("rejection_message");
		courseService.rejectCourse(Integer.parseInt(olr_no), rejection_message);
	}
}
