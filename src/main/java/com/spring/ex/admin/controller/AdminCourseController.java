package com.spring.ex.admin.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.admin.service.AdminCourseService;
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseSubTypeDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.FileService;
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
	
	@Inject
	private AdminCourseService adminCourseService;
	
	@Inject
	private FileService fileService;
	
	// 강의 관리 대시보드 페이지
	@RequestMapping("/admin/course")
	public String courseDashboard(Model model) {
		int pendingCoursesCount = adminCourseService.getPendingCoursesCount();
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
		int totalCount = adminCourseService.getPendingCoursesCount();
		PagingService pagingService = new PagingService(request, totalCount, pageSize);
		
		model.addAttribute("paging", pagingService.getPaging());
		model.addAttribute("courseRequestList", adminCourseService.getPendingCourseRequestList(searchCategory, search, pagingService.getNowPage(), pageSize));
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("search", search);
		
		return "admin/course/pending_courses";
	}
	
	// 모든 강의 관리 페이지
	@RequestMapping("/admin/course/courses-management")
	public String coursesManagement(HttpServletRequest request, Model model) {
		String searchCategory = request.getParameter("searchCategory");
		String searchKeyword = request.getParameter("searchKeyword");
		
		final int pageSize = 10;
		int coursePostCount = adminCourseService.getCoursePostCount(searchCategory, searchKeyword);
		PagingService pagingService = new PagingService(request, coursePostCount, pageSize);
		
		model.addAttribute("paging", pagingService.getPaging());
		model.addAttribute("courseList", adminCourseService.getCourseBoard(searchCategory, searchKeyword, pagingService.getNowPage(), pageSize));
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("searchKeyword", searchKeyword);
		
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
		
		if(course.getOrigin() == 0) {
			 model.addAttribute("origin_oli_no", adminCourseService.getCourseRequestByOli_no(oli_no).getOrigin_oli_no());
		}
		model.addAttribute("course", course);
		model.addAttribute("orderHistoryList", holList);
		model.addAttribute("allTagList", tagService.getTagList());
		model.addAttribute("allMainCategoryList", typeService.getMainTypeList());
		if(courseService.getMainTypeOfCourse(oli_no) != null)
			model.addAttribute("allSubCategoryList", typeService.getSubTypeListOfMainType(courseService.getMainTypeOfCourse(oli_no).getMain_type_no()));
		model.addAttribute("myCategoryList", myCategoryList);
		model.addAttribute("myTagList", myTagList);
		model.addAttribute("videoList", videoList);
		model.addAttribute("teacherList", teacherService.getTeacherList());
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
		adminCourseService.approveCourse(Integer.parseInt(olr_no));
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
		adminCourseService.rejectCourse(Integer.parseInt(olr_no), rejection_message);
	}
	
	@ResponseBody
	@RequestMapping("admin/course/deleteCourses")
	public void deleteCourses(HttpServletRequest request) throws Exception {
		String[] oli_noList = request.getParameterValues("oli_noList");
		
		for(String _oli_no : oli_noList) {
			int oli_no = Integer.parseInt(_oli_no);
			CourseDTO dto = courseService.getCourseDetail(oli_no);
			// 섬네일 삭제
			fileService.deleteFileToLocalAndServer(dto.getImg_path());
			fileService.deleteAllFileOfPost(oli_no, 1);
			courseService.deleteCourse(oli_no);
		}
		
	}
	
	@ResponseBody
	@RequestMapping("admin/course/createCourse")
	public void createCourse(HttpServletRequest request, @RequestParam("thumbnail") MultipartFile thumbnail) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return;
		}
		
		if(member.getM_authority() != 1) {
			System.out.println("관리자 권한이 필요합니다.");
			return;
		}
		
		System.out.println("등록 시작");
		String olt_no = request.getParameter("olt_no");
		String title = request.getParameter("title");
		String introduction = request.getParameter("introduction");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String level = request.getParameter("level");
		String[] categorys = request.getParameterValues("subCategorys");
		String[] tags = request.getParameterValues("tags");
		String[] videoTitles = request.getParameterValues("video_titles");
		String[] videoPaths = request.getParameterValues("video_paths");
		
		if(member == null || title == null || introduction == null || content == null || price == null || level == null || categorys == null || tags == null) {
			System.out.println("빈 칸이 있습니다.");
			return;
		}
		
		// 강의 등록
		CourseDTO course = new CourseDTO();
		course.setOlt_no(Integer.parseInt(olt_no));
		course.setTitle(title);
		course.setIntroduction(introduction);
		course.setContent(content);
		course.setPrice(Integer.parseInt(price));
		course.setLevel(Integer.parseInt(level));
		course.setEnable(1);
		course.setOrigin(1);
		
		adminCourseService.createCourse(course, thumbnail, categorys, tags, videoTitles, videoPaths);
	}
	
	@ResponseBody
	@RequestMapping("admin/course/updateCourse")
	public void updateCourse(HttpServletRequest request, @RequestParam("thumbnail") MultipartFile thumbnail) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return;
		}
		
		if(member.getM_authority() != 1) {
			System.out.println("관리자 권한이 필요합니다.");
			return;
		}
		
		System.out.println("수정 시작");
		String oli_no = request.getParameter("oli_no");
		String olt_no = request.getParameter("olt_no");
		String title = request.getParameter("title");
		String introduction = request.getParameter("introduction");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String level = request.getParameter("level");
		String[] categorys = request.getParameterValues("subCategorys");
		String[] tags = request.getParameterValues("tags");
		String[] videoTitles = request.getParameterValues("video_titles");
		String[] videoPaths = request.getParameterValues("video_paths");
		
		if(member == null || title == null || introduction == null || content == null || price == null || level == null || categorys == null || tags == null) {
			System.out.println("빈 칸이 있습니다.");
			return;
		}
		
		// 강의 등록
		CourseDTO course = courseService.getCourseDetail(Integer.parseInt(oli_no));
		course.setOlt_no(Integer.parseInt(olt_no));
		course.setTitle(title);
		course.setIntroduction(introduction);
		course.setContent(content);
		course.setPrice(Integer.parseInt(price));
		course.setLevel(Integer.parseInt(level));
		
		adminCourseService.updateCourse(course, thumbnail, categorys, tags, videoTitles, videoPaths);
	}
}
