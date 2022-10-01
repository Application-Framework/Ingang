package com.spring.ex.controller.note;

import java.sql.Date;
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
	
	// 노트 검색 페이지
	@RequestMapping("/notes")
	public String showNotes(HttpServletRequest request, Model model) {
		String searchTitle = request.getParameter("s");
		String _category_tags = request.getParameter("category_tags");
		List<String> category_tags = null;
		if(_category_tags != null) {
			category_tags = Arrays.asList(_category_tags.split("\\s*,\\s*")); // convert string to separated comma string
			System.out.println("category_tags : " + category_tags);
		}
		String order = request.getParameter("order");
		
		final int pageSize = 28;
		
		System.out.println("searchTitle : " + searchTitle + ", order : " + order);
		
		HashMap<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("searchTitle", searchTitle);
		countMap.put("category_tags", category_tags);
		
		int totalCount = noteService.getNoteTotalCount(countMap);
		
		pagingService = new PagingService(request, totalCount, pageSize);
		
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("page", pagingService.getNowPage());
		pageMap.put("pageSize", pageSize);
		pageMap.put("searchTitle", searchTitle);
		pageMap.put("category_tags", category_tags);
		pageMap.put("order", order);
		
		System.out.println("pagingDTO : " + pagingService.getPaging());
		
		model.addAttribute("paging", pagingService.getPaging()); 
		model.addAttribute("nlist", noteService.getNoteList(pageMap));
		model.addAttribute("nowURL", request.getServletPath());
		model.addAttribute("s", searchTitle);
		model.addAttribute("category_tags", category_tags);
		model.addAttribute("order", order);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("memberService", memberService);
		model.addAttribute("courseService", courseService);
		
		return "note/note_search";
	}
	
	// 노트 상세 페이지
	@RequestMapping("/note/{pageNo}")
	public String note_detail(Model model, HttpServletRequest request, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		NoteDTO noteDTO = noteService.getNote(pageNo);
		CourseDTO courseDTO = courseService.getCourseDetail(noteDTO.getOli_no());
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
		
		model.addAttribute("note", noteDTO);
		model.addAttribute("course", courseDTO);
		model.addAttribute("replys", replys);
		model.addAttribute("tags", tags);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("stdCnt", stdCnt);
		model.addAttribute("memberSerivce", memberService);
		model.addAttribute("existLike", existLike);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("articles", articles);
		model.addAttribute("contentType", "main");
		
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
	
	@RequestMapping("/purchaseNote")
	public String purchaseNote(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int n_no = Integer.parseInt(request.getParameter("n_no"));
		NoteDTO noteDTO = noteService.getNote(n_no);
		
		HistoryOrderNoteDTO historyOrderNoteDTO = new HistoryOrderNoteDTO();
		historyOrderNoteDTO.setN_no(n_no);
		historyOrderNoteDTO.setM_no(memberDTO.getM_no());
		historyOrderNoteDTO.setPayment(noteDTO.getPrice());
		historyOrderNoteDTO.setPayment_status(1);
		
		historyOrderService.insertHistoryOrderNote(historyOrderNoteDTO);
		
		return "redirect:/";
	}
	
}
