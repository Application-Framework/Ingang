package com.spring.ex.controller.course;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.dto.course.HistoryOrderLectureDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.NoteService;
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
	private NoteService noteService;
	
	void showCourses(HttpServletRequest request, Model model, String tag) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		boolean isTeacher = false;
		if(memberDTO != null) 
			isTeacher = (courseService.checkTeacherByM_no(memberDTO.getM_no()) == 1) ? true : false;
		
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order");
		String tagParam = request.getParameter("tag");
		keyword = (keyword == null) ? "" : keyword;
		order = (order == null) ? "" : order;
		
		String keywordParam = (keyword != "") ? "keyword="+keyword+"&" : "";
		String orderParam = (order != "") ? "order="+order+"&" : "";
		
		final int pageSize = 12;
		
		HashMap<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("keyword", keyword);
		countMap.put("tag", tag);
		countMap.put("tagParam", tagParam);
		
		int totalCount = courseService.getCourseTotalCount(countMap);
		
		pagingService = new PagingService(request, totalCount, pageSize);
		System.out.println("NowPage : " + pagingService.getNowPage());
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("page", pagingService.getNowPage());
		pageMap.put("pageSize", pageSize);
		pageMap.put("order", order);
		pageMap.put("keyword", keyword);
		pageMap.put("tag", tag);
		pageMap.put("tagParam", tagParam);
		
		model.addAttribute("paging", pagingService.getPaging()); 
		model.addAttribute("clist", courseService.getCoursePage(pageMap));
		model.addAttribute("nowURL", request.getServletPath());
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);
		model.addAttribute("keywordParam", keywordParam);
		model.addAttribute("orderParam", orderParam);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("isTeacher", isTeacher);
	}
	
	@RequestMapping("/courses")
	public String courses(HttpServletRequest request, Model model) {
		showCourses(request, model, null);
		return "course/course_search";
	}
	
	@RequestMapping("/courses/web-dev")
	public String coursesWebDev(HttpServletRequest request, Model model) {
		showCourses(request, model, "웹 개발");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/front-end")
	public String coursesFrontEnd(HttpServletRequest request, Model model) {
		showCourses(request, model, "프론트엔드");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/back-end")
	public String coursesBackEnd(HttpServletRequest request, Model model) {
		showCourses(request, model, "백엔드");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/programming-lang")
	public String coursesProgrammingLang(HttpServletRequest request, Model model) {
		showCourses(request, model, "프로그래밍 언어");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/database-dev")
	public String coursesDatabaseDev(HttpServletRequest request, Model model) {
		showCourses(request, model, "데이터베이스");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/algorithm")
	public String coursesAlgorithm(HttpServletRequest request, Model model) {
		showCourses(request, model, "알고리즘");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/mobile-app")
	public String coursesMobileApp(HttpServletRequest request, Model model) {
		showCourses(request, model, "모바일 앱 개발");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/artificial-intelligence")
	public String coursesArtificialIntelligence(HttpServletRequest request, Model model) {
		showCourses(request, model, "AI");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/security")
	public String coursesSecurity(HttpServletRequest request, Model model) {
		showCourses(request, model, "보안");
		return "course/course_search";
	}
	
	// 강의 상세 페이지
	@RequestMapping("/courses/{pageNo}")
	public String courses_detail(Model model, HttpServletRequest request, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		TeacherDTO courseTeacherDTO = courseService.getTeacherInfo(courseDTO.getOlt_no());
		boolean existLike = false;
		// 접속한 회원이 현재 강의의 강사인지 확인
		boolean isCurrentCourseTeacher = false;
		if(memberDTO != null) {
			TeacherDTO currentTeacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
			existLike = (courseService.existCourseLike(pageNo, memberDTO.getM_no()) == 1) ? true : false;
			if(currentTeacherDTO != null) {
				if(courseTeacherDTO.getOlt_no() == currentTeacherDTO.getOlt_no()) isCurrentCourseTeacher = true;
			}
		}
		
		List<CourseReplyDTO> replys = courseService.getCourseReplys(pageNo);
		List<CourseTagDTO> tags = courseService.getCourseTags(pageNo);
		List<CourseVideoDTO> videos = courseService.getCourseVideoList(pageNo);
		List<NoteDTO> notes = noteService.getNoteListByOli_no(pageNo);
		
		// 회원이 강의를 구매했는지 확인
		if(memberDTO != null) {
			HistoryOrderLectureDTO historyOrderLectureDTO = courseService.getHistoryOrderLectureByOli_noM_no(pageNo, memberDTO.getM_no());
			boolean purchased = (historyOrderLectureDTO != null) ? true : false;
			model.addAttribute("purchased", purchased);
		}
		
		int likeCnt = courseService.getCourseLikeCount(pageNo);
		float starAvg = courseService.getCourseStarAvg(pageNo);
		int stdCnt = 0;
		
		System.out.println("강의 상세 페이지 정보 출력");
		System.out.println(courseDTO);
		System.out.println(courseTeacherDTO);
		System.out.println(replys);
		System.out.println(tags);
		System.out.println(likeCnt);
		System.out.println(videos);
		
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", courseTeacherDTO);
		model.addAttribute("replys", replys);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("memberService", memberService);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("videos", videos);
		model.addAttribute("contentType", "main");
		model.addAttribute("notes", notes);
		model.addAttribute("isCurrentCourseTeacher", isCurrentCourseTeacher);
		
		return "course/course_detail";
	}
	
	// 왜 쓴지 확인 필요
	@RequestMapping("/courses/{pageNo}/main")
	public String course_main(Model model) {
		model.addAttribute("contentType", "main");
		
		return "course/course_detail";
	}
	
	@RequestMapping("/courses/{pageNo}/community")
	public String course_community(HttpServletRequest request, Model model, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		TeacherDTO teachertDTO = courseService.getTeacherInfo(courseDTO.getOlt_no());
		List<CourseTagDTO> tags = courseService.getCourseTags(pageNo);
		int likeCnt = courseService.getCourseLikeCount(pageNo);
		float starAvg = courseService.getCourseStarAvg(pageNo);
		boolean existLike = false;
		if(memberDTO != null) existLike = (courseService.existCourseLike(pageNo, memberDTO.getM_no()) == 1) ? true : false; 
		int stdCnt = 0;
		
		String search = request.getParameter("search");
		if(search == null) search = "";
		
		System.out.println("search : " + search);
		
		String classify = request.getParameter("classify");
		if(classify == null) classify = "2"; // default로 질문 게시판 설정
		
		HashMap<String, Object> cbMap = new HashMap<String, Object>();
		cbMap.put("oli_no", pageNo);
		cbMap.put("search", search);
		cbMap.put("classify", Integer.parseInt(classify));
		pagingService = new PagingService(request, cbService.selectCommunityBoardTotalCountByOli_no(cbMap), 10);
		cbMap.put("page", pagingService.getNowPage());
		cbMap.put("pageSize", 10);
		List<CommunityBoardDTO> cbList = cbService.selectCommunityBoardByOli_no(cbMap);
		
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", teachertDTO);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("memberService", memberService);
		model.addAttribute("cbService", cbService);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("cbList", cbList);
		model.addAttribute("contentType", "community");
		model.addAttribute("paging", pagingService.getPaging());
		model.addAttribute("classify", Integer.parseInt(classify));
		
		return "course/course_detail";
	}
	
	// 강의 재생 페이지
	@RequestMapping("/courses/{pageNo}/play/{olv_no}")
	public String course_play(HttpServletRequest request, Model model, @PathVariable int pageNo, @PathVariable int olv_no) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		CourseVideoDTO courseVideoDTO = courseService.getCourseVideo(olv_no);
		List<CourseVideoDTO> videoList = courseService.getCourseVideoList(pageNo);
		NoteDTO noteDTO = noteService.getNoteByOli_noM_no(pageNo, memberDTO.getM_no());
		System.out.println(noteDTO);
		if(noteDTO != null) {
			NoteArticleDTO noteArticleDTO = noteService.getNoteArticleByN_noOlv_no(noteDTO.getN_no(), olv_no);
			System.out.println(noteArticleDTO);
			model.addAttribute("noteArticle", noteArticleDTO);
		}
		
		model.addAttribute("videoPath", courseVideoDTO.getS_file_name());
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("olv_no", olv_no);
		model.addAttribute("videoList", videoList);
		model.addAttribute("type", "content");
		model.addAttribute("note", noteDTO);
		
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
	
	// 강의 구매
	@RequestMapping("/courses/purchaseCourse")
	public String purchaseCourse(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		CourseDTO courseDTO = courseService.getCourseDetail(oli_no);
		
		HistoryOrderLectureDTO historyOrderLectureDTO = new HistoryOrderLectureDTO();
		historyOrderLectureDTO.setOli_no(oli_no);
		historyOrderLectureDTO.setM_no(memberDTO.getM_no());
		historyOrderLectureDTO.setPayment(courseDTO.getPrice());
		historyOrderLectureDTO.setPayment_status(1);
		
		courseService.insertHistoryOrderLecture(historyOrderLectureDTO);
		
		return "redirect:/";
	}
}
