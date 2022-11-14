package com.spring.ex.controller.note;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.dto.note.NoteReplyDTO;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.HistoryOrderService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.NoteService;
import com.spring.ex.service.PagingService;
import com.spring.ex.service.TypeService;
import com.spring.ex.service.t_TagService;

@Controller
public class NoteController {
	private PagingService pagingService;
	
	@Inject
	private NoteService noteService;
	
	@Inject
	private MemberService memberService;
	
	@Inject
	private CourseService courseService;
	
	@Inject
	private HistoryOrderService historyOrderService;
	
	@Inject
	private TypeService typeService;
	
	@Inject
	private t_TagService tagService;
	
	// 노트 검색 페이지
	public String showNotes(HttpServletRequest request, Model model, String main_type, String sub_type) {
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
		
		int totalCount = noteService.getNoteTotalCount(countMap);
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
		
		model.addAttribute("typeService", typeService);
		model.addAttribute("paging", pagingService.getPaging()); 
		model.addAttribute("nlist", noteService.getNoteList(pageMap));
		model.addAttribute("s", searchTitle);
		model.addAttribute("order", order);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("courseService", courseService);
		model.addAttribute("tagList", tagService.getTagList());
		
		return "note/note_search";
	}
	
	// 모든 강의 검색
	@RequestMapping("/notes")
	public String showAllNotes(HttpServletRequest request, Model model) {
		showNotes(request, model, null, null);
		return "note/note_search";
	}
	
	// 메인 타입의 모든 강의 검색
	@RequestMapping("/notes/{main_type}")
	public String showAllTypeOfNotes(HttpServletRequest request, Model model, @PathVariable String main_type) {
		showNotes(request, model, main_type, null);
		return "note/note_search";
	}
	
	// 서브 타입만 검색
	@RequestMapping("/notes/{main_type}/{sub_type}")
	public String showAllNotes(HttpServletRequest request, Model model, @PathVariable String main_type, @PathVariable String sub_type) {
		showNotes(request, model, main_type, sub_type);
		return "note/note_search";
	}
	
	// 노트 상세 페이지
	@RequestMapping("/note/{pageNo}")
	public String note_detail(Model model, HttpServletRequest request, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		NoteDTO noteDTO = noteService.getNote(pageNo);
		CourseDTO course = courseService.getCourseDetail(noteDTO.getOli_no());
		List<NoteReplyDTO> replys = noteService.getNoteReplyList(pageNo);
		List<CourseTagDTO> tags = courseService.getCourseTags(noteDTO.getOli_no());
		List<NoteArticleDTO> articles = noteService.getNoteArticleList(pageNo);
		boolean existLike = false;
		if(memberDTO != null) {
			existLike = (noteService.existNoteLike(pageNo, memberDTO.getM_no()) == 1) ? true : false; 
			HistoryOrderNoteDTO historyOrderNoteDTO = historyOrderService.getHistoryOrderNoteByN_noM_no(pageNo, memberDTO.getM_no());
			boolean purchased = (historyOrderNoteDTO != null) ? true : false;
			model.addAttribute("purchased", purchased);
		}
		
		int likeCnt = noteService.getNoteLikeCount(pageNo);
		int starAvg = 0;
		if(replys.size() != 0) {
			for(NoteReplyDTO reply : replys)
				 starAvg += reply.getStar_rating();
			starAvg /= replys.size();
		}
		int stdCnt = 0;
		
		
		System.out.println("노트 상세 페이지 정보 출력");
		System.out.println(noteDTO);
		System.out.println(replys);
		System.out.println(tags);
		System.out.println(likeCnt);
		System.out.println(articles);
		
		model.addAttribute("memberService", memberService);
		model.addAttribute("tagService", tagService);
		model.addAttribute("typeService", typeService);
		model.addAttribute("note", noteDTO);
		model.addAttribute("course", course);
		model.addAttribute("replys", replys);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("articles", articles);
		model.addAttribute("contentType", "main");
		model.addAttribute("mainCategory", courseService.getMainTypeOfCourse(course.getOli_no()).getMain_type_name());
		model.addAttribute("subCategoryList", courseService.getCourseSubTypeList(course.getOli_no()));
		
		return "note/note_detail";
	}
	

	@RequestMapping("/noteClickedLike")
	public String noteClickedLike(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int n_no = Integer.parseInt(request.getParameter("n_no"));
		String status = request.getParameter("status");
		if(status.equals("true")) {
			noteService.insertNoteLike(n_no, memberDTO.getM_no());
		}
		else {
			noteService.deleteNoteLike(n_no, memberDTO.getM_no());
		}
		
		return "redirect:" + request.getHeader("referer");
	}
}
