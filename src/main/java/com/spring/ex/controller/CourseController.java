package com.spring.ex.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseFileUploadDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.dto.course.HistoryOrderLectureDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.FileService;
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
	private FileService fileService;
	
	@Inject
	private NoteService noteService;
	
	@Resource(name="imagePath")
	String imagePath;
	
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
	
	//--------------------------------
	// 강의 상세 페이지 관리 부분
	//--------------------------------
	
	// 강의 생성 페이지
	@RequestMapping("/writeCourse")
	public String writeCourse(HttpServletRequest request, Model model) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강사 자격이 없습니다.");
			return "error";
		}
		
		model.addAttribute("actionURL", "/submitCourse");
		
		return "course/course_write";
	}
	
	// 강의 등록 페이지
	@RequestMapping("/submitCourse")
	public String submitCourse(HttpServletRequest request, Model model, @RequestParam("thumbnail") MultipartFile thumbnail) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강의 생성 권한이 없습니다.");
			return "error";
		}
		
		System.out.println("등록 시작");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String[] tags = request.getParameterValues("tags");
		String[] videoTitles = request.getParameterValues("video_titles");
		String[] videoPaths = request.getParameterValues("video_paths");
	
		String img_path = null;
		if(!thumbnail.isEmpty()) { 
			img_path = fileService.uploadFile(thumbnail, "/img/course/uploaded_images");
		}
		
		if(memberDTO == null || title == null || content == null || img_path == null || price == null) {
			System.out.println("빈 칸이 있습니다.");
			return "error";
		}
		
		// 강의 등록
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setOlt_no(teacherDTO.getOlt_no());
		courseDTO.setTitle(title);
		courseDTO.setContent(content);
		courseDTO.setImg_path(img_path);
		courseDTO.setPrice(Integer.parseInt(price));
		courseDTO.setReg_date(new Date(System.currentTimeMillis()));
		courseDTO.setEnable(1);
		
		courseService.submitCourse(courseDTO);
		
		for(String t : tags) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(courseDTO.getOli_no());
			courseTagDTO.setTag(t);
			
			courseService.submitCourseTag(courseTagDTO);
		}
		
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO courseVideoDTO = new CourseVideoDTO();
			courseVideoDTO.setOli_no(courseDTO.getOli_no());
			courseVideoDTO.setTitle(videoTitles[i]);
			courseVideoDTO.setS_file_name(videoPaths[i]);
			
			courseService.submitCourseVideo(courseVideoDTO);
		}
		
		System.out.println("강의 등록 성공");
		System.out.println("등록 내용 : " + courseDTO);
		
		// 임시로 저장된 사진은 로컬에 복사하고, 사용되지 않는 사진은 DB와 서버에서 삭제하는 작업
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		
		// html의 src값을 추출
		List<String> srcList = courseService.convertHtmlToSrcList(content); 
		
		// 서버에 저장된 이미지를 로컬과 DB에 복사
		courseService.copySrcListToLocalAndDB(srcList, courseDTO.getOli_no(), contextRoot);
		
		// 해당 강의의 임시 + 모든 파일의 url 가져오기
		List<String> uploadedUrlList = courseService.selectUrlListByOli_no(courseDTO.getOli_no());
		
		// 현재 강의에 필요한 사진을 제외한 사진은 삭제
		courseService.deleteFileNotInMain(srcList, uploadedUrlList, contextRoot);
		
		return "redirect:/courses/"+courseDTO.getOli_no();
	}
	
	// 강의 수정 페이지
	@RequestMapping("/rewriteCourse")
	public String rewriteCourse(HttpServletRequest request, Model model) {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("강의를 찾을 수 없습니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강사 자격이 없습니다.");
			return "error";
		}
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		if(courseDTO == null) {
			System.out.println("존재하지 않는 강의입니다.");
			return "error";
		}
		
		if(teacherDTO.getOlt_no() != courseDTO.getOlt_no()) {
			System.out.println("강의 수정 권한이 없습니다.");
			return "error";
		}
		
		List<CourseTagDTO> tagList = courseService.getCourseTags(pageNo);
		List<CourseVideoDTO> videoList = courseService.getCourseVideoList(pageNo);
		
		model.addAttribute("course", courseDTO);
		model.addAttribute("tagList", tagList);
		model.addAttribute("videoList", videoList);
		model.addAttribute("courseService", courseService);
		model.addAttribute("actionURL", "/updateCourse");
		return "course/course_write";
	}
	
	@RequestMapping("/updateCourse")
	public String updateCourse(HttpServletRequest request, Model model, @RequestParam("thumbnail") MultipartFile thumbnail) throws Exception {
		String _pageNo = request.getParameter("pageNo");
		if(_pageNo == null) {
			System.out.println("강의를 찾을 수 없습니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강사 자격이 없습니다.");
			return "error";
		}
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		if(courseDTO == null) {
			System.out.println("존재하지 않는 강의입니다.");
			return "error";
		}
		
		if(teacherDTO.getOlt_no() != courseDTO.getOlt_no()) {
			System.out.println("강의 수정 권한이 없습니다.");
			return "error";
		}
		
		System.out.println("수정 시작");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String[] tags = request.getParameterValues("tags");
		String[] videoTitles = request.getParameterValues("video_titles");
		String[] videoPaths = request.getParameterValues("video_paths");
	
		String img_path = null;
		if(!thumbnail.isEmpty()) { 
			img_path = fileService.uploadFile(thumbnail, "/img/course/uploaded_images");
			fileService.deleteFile(courseDTO.getImg_path());
		}
		
		if(memberDTO == null || title == null || content == null || price == null) {
			System.out.println("빈 칸이 있습니다.");
			return "error";
		}
		
		// 강의 수정
		courseDTO.setTitle(title);
		courseDTO.setContent(content);
		if(img_path != null) 
			courseDTO.setImg_path(img_path);
		courseDTO.setPrice(Integer.parseInt(price));
		courseDTO.setReg_date(new Date(System.currentTimeMillis()));
		courseDTO.setEnable(1);
		
		courseService.updateCourse(courseDTO);
		
		courseService.deleteCourseTag(pageNo);
		for(String t : tags) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(courseDTO.getOli_no());
			courseTagDTO.setTag(t);
			
			courseService.submitCourseTag(courseTagDTO);
		}
		
		courseService.deleteCourseVideo(pageNo);
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO courseVideoDTO = new CourseVideoDTO();
			courseVideoDTO.setOli_no(courseDTO.getOli_no());
			courseVideoDTO.setTitle(videoTitles[i]);
			courseVideoDTO.setS_file_name(videoPaths[i]);
			
			courseService.submitCourseVideo(courseVideoDTO);
		}
		
		System.out.println("강의 수정 성공");
		System.out.println("수정 내용 : " + courseDTO);
		
		// 임시로 저장된 사진은 로컬에 복사하고, 사용되지 않는 사진은 DB와 서버에서 삭제하는 작업
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		
		// html의 src값을 추출
		List<String> srcList = courseService.convertHtmlToSrcList(content); 
		
		// 서버에 저장된 이미지를 로컬과 DB에 복사
		courseService.copySrcListToLocalAndDB(srcList, pageNo, contextRoot);
		
		// 해당 강의의 임시 + 모든 파일의 url 가져오기
		List<String> uploadedUrlList = courseService.selectUrlListByOli_no(courseDTO.getOli_no());
		
		// 현재 강의에 필요한 사진을 제외한 사진은 삭제
		courseService.deleteFileNotInMain(srcList, uploadedUrlList, contextRoot);
		
		return "redirect:/courses/"+pageNo;
	}
	
	// 강의 수정 취소
	@RequestMapping("/cancelCourse")
	public void cancelCourse(HttpServletRequest request) throws Exception {
		System.out.println("강의 수정 취소");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		// html의 src값을 추출
		List<String> srcList = courseService.convertHtmlToSrcList(courseDTO.getContent()); 
		
		// 해당 강의의 모든 파일의 url 가져오기
		List<String> uploadedUrlList = courseService.selectUrlListByOli_no(pageNo);
		
		// 임시로 저장된 파일 삭제
		courseService.deleteFileNotInMain(srcList, uploadedUrlList, contextRoot);
	}
	
	// 게시물 삭제
	@RequestMapping("/deleteCourse")
	public String deleteCourse(HttpServletRequest request) throws Exception {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("강의를 찾을 수 없습니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강사 자격이 없습니다.");
			return "error";
		}
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		if(courseDTO == null) {
			System.out.println("존재하지 않는 강의입니다.");
			return "error";
		}
		
		if(teacherDTO.getOlt_no() != courseDTO.getOlt_no()) {
			System.out.println("강의 삭제 권한이 없습니다.");
			return "error";
		}
		
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		// 데이터베이스에서 게시물의 모든 파일 정보 불러오고 삭제하기
		List<CourseFileUploadDTO> fileUploadDTOList = courseService.selectFileListByOli_no(pageNo);
		for(int i = 0; i < fileUploadDTOList.size(); i++) {
			courseService.deleteFileEveryWhere(fileUploadDTOList.get(i).getUrl(), contextRoot);
		}
		
		courseService.deleteFileEveryWhere(courseDTO.getImg_path(), contextRoot);
		
		courseService.deleteCourse(pageNo);
		System.out.println("게시물 삭제 성공");
		
		return "redirect:/courses";
	}
	
	
	// 서버에만 이미지 임시로 저장
	@ResponseBody
	@RequestMapping(value="/courseUploadSummernoteImageFile", produces = "application/json; charset=utf8")
	public String courseUploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request ) throws Exception {
		JsonObject jsonObject = new JsonObject();
		// 내부경로로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/resources");
		String fileRoot = contextRoot + imagePath;
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		File serverFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, serverFile);	// 서버에 파일 저장
			jsonObject.addProperty("url", "/resources" + imagePath + savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
		} catch (IOException e) {
			FileUtils.deleteQuietly(serverFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		} 
		String a = jsonObject.toString();
		return a;
	}
	
	@RequestMapping("/courses/submitReply")
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
	
	// 강의 재생 페이지의 우측 아이콘 클릭했을 때
	@RequestMapping("/courses/clickNav")
	public String clickNav(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		
		model.addAttribute("type", type);
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
	
	// 노트 생성
	@RequestMapping("/courses/createNote")
	public String createNote(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int price = Integer.parseInt(request.getParameter("price"));
		
		NoteDTO noteDTO = new NoteDTO();
		noteDTO.setOli_no(oli_no);
		noteDTO.setM_no(memberDTO.getM_no());
		noteDTO.setTitle(title);
		noteDTO.setContent(content);
		noteDTO.setPrice(price);
		noteDTO.setClassify(0);
		
		noteService.insertNote(noteDTO);
		
		return "redirect:" + request.getHeader("referer");
	}
	
	// 노트 저장
	@RequestMapping("/courses/saveNote")
	public String saveNote(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		NoteDTO noteDTO = noteService.getNoteByOli_noM_no(oli_no, memberDTO.getM_no());
		int olv_no = Integer.parseInt(request.getParameter("olv_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoteArticleDTO noteArticleDTO = noteService.getNoteArticleByN_noOlv_no(noteDTO.getN_no(), olv_no);
		
		if(noteArticleDTO != null) {
			int na_no = Integer.parseInt(request.getParameter("na_no"));
			noteArticleDTO.setNa_no(na_no);
			noteArticleDTO.setTitle(title);
			noteArticleDTO.setContent(content);
			
			noteService.updateNoteArticle(noteArticleDTO);
		}
		else {
			noteArticleDTO = new NoteArticleDTO();
			noteArticleDTO.setN_no(noteDTO.getN_no());
			noteArticleDTO.setOlv_no(olv_no);
			noteArticleDTO.setTitle(title);
			noteArticleDTO.setContent(content);
			
			noteService.insertNoteArticle(noteArticleDTO);
		}
		
		return "redirect:/";
	}
}
