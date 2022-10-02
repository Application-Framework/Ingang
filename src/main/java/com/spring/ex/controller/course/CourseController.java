package com.spring.ex.controller.course;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.HistoryOrderService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.NoteService;
import com.spring.ex.service.PagingService;
import com.spring.ex.service.TeacherService;
import com.spring.ex.service.TypeService;
import com.spring.ex.service.t_TagService;

@Controller
public class CourseController {
	private PagingService pagingService;
	
	@Inject
	private CourseService courseService; 
	
	@Inject
	private TeacherService teacherService;
	
	@Inject
	private MemberService memberService;
	
	@Inject
	CommunityBoardService cbService;
	
	@Inject
	private NoteService noteService;
	
	@Inject
	private t_TagService tagService;
	
	@Inject
	private TypeService typeService;
	
	@Inject
	private HistoryOrderService historyOrderService;
	
	// 강의 검색 페이지
	private void showCourses(HttpServletRequest request, Model model, String main_type, String sub_type) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		boolean isTeacher = false;
		if(memberDTO != null) 
			isTeacher = (teacherService.checkTeacherByM_no(memberDTO.getM_no()) == 1) ? true : false;
		
		System.out.println("main_type : " + main_type);
		System.out.println("sub_type : " + sub_type);
		
		String searchTitle = request.getParameter("s");
		String order = request.getParameter("order");
		String _tags = request.getParameter("tags");
		List<String> tags = null;
		if(_tags != null) {
			tags = Arrays.asList(_tags.split("\\s*,\\s*")); // convert string to separated comma string
			System.out.println("tags : " + tags);
		}
		
		String level = request.getParameter("level");
		String charge = request.getParameter("charge");
		
		final int pageSize = 12;
		
		HashMap<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("main_type_abbr", main_type);
		countMap.put("sub_type_abbr", sub_type);
		countMap.put("searchTitle", searchTitle);
		countMap.put("tags", tags);
		countMap.put("level", level);
		countMap.put("charge", charge);
		
		int totalCount = courseService.getCourseTotalCount(countMap);
		System.out.println("totalCount : " + totalCount);
		
		pagingService = new PagingService(request, totalCount, pageSize);
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("main_type_abbr", main_type);
		pageMap.put("sub_type_abbr", sub_type);
		pageMap.put("page", pagingService.getNowPage());
		pageMap.put("pageSize", pageSize);
		pageMap.put("order", order);
		pageMap.put("searchTitle", searchTitle);
		pageMap.put("tags", tags);
		pageMap.put("level", level);
		pageMap.put("charge", charge);
		
		/* model.addAttribute("mainTypeList", typeSerivce.getMainTypeList()); */
		model.addAttribute("typeService", typeService);
		model.addAttribute("teacherService", teacherService);
		model.addAttribute("paging", pagingService.getPaging()); 
		model.addAttribute("clist", courseService.getCoursePage(pageMap));
		model.addAttribute("s", searchTitle);
		model.addAttribute("order", order);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("isTeacher", isTeacher);
		model.addAttribute("courseService", courseService);
		model.addAttribute("tagList", tagService.getTagList());
	}
	
	// 모든 강의 검색
	@RequestMapping("/courses")
	public String showAllCourses(HttpServletRequest request, Model model) {
		showCourses(request, model, null, null);
		return "course/course_search";
	}
	
	// 메인 타입의 모든 강의 검색
	@RequestMapping("/courses/{main_type}")
	public String showAllTypeOfCourses(HttpServletRequest request, Model model, @PathVariable String main_type) {
		showCourses(request, model, main_type, null);
		return "course/course_search";
	}
	
	// 서브 타입만 검색
	@RequestMapping("/courses/{main_type}/{sub_type}")
	public String showAllCourses(HttpServletRequest request, Model model, @PathVariable String main_type, @PathVariable String sub_type) {
		showCourses(request, model, main_type, sub_type);
		return "course/course_search";
	}
	
	// 강의 상세 페이지
	@RequestMapping("/course/{pageNo}")
	public String courses_detail(Model model, HttpServletRequest request, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		TeacherDTO courseTeacherDTO = teacherService.getTeacherInfo(courseDTO.getOlt_no());
		boolean existLike = false;
		// 접속한 회원이 현재 강의의 강사인지 확인
		boolean isCurrentCourseTeacher = false;
		if(memberDTO != null) {
			TeacherDTO currentTeacherDTO = teacherService.getTeacherInfoByM_no(memberDTO.getM_no());
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
			HistoryOrderLectureDTO historyOrderLectureDTO = historyOrderService.getHistoryOrderLectureByOli_noM_no(pageNo, memberDTO.getM_no());
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
		System.out.println(notes);
		
		model.addAttribute("memberService", memberService);
		model.addAttribute("tagService", tagService);
		model.addAttribute("typeService", typeService);
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", courseTeacherDTO);
		model.addAttribute("replys", replys);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("videos", videos);
		model.addAttribute("contentType", "main");
		model.addAttribute("notes", notes);
		model.addAttribute("isCurrentCourseTeacher", isCurrentCourseTeacher);
		model.addAttribute("mainCategory", courseService.getMainTypeOfCourse(pageNo).getMain_type_name());
		model.addAttribute("subCategoryList", courseService.getCourseSubTypeList(pageNo));
		
		return "course/course_detail";
	}
	
	/*
	 * @RequestMapping("/courses/{pageNo}/main") public String course_main(Model
	 * model) { model.addAttribute("contentType", "main");
	 * 
	 * return "course/course_detail"; }
	 */
	
	@RequestMapping("/course/{pageNo}/community")
	public String course_community(HttpServletRequest request, Model model, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		TeacherDTO teachertDTO = teacherService.getTeacherInfo(courseDTO.getOlt_no());
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
	@RequestMapping("/course/{pageNo}/play/{olv_no}")
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
	
	@RequestMapping("/courseClickedLike")
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
}
