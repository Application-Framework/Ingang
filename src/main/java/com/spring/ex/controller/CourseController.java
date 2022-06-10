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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.FileUploadService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.PagingService;

@Controller
public class CourseController {
	private PagingService pagingService;
	
	@Inject
	private CourseService courseService; 
	
	@Inject
	private MemberService memberService;
	
	@Inject
	CommunityBoardService cbService;
	
	@Inject
	private FileUploadService fileUploadService;
	
	void showCourses(HttpServletRequest request, Model model, String tag) {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order");
				
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
		
		
		System.out.println("pagingDTO : " + pagingService.getPaging());
		
		model.addAttribute("paging", pagingService.getPaging()); 
		model.addAttribute("clist", courseService.getCoursePage(pageMap));
		model.addAttribute("nowURL", request.getServletPath());
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);
		model.addAttribute("keywordParam", keywordParam);
		model.addAttribute("orderParam", orderParam);
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
		List<CourseVideoDTO> videos = courseService.getCourseVideoList(pageNo);
		
		
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
		
		
		System.out.println("강의 상세 페이지 정보 출력");
		System.out.println(courseDTO);
		System.out.println(teachertDTO);
		System.out.println(replys);
		System.out.println(tags);
		System.out.println(likeCnt);
		System.out.println(videos);
		
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", teachertDTO);
		model.addAttribute("replys", replys);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("memberSerivce", memberService);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("videos", videos);
		model.addAttribute("contentType", "main");
		
		return "course/course_detail";
	}
	
	@RequestMapping("/courses/{pageNo}/main")
	public String course_main(Model model) {
		model.addAttribute("contentType", "main");
		
		return "course/course_detail";
	}
	
	@RequestMapping("/courses/{pageNo}/community")
	public String course_community(HttpServletRequest request, Model model, @PathVariable int pageNo) throws Exception {
		pagingService = new PagingService(request, cbService.getCommunityBoardTotalCount(), 10);
		
		HashMap<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("Page", pagingService.getNowPage());
		pageMap.put("PageSize", 10);
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		TeacherDTO teachertDTO = courseService.getTeacherInfo(courseDTO.getOlt_no());
		List<CourseReplyDTO> replys = courseService.getCourseReplys(pageNo);
		List<CourseTagDTO> tags = courseService.getCourseTags(pageNo);
		List<CommunityBoardDTO> cbRegDateList = cbService.getCommunityBoardChatRegDateShowPage(pageMap);
		
		System.out.println(cbRegDateList);
		
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
		
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", teachertDTO);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("memberSerivce", memberService);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("cbRegDateList", cbRegDateList);
		model.addAttribute("contentType", "community");
		model.addAttribute("paging", pagingService.getPaging());
		
		return "course/course_detail";
	}
	
	
	@RequestMapping("/courses/{pageNo}/play/{olv_no}")
	public String course_play(HttpServletRequest request, Model model, @PathVariable int pageNo, @PathVariable int olv_no) {
		CourseVideoDTO courseVideoDTO = courseService.getCourseVideo(olv_no);
		List<CourseVideoDTO> videoList = courseService.getCourseVideoList(pageNo);
		
		model.addAttribute("videoPath", courseVideoDTO.getS_file_name());
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("olv_no", olv_no);
		model.addAttribute("videoList", videoList);
		model.addAttribute("type", "content");
		return "course/course_play";
	}
	
	@RequestMapping("/courses/courseClickedLike")
	public String clickedLikeInCourse(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		//int m_no = Integer.parseInt(request.getParameter("m_no"));
		String status = request.getParameter("status");
		if(status.equals("true")) {
			courseService.insertCourseLike(oli_no, memberDTO.getM_no());
		}
		else {
			courseService.deleteCourseLike(oli_no, memberDTO.getM_no());
		}
		
		return "redirect:" + request.getHeader("referer");
	}
	
	//-----------------------
	// 작업중 mapper 작성 예정
	//-----------------------
	@RequestMapping("/courses/writeCourse")
	public String writeCourse(HttpServletRequest request) {
		
		return "course/course_write";
	}
	
	@RequestMapping("/courses/submitCourse")
	public String submitCourse(HttpServletRequest request, @RequestParam("thumbnail") MultipartFile thumbnail) throws Exception {
		System.out.println("등록 시작");
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		String pageNo = request.getParameter("pageNo");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String[] tags = request.getParameterValues("tags");
		String[] videoTitles = request.getParameterValues("video_titles");
		String[] videoPaths = request.getParameterValues("video_paths");
	
		String img_path = null;
		
		if(!thumbnail.isEmpty()) { 
			img_path = fileUploadService.uploadFile(thumbnail, "/img/course/uploaded_images");
		}
		
		
		if(memberDTO == null || title == null || content == null || img_path == null || price == null) {
			return "error";
		}
		
		System.out.println("t_no :"+ courseService.getOlt_noByM_no(memberDTO.getM_no()));
		
		// 강의 등록
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setOlt_no(courseService.getOlt_noByM_no(memberDTO.getM_no()));
		courseDTO.setTitle(title);
		courseDTO.setContent(content);
		courseDTO.setImg_path(img_path);
		courseDTO.setPrice(Integer.parseInt(price));
		courseDTO.setReg_date(new Date(System.currentTimeMillis()));
		
		courseService.submitCourse(courseDTO);
		
		System.out.println(courseDTO.getOli_no());
		
		// 멀티 태그 추가 예정
		
		for(String t : tags) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(courseDTO.getOli_no());
			courseTagDTO.setTag(t);
			
			courseService.submitTag(courseTagDTO);
		}
		
		
		// 비디오 추가 예정
		for(int i = 0; i < videoTitles.length; i++) {
			System.out.println("videoPaths:" + videoPaths[i]);
			System.out.println("videoTitles:" + videoTitles[i]);
			CourseVideoDTO courseVideoDTO = new CourseVideoDTO();
			courseVideoDTO.setOli_no(courseDTO.getOli_no());
			courseVideoDTO.setTitle(videoTitles[i]);
			courseVideoDTO.setS_file_name(videoPaths[i]);
			
			courseService.submitCourseVideo(courseVideoDTO);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/courses/submitReply")
	public String submitReply(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		String oli_no = request.getParameter("pageNo");
		String star_rating = request.getParameter("star_rating");
		String content = request.getParameter("content");
		
		System.out.println("star_rating:" + star_rating);
		
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
	
	// 강의 재생 페이지의 우측 아이콘 클릭했을 때
	@RequestMapping("/courses/clickNav")
	public String clickNav(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		
		System.out.println("type : " + type);
		
		model.addAttribute("type", type);
		return "redirect:" + request.getHeader("referer");
	}
}
