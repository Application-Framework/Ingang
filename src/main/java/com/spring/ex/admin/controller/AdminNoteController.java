package com.spring.ex.admin.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.admin.service.AdminCourseService;
import com.spring.ex.admin.service.AdminMemberService;
import com.spring.ex.admin.service.AdminNoteService;
import com.spring.ex.admin.service.AdminStatisticsService;
import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.dto.note.NoteReplyDTO;
import com.spring.ex.service.FileService;
import com.spring.ex.service.HistoryOrderService;
import com.spring.ex.service.MemberService;
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
	
	@Inject
	private AdminCourseService adminCourseService;
	
	@Inject
	private AdminMemberService adminMemberService;
	
	@Inject
	private MemberService memberService;
	
	@Inject AdminStatisticsService statisticsService;
	
	// 노트 관리 대시보드 페이지
	@RequestMapping("/admin/note")
	public String noteDashboard(Model model) throws Exception {
		List<Map<String, Object>> noteOrderBy7Days = historyOrderService.getNoteOrderBy7Days();
	 	Map<String, Object> todayOrder = noteOrderBy7Days.get(noteOrderBy7Days.size() -1);
		int todaySubmittedNoteCount = historyOrderService.getTodaySubmittedNoteCount();
		List<HistoryOrderNoteDTO> HistoryOrderNoteDTOTotal = statisticsService.getNoteTotalSell(); // 전체 노트 판매 비율
		
		model.addAttribute("todayOrder", todayOrder);
		model.addAttribute("noteOrderBy7Days", noteOrderBy7Days);
		model.addAttribute("todaySubmittedNoteCount", todaySubmittedNoteCount);
		model.addAttribute("listNoteTotal", HistoryOrderNoteDTOTotal);
		
		return "admin/note/note_dashboard";
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
		
		// 노트 생성에 필요한 데이터
		model.addAttribute("courseList", adminCourseService.getAllCourseList());
		model.addAttribute("memberList", adminMemberService.getAllMemberList());
		
		return "admin/note/notes_management";
	}
	
	@RequestMapping("/admin/note/{n_no}")
	public String noteDetailPage(@PathVariable("n_no") int n_no, HttpServletRequest request, Model model) {
		NoteDTO note = noteService.getNote(n_no);
		int noteLikeCount = noteService.getNoteLikeCount(n_no);
		List<NoteArticleDTO> articles = noteService.getNoteArticleList(n_no);
		List<NoteReplyDTO> replys = noteService.getNoteReplyList(n_no);
		List<HistoryOrderNoteDTO> honList = historyOrderService.getHistoryOrderNoteByN_no(n_no);
		int starAvg = 0;
		if(replys.size() != 0) {
			for(NoteReplyDTO reply : replys)
				 starAvg += reply.getStar_rating();
			starAvg /= replys.size();
		}
		
		model.addAttribute("memberService", memberService);
		
		model.addAttribute("note", note);
		model.addAttribute("noteLikeCount", noteLikeCount);
		model.addAttribute("noteArticleList", articles);
		model.addAttribute("starAvg", starAvg);
		model.addAttribute("noteReplyList", replys);
		model.addAttribute("orderHistoryList", honList);
		
		return "admin/note/note_detail";
	}
	
	// 노트 등록
	@RequestMapping(value="/admin/note/createNote", method=RequestMethod.POST)
	public String createNote(HttpServletRequest request, Model model) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		if(member.getM_authority() != 1) {
			System.out.println("관리자 권한이 필요합니다.");
			return "error";
		}
		
		System.out.println("등록 시작");
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		int m_no = Integer.parseInt(request.getParameter("m_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String _enable = request.getParameter("enable");
		
		if(title == null || content == null || price == null) {
			System.out.println("빈 칸이 있습니다.");
			return "error";
		}
		
		int enable = (_enable == null) ? 0 : 1;
		
		NoteDTO note = new NoteDTO();
		note.setOli_no(oli_no);
		note.setM_no(m_no);
		note.setTitle(title);
		note.setContent(content);
		note.setPrice(Integer.parseInt(price));
		note.setReg_date(new Date(System.currentTimeMillis()));
		note.setClassify(0);
		note.setEnable(enable);
		
		noteService.insertNote(note);
		System.out.println("노트 등록 성공");
		System.out.println("등록 내용 : " + note);

		// 게시글의 파일 관리
		fileService.manageFileAfterPostSubmission(content, note.getN_no(), 2);
		
		return "redirect:" + request.getHeader("referer");
	}
	
	// 노트 수정
	@ResponseBody
	@RequestMapping(value="/admin/note/updateNote", method=RequestMethod.POST)
	public void updateNote(HttpServletRequest request, Model model) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return;
		}
		
		if(member.getM_authority() != 1) {
			System.out.println("관리자 권한이 필요합니다.");
			return;
		}
		
		System.out.println("수정 시작");
		int n_no = Integer.parseInt(request.getParameter("n_no"));
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		int m_no = Integer.parseInt(request.getParameter("m_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String _enable = request.getParameter("enable");
		
		if(title == null || content == null || price == null) {
			System.out.println("빈 칸이 있습니다.");
			return;
		}
		
		int enable = (_enable == null) ? 0 : 1;
		
		NoteDTO note = noteService.getNote(n_no);
		note.setOli_no(oli_no);
		note.setM_no(m_no);
		note.setTitle(title);
		note.setContent(content);
		note.setPrice(Integer.parseInt(price));
		note.setClassify(0);
		note.setEnable(enable);
		
		noteService.updateNote(note);
		System.out.println("노트 수정 성공");

		// 게시글의 파일 관리
		fileService.manageFileAfterPostSubmission(content, note.getN_no(), 2);
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
