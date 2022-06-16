package com.spring.ex.controller;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.note.HistoryOrderNoteDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.dto.note.NoteReplyDTO;
import com.spring.ex.service.CourseService;
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
	
	// 노트 검색 페이지
	void showNotes(HttpServletRequest request, Model model, String tag) {
		String keyword = request.getParameter("keyword");
		String order = request.getParameter("order");
		String tagParam = request.getParameter("tag");
				
		keyword = (keyword == null) ? "" : keyword;
		order = (order == null) ? "" : order;
		
		String keywordParam = (keyword != "") ? "keyword="+keyword+"&" : "";
		String orderParam = (order != "") ? "order="+order+"&" : "";
		
		final int pageSize = 12;
		
		System.out.println("keyword : " + keyword + ", tag : " + tag);
		
		int totalCount = noteService.getNoteTotalCount(keyword, tag, tagParam);
		
		pagingService = new PagingService(request, totalCount, pageSize);
		
		System.out.println("pagingDTO : " + pagingService.getPaging());
		
		model.addAttribute("paging", pagingService.getPaging()); 
		model.addAttribute("nlist", noteService.getNoteList(keyword, tag, tagParam, order, pagingService.getNowPage(), pageSize));
		model.addAttribute("nowURL", request.getServletPath());
		model.addAttribute("keyword", keyword);
		model.addAttribute("order", order);
		model.addAttribute("keywordParam", keywordParam);
		model.addAttribute("orderParam", orderParam);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("memberService", memberService);
		model.addAttribute("courseService", courseService);		
	}
	
	@RequestMapping("/notes")
	public String notes(HttpServletRequest request, Model model) {
		showNotes(request, model, null);
		return "note/note_search";
	}
	
	@RequestMapping("/notes/web-dev")
	public String notesWebDev(HttpServletRequest request, Model model) {
		showNotes(request, model, "웹 개발");
		return "note/note_search";
	}
	
	@RequestMapping("/notes/front-end")
	public String notesFrontEnd(HttpServletRequest request, Model model) {
		showNotes(request, model, "프론트엔드");
		return "note/note_search";
	}
	
	@RequestMapping("/notes/back-end")
	public String notesBackEnd(HttpServletRequest request, Model model) {
		showNotes(request, model, "백엔드");
		return "note/note_search";
	}
	
	@RequestMapping("/notes/programming-lang")
	public String notesProgrammingLang(HttpServletRequest request, Model model) {
		showNotes(request, model, "프로그래밍 언어");
		return "note/note_search";
	}
	
	@RequestMapping("/notes/database-dev")
	public String notesDatabaseDev(HttpServletRequest request, Model model) {
		showNotes(request, model, "데이터베이스");
		return "note/note_search";
	}
	
	@RequestMapping("/notes/algorithm")
	public String notesAlgorithm(HttpServletRequest request, Model model) {
		showNotes(request, model, "알고리즘");
		return "note/note_search";
	}
	
	@RequestMapping("/notes/mobile-app")
	public String notesMobileApp(HttpServletRequest request, Model model) {
		showNotes(request, model, "모바일 앱 개발");
		return "note/note_search";
	}
	
	@RequestMapping("/notes/artificial-intelligence")
	public String notesArtificialIntelligence(HttpServletRequest request, Model model) {
		showNotes(request, model, "AI");
		return "note/note_search";
	}
	
	@RequestMapping("/notes/security")
	public String notesSecurity(HttpServletRequest request, Model model) {
		showNotes(request, model, "보안");
		return "note/note_search";
	}
	
	// 노트 상세 페이지
	@RequestMapping("/notes/{pageNo}")
	public String notes_detail(Model model, HttpServletRequest request, @PathVariable int pageNo) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		NoteDTO noteDTO = noteService.getNote(pageNo);
		CourseDTO courseDTO = courseService.getCourseDetail(noteDTO.getOli_no());
		String writerName = memberService.getNameByM_no(noteDTO.getM_no());
		List<NoteReplyDTO> replys = noteService.getNoteReplyList(pageNo);
		List<CourseTagDTO> tags = courseService.getCourseTags(noteDTO.getOli_no());
		List<NoteArticleDTO> articles = noteService.getNoteArticleList(pageNo);
		if(memberDTO != null) {
			HistoryOrderNoteDTO historyOrderNoteDTO = noteService.getHistoryOrderNoteByN_noM_no(pageNo, memberDTO.getM_no());
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
		boolean existLike; 
		if(noteService.existNoteLike(pageNo, 2) == 1) // m_no 임시
			existLike = true;
		else existLike= false;
		
		
		System.out.println("노트 상세 페이지 정보 출력");
		System.out.println(noteDTO);
		System.out.println(writerName);
		System.out.println(replys);
		System.out.println(tags);
		System.out.println(likeCnt);
		System.out.println(articles);
		
		model.addAttribute("note", noteDTO);
		model.addAttribute("course", courseDTO);
		model.addAttribute("writerName", writerName);
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
	
	@RequestMapping("/notes/submitReply")
	public String submitReply(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		String n_no = request.getParameter("pageNo");
		String star_rating = request.getParameter("star_rating");
		String content = request.getParameter("content");
		
		if(memberDTO == null || n_no == null || star_rating == "" || content == null) {
			return "error";
		}
		
		NoteReplyDTO noteReplyDTO = new NoteReplyDTO();
		noteReplyDTO.setN_no(Integer.parseInt(n_no));
		noteReplyDTO.setM_no(memberDTO.getM_no());
		noteReplyDTO.setStar_rating(Integer.parseInt(star_rating));
		noteReplyDTO.setContent(content);
		noteReplyDTO.setReg_date(new Date(System.currentTimeMillis()));
		
		noteService.submitNoteReply(noteReplyDTO);
		return "redirect:" + request.getHeader("referer");
	}
	
	@RequestMapping("/notes/noteClickedLike")
	public String clickedLikeInNote(HttpServletRequest request) {
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
	
	@RequestMapping("/notes/purchaseNotes")
	public String purchaseNotes(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int n_no = Integer.parseInt(request.getParameter("n_no"));
		NoteDTO noteDTO = noteService.getNote(n_no);
		
		HistoryOrderNoteDTO historyOrderNoteDTO = new HistoryOrderNoteDTO();
		historyOrderNoteDTO.setN_no(n_no);
		historyOrderNoteDTO.setM_no(memberDTO.getM_no());
		historyOrderNoteDTO.setPayment(noteDTO.getPrice());
		historyOrderNoteDTO.setPayment_status(1);
		
		noteService.insertHistoryOrderNote(historyOrderNoteDTO);
		
		return "redirect:/";
	}
	
}
