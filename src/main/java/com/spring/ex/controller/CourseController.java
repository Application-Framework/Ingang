package com.spring.ex.controller;

import java.sql.Date;
import java.util.ArrayList;
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
import com.spring.ex.dto.course.HistoryOrderLectureDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.FileUploadService;
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
	private FileUploadService fileUploadService;
	
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
		showCourses(request, model, "??? ??????");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/front-end")
	public String coursesFrontEnd(HttpServletRequest request, Model model) {
		showCourses(request, model, "???????????????");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/back-end")
	public String coursesBackEnd(HttpServletRequest request, Model model) {
		showCourses(request, model, "?????????");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/programming-lang")
	public String coursesProgrammingLang(HttpServletRequest request, Model model) {
		showCourses(request, model, "??????????????? ??????");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/database-dev")
	public String coursesDatabaseDev(HttpServletRequest request, Model model) {
		showCourses(request, model, "??????????????????");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/algorithm")
	public String coursesAlgorithm(HttpServletRequest request, Model model) {
		showCourses(request, model, "????????????");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/mobile-app")
	public String coursesMobileApp(HttpServletRequest request, Model model) {
		showCourses(request, model, "????????? ??? ??????");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/artificial-intelligence")
	public String coursesArtificialIntelligence(HttpServletRequest request, Model model) {
		showCourses(request, model, "AI");
		return "course/course_search";
	}
	
	@RequestMapping("/courses/security")
	public String coursesSecurity(HttpServletRequest request, Model model) {
		showCourses(request, model, "??????");
		return "course/course_search";
	}
	
	// ?????? ?????? ?????????
	@RequestMapping("/courses/{pageNo}")
	public String courses_detail(Model model, HttpServletRequest request, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		TeacherDTO courseTeacherDTO = courseService.getTeacherInfo(courseDTO.getOlt_no());
		
		// ????????? ????????? ?????? ????????? ???????????? ??????
		boolean isCurrentCourseTeacher = false;
		if(memberDTO != null) {
			TeacherDTO currentTeacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
			if(currentTeacherDTO != null) {
				if(courseTeacherDTO.getOlt_no() == currentTeacherDTO.getOlt_no()) isCurrentCourseTeacher = true;
			}
		}
		
		List<CourseReplyDTO> replys = courseService.getCourseReplys(pageNo);
		List<CourseTagDTO> tags = courseService.getCourseTags(pageNo);
		List<CourseVideoDTO> videos = courseService.getCourseVideoList(pageNo);
		List<NoteDTO> notes = noteService.getNoteListByOli_no(pageNo);
		
		// ????????? ????????? ??????????????? ??????
		if(memberDTO != null) {
			HistoryOrderLectureDTO historyOrderLectureDTO = courseService.getHistoryOrderLectureByOli_noM_no(pageNo, memberDTO.getM_no());
			boolean purchased = (historyOrderLectureDTO != null) ? true : false;
			model.addAttribute("purchased", purchased);
		}
		
		int likeCnt = courseService.getCourseLikeCount(pageNo);
		int starAvg = 0;
		if(replys.size() != 0) {
			for(CourseReplyDTO reply : replys)
				 starAvg += reply.getStar_rating();
			starAvg /= replys.size();
		}
		int stdCnt = 0;
		boolean existLike; 
		if(courseService.existCourseLike(pageNo, 2) == 1) // m_no ??????
			existLike = true;
		else existLike= false;
		
		
		System.out.println("?????? ?????? ????????? ?????? ??????");
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
	
	@RequestMapping("/courses/{pageNo}/main")
	public String course_main(Model model) {
		model.addAttribute("contentType", "main");
		
		return "course/course_detail";
	}
	
	@RequestMapping("/courses/{pageNo}/community")
	public String course_community(HttpServletRequest request, Model model, @PathVariable int pageNo) throws Exception {
		// ????????? map ?????? ???????????????
		HashMap<String, Object> map = new HashMap<String, Object>(); 
		map.put("checkClass", "chat");
		pagingService = new PagingService(request, cbService.getCommunityBoardTotalCount(map), 10);
		
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("Page", pagingService.getNowPage());
		pageMap.put("PageSize", 10);
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		TeacherDTO teachertDTO = courseService.getTeacherInfo(courseDTO.getOlt_no());
		List<CourseReplyDTO> replys = courseService.getCourseReplys(pageNo);
		List<CourseTagDTO> tags = courseService.getCourseTags(pageNo);
		List<CommunityBoardDTO> cbRegDateList = cbService.getCommunityBoardChatRegDateShowPage(pageMap);
		
		int likeCnt = courseService.getCourseLikeCount(pageNo);
		int starAvg = 0;
		if(replys.size() != 0) {
			for(CourseReplyDTO reply : replys)
				 starAvg += reply.getStar_rating();
			starAvg /= replys.size();
		}
		int stdCnt = 0;
		boolean existLike; 
		if(courseService.existCourseLike(pageNo, 2) == 1) // m_no ??????
			existLike = true;
		else existLike= false;
		
		model.addAttribute("course", courseDTO);
		model.addAttribute("teacher", teachertDTO);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("memberService", memberService);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("cbRegDateList", cbRegDateList);
		model.addAttribute("contentType", "community");
		model.addAttribute("paging", pagingService.getPaging());
		
		return "course/course_detail";
	}
	
	// ?????? ?????? ?????????
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
	
	@RequestMapping("/courses/writeCourse")
	public String writeCourse(HttpServletRequest request, Model model) {
		if(request.getParameter("update") != null) {
			if(request.getParameter("update").equals("true")) {
				int pageNo = Integer.parseInt(request.getParameter("pageNo"));
				
				CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
				List<CourseTagDTO> tagList = courseService.getCourseTags(pageNo);
				List<CourseVideoDTO> videoList = courseService.getCourseVideoList(pageNo);
				
				System.out.println(videoList);
				
				model.addAttribute("course", courseDTO);
				model.addAttribute("tagList", tagList);
				model.addAttribute("videoList", videoList);
				model.addAttribute("courseService", courseService);
			}
		}
		
		return "course/course_write";
	}
	
	@RequestMapping("/courses/updateCourse")
	public String updateCourse(HttpServletRequest request, Model model) {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int price = Integer.parseInt(request.getParameter("price"));
		String[] tags = request.getParameterValues("tags");
		String[] videoTitles = request.getParameterValues("video_titles");
		String[] videoPaths = request.getParameterValues("video_paths");
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		courseDTO.setTitle(title);
		courseDTO.setContent(content);
		courseDTO.setPrice(price);
		
		List<CourseTagDTO> tagList = new ArrayList<CourseTagDTO>();
		for(int i = 0; i < tags.length; i++) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(pageNo);
			courseTagDTO.setTag(tags[i]);
			tagList.add(courseTagDTO);
		}
		
		List<CourseVideoDTO> videoList = new ArrayList<CourseVideoDTO>();
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO courseVideoDTO = new CourseVideoDTO();
			courseVideoDTO.setOli_no(pageNo);
			courseVideoDTO.setTitle(videoTitles[i]);
			courseVideoDTO.setS_file_name(videoPaths[i]);
			videoList.add(courseVideoDTO);
		}
		
		courseService.updateCourse(courseDTO, tagList, videoList);
		
		return "redirect:/courses/"+pageNo;
	}
	
	@RequestMapping("/courses/submitCourse")
	public String submitCourse(HttpServletRequest request, @RequestParam("thumbnail") MultipartFile thumbnail) throws Exception {
		System.out.println("?????? ??????");
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
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
		
		
		// ?????? ??????
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setOlt_no(teacherDTO.getOlt_no());
		courseDTO.setTitle(title);
		courseDTO.setContent(content);
		courseDTO.setImg_path(img_path);
		courseDTO.setPrice(Integer.parseInt(price));
		courseDTO.setReg_date(new Date(System.currentTimeMillis()));
		
		courseService.submitCourse(courseDTO);
		
		for(String t : tags) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(courseDTO.getOli_no());
			courseTagDTO.setTag(t);
			
			courseService.submitCourseTag(courseTagDTO);
		}
		
		
		// ????????? ?????? ??????
		for(int i = 0; i < videoTitles.length; i++) {
			System.out.println("videoPaths:" + videoPaths[i]);
			System.out.println("videoTitles:" + videoTitles[i]);
			CourseVideoDTO courseVideoDTO = new CourseVideoDTO();
			courseVideoDTO.setOli_no(courseDTO.getOli_no());
			courseVideoDTO.setTitle(videoTitles[i]);
			courseVideoDTO.setS_file_name(videoPaths[i]);
			
			courseService.submitCourseVideo(courseVideoDTO);
		}
		
		return "redirect:/courses";
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
	
	// ?????? ?????? ???????????? ?????? ????????? ???????????? ???
	@RequestMapping("/courses/clickNav")
	public String clickNav(HttpServletRequest request, Model model) {
		String type = request.getParameter("type");
		
		model.addAttribute("type", type);
		return "redirect:" + request.getHeader("referer");
	}
	
	// ?????? ??????
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
	
	// ?????? ??????
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
	
	// ?????? ??????
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
