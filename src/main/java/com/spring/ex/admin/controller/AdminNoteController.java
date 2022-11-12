package com.spring.ex.admin.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.admin.service.AdminNoteService;
import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.FileService;
import com.spring.ex.service.HistoryOrderService;
import com.spring.ex.service.NoteService;
import com.spring.ex.service.PagingService;

@Controller
public class AdminNoteController {
	
	@Inject
	private NoteService noteService;
	
	@Inject
	private AdminNoteService adminNoteService;
	
	@Inject
	private FileService fileService;
	
	@Inject
	private HistoryOrderService historyOrderService;
	
	// 노트 관리 대시보드 페이지
	@RequestMapping("/admin/note")
	public String noteDashboard(Model model) {
		List<Map<String, Object>> noteOrderBy7Days = historyOrderService.getNoteOrderBy7Days();
	 	Map<String, Object> todayOrder = noteOrderBy7Days.get(noteOrderBy7Days.size() -1);
		
		model.addAttribute("todayOrder", todayOrder);
		model.addAttribute("noteOrderBy7Days", noteOrderBy7Days);
		 
		return "admin/note/note-dashboard";
	}
	
	// 노트 관리 페이지
	@RequestMapping("/admin/note/notes-management")
	public String notesManagement(HttpServletRequest request, Model model) {
		String searchCategory = request.getParameter("searchCategory");
		String searchKeyword = request.getParameter("searchKeyword");
		
		final int pageSize = 10;
		int coursePostCount = adminNoteService.getAdminNotePostCount(searchCategory, searchKeyword);
		PagingService pagingService = new PagingService(request, coursePostCount, pageSize);
		
		model.addAttribute("paging", pagingService.getPaging());
		model.addAttribute("noteList", adminNoteService.getAdminNoteBoard(searchCategory, searchKeyword, pagingService.getNowPage(), pageSize));
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "admin/course/courses_management";
	}
	
	@RequestMapping("/admin/note/{n_no}")
	public String noteDetailPage(@PathVariable("n_no") int n_no, HttpServletRequest request, Model model) {
		NoteDTO note = noteService.getNote(n_no);
		List<HistoryOrderNoteDTO> honList = historyOrderService.getHistoryOrderNoteByN_no(n_no);
		
		model.addAttribute("note", note);
		model.addAttribute("orderHistoryList", honList);
		
		return "admin/course/course_detail";
	}
	
	@ResponseBody
	@RequestMapping("admin/note/deleteNotes")
	public void deleteNotes(HttpServletRequest request) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return;
		}
		
		if(member.getM_authority() != 1) {
			System.out.println("관리자 권한이 필요합니다.");
			return;
		}
		
		String[] n_noList = request.getParameterValues("n_noList");
		
		for(String _n_no : n_noList) {
			int n_no = Integer.parseInt(_n_no);
			fileService.deleteAllFileOfPost(n_no, 2);
			noteService.deleteNote(n_no);
		}
	}
	
}
