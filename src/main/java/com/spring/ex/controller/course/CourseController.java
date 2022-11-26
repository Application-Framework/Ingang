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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
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
		if(courseDTO == null) {
			model.addAttribute("errorMessage", "존재하지 않는 강의입니다.");
			return "errorPage";
		}
		
		TeacherDTO teacher = teacherService.getTeacherInfo(courseDTO.getOlt_no());
		if(courseDTO.getOlt_no() != 0 && teacher == null) {
			model.addAttribute("errorMessage", "존재하지 않는 강사입니다.");
			return "errorPage";
		}
		
		boolean isExistLike = false; 
		boolean isCurrentCourseTeacher = false;
		
		if(courseDTO.getOrigin() == 0) {
			System.err.println("원본이 아닌 강의에 접속을 시도했습니다.");
			model.addAttribute("errorMessage", "원본이 아닌 강의에 접속을 시도했습니다.");
			return "errorPage";
		}
		
		List<CourseReplyDTO> replys = courseService.getCourseReplys(pageNo);
		List<CourseTagDTO> tags = courseService.getCourseTags(pageNo);
		List<CourseVideoDTO> videos = courseService.getCourseVideoList(pageNo);
		List<NoteDTO> notes = noteService.getNoteListByOli_no(pageNo);
		
		// 회원이 강의를 구매했는지 확인
		if(memberDTO != null) {
			isExistLike = courseService.existCourseLike(pageNo, memberDTO.getM_no());
			isCurrentCourseTeacher = teacherService.isTeacherOfThisCourse(pageNo, memberDTO.getM_no());
			HistoryOrderLectureDTO historyOrderLectureDTO = historyOrderService.getHistoryOrderLectureByOli_noM_no(pageNo, memberDTO.getM_no());
			boolean purchased = (historyOrderLectureDTO != null) ? true : false;
			model.addAttribute("purchased", purchased);
		}
		
		int likeCnt = courseService.getCourseLikeCount(pageNo);
		float starAvg = courseService.getCourseStarAvg(pageNo);
		int stdCnt = historyOrderService.getHistoryOrderLectureListByOli_no(pageNo).size();
		
		model.addAttribute("memberService", memberService);
		model.addAttribute("tagService", tagService);
		model.addAttribute("typeService", typeService);
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", teacher);
		model.addAttribute("replys", replys);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("existLike", isExistLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("videos", videos);
		model.addAttribute("contentType", "main");
		model.addAttribute("notes", notes);
		model.addAttribute("isCurrentCourseTeacher", isCurrentCourseTeacher);
		model.addAttribute("mainCategory", courseService.getMainTypeOfCourse(pageNo).getMain_type_name());
		model.addAttribute("subCategoryList", courseService.getCourseSubTypeList(pageNo));
		
		return "course/course_detail";
	}
	
	@RequestMapping("/course/{pageNo}/community")
	public String course_community(HttpServletRequest request, Model model, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		if(courseDTO == null) {
			model.addAttribute("errorMessage", "존재하지 않는 강의입니다.");
			return "errorPage";
		}
		
		TeacherDTO teacher = teacherService.getTeacherInfo(courseDTO.getOlt_no());
		if(courseDTO.getOlt_no() != 0 && teacher == null) {
			model.addAttribute("errorMessage", "존재하지 않는 강사입니다.");
			return "errorPage";
		}
		
		List<CourseTagDTO> tags = courseService.getCourseTags(pageNo);
		int likeCnt = courseService.getCourseLikeCount(pageNo);
		float starAvg = courseService.getCourseStarAvg(pageNo);
		boolean existLike = courseService.existCourseLike(pageNo, memberDTO.getM_no());
		boolean isCurrentCourseTeacher = teacherService.isTeacherOfThisCourse(pageNo, memberDTO.getM_no());
		int stdCnt = historyOrderService.getHistoryOrderLectureListByOli_no(pageNo).size();
		
		String search = request.getParameter("search");
		String classify = request.getParameter("classify");
		if(classify == null) classify = "2"; // default로 질문 게시판 설정
		
		HashMap<String, Object> cbMap = new HashMap<String, Object>();
		cbMap.put("oli_no", pageNo);
		cbMap.put("search", search);
		cbMap.put("classify", Integer.parseInt(classify));
		
		try {
			pagingService = new PagingService(request, cbService.selectCommunityBoardTotalCountByOli_no(cbMap), 10);
		}
		catch(Exception e) {
			System.err.println(e.getStackTrace()[0].getLineNumber()); 
			model.addAttribute("errorMessage", pageNo + "강의의 커뮤니티 게시물의 총 개수를 가져올 수 없습니다.");
			return "errorPage";
		}
		
		cbMap.put("page", pagingService.getNowPage());
		cbMap.put("pageSize", 10);
		List<CommunityBoardDTO> cbList;
		try {
			cbList = cbService.selectCommunityBoardByOli_no(cbMap);
		}
		catch(Exception e) {
			System.err.println(e.getStackTrace()[0].getLineNumber()); 
			model.addAttribute("errorMessage", pageNo + "강의의 커뮤니티를 가져올 수 없습니다.");
			return "errorPage";
		}
		
		model.addAttribute("memberService", memberService);
		model.addAttribute("tagService", tagService);
		model.addAttribute("typeService", typeService);
		model.addAttribute("cbService", cbService);
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", teacher);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("cbList", cbList);
		model.addAttribute("contentType", "community");
		model.addAttribute("paging", pagingService.getPaging());
		model.addAttribute("classify", Integer.parseInt(classify));
		model.addAttribute("isCurrentCourseTeacher", isCurrentCourseTeacher);
		model.addAttribute("mainCategory", courseService.getMainTypeOfCourse(pageNo).getMain_type_name());
		model.addAttribute("subCategoryList", courseService.getCourseSubTypeList(pageNo));
		
		return "course/course_detail";
	}
	
	// 강의 재생 페이지
	@RequestMapping("/course/{oli_no}/play/{order}")
	public String course_play(HttpServletRequest request, Model model, @PathVariable("oli_no") int oli_no, @PathVariable("order") int order) {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			model.addAttribute("errorMessage", "로그인이 필요합니다.");
			return "errorPage";
		}
		
		HistoryOrderLectureDTO hol = historyOrderService.getHistoryOrderLectureByOli_noM_no(oli_no, member.getM_no());
		if(member.getM_authority() != 1 && !teacherService.isTeacherOfThisCourse(oli_no, member.getM_no()) && hol == null) {
			model.addAttribute("errorMessage", "강의를 시청할 권한이 없습니다.");
			return "errorPage";
		}
		
		CourseVideoDTO courseVideoDTO = courseService.getCourseVideoByOli_noAndOrder(oli_no, order);
		if(courseVideoDTO == null) {
			model.addAttribute("errorMessage", "강의의 동영상 정보를 가져올 수 없습니다.");
			return "errorPage";
		}
		
		List<CourseVideoDTO> videoList = courseService.getCourseVideoList(oli_no);
		if(videoList.size() == 0) {
			model.addAttribute("errorMessage", "강의의 동영상 리스트 정보를 가져올 수 없습니다.");
			return "errorPage";
		}
		
		NoteDTO noteDTO = noteService.getNoteByOli_noM_no(oli_no, member.getM_no());
		if(noteDTO != null) {
			NoteArticleDTO noteArticleDTO = noteService.getNoteArticleByN_noOrder(noteDTO.getN_no(), order);
			model.addAttribute("noteArticle", noteArticleDTO);
		}
		
		String search = request.getParameter("search");
		String classify = request.getParameter("classify");
		
		// 커뮤니티 불러오기
		HashMap<String, Object> cbMap = new HashMap<String, Object>();
		cbMap.put("oli_no", oli_no);
		cbMap.put("search", search);
		if(classify != null) cbMap.put("classify", Integer.parseInt(classify));
		else cbMap.put("classify", 2);
		
		try {
			pagingService = new PagingService(request, cbService.selectCommunityBoardTotalCountByOli_no(cbMap), 10);
		}
		catch(Exception e) {
			System.err.println(e.getStackTrace()[0].getLineNumber()); 
			model.addAttribute("errorMessage", oli_no + "강의의 커뮤니티 게시물의 총 개수를 가져올 수 없습니다.");
			return "errorPage";
		}
		cbMap.put("page", pagingService.getNowPage());
		cbMap.put("pageSize", 10);
		List<CommunityBoardDTO> cbList;
		try {
			cbList = cbService.selectCommunityBoardByOli_no(cbMap);
		}
		catch(Exception e) {
			System.err.println(e.getStackTrace()[0].getLineNumber()); 
			model.addAttribute("errorMessage", oli_no + "강의의 커뮤니티를 가져올 수 없습니다.");
			return "errorPage";
		}
		
		
		model.addAttribute("memberService", memberService);
		model.addAttribute("cbService", cbService);
		model.addAttribute("video", courseVideoDTO);
		model.addAttribute("oli_no", oli_no);
		model.addAttribute("order", order);
		model.addAttribute("videoList", videoList);
		model.addAttribute("type", "content");
		model.addAttribute("note", noteDTO);
		model.addAttribute("cbList", cbList);
		
		return "course/course_play";
	}
	
	@ResponseBody
	@RequestMapping(value="/courseClickedLike", produces="application/json; charset=utf8")
	public String clickedLikeInCourse(HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.err.println("로그인이 필요합니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "로그인이 필요합니다.");
			return jsonObject.toString();
		}
		
		int oli_no;
		try {
			oli_no = Integer.parseInt(request.getParameter("oli_no"));
		}
		catch(Exception e) {
			System.err.println("강의를 찾을 수 없습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "강의를 찾을 수 없습니다.");
			return jsonObject.toString();
		}
			
		boolean isExistLike = courseService.existCourseLike(oli_no, member.getM_no());
		
		if(isExistLike == true) {
			courseService.insertCourseLike(oli_no, member.getM_no());
		}
		else {
			courseService.deleteCourseLike(oli_no, member.getM_no());
		}
		
		jsonObject.addProperty("responseCode", "success");
		return jsonObject.toString();
	}
}
