package com.spring.ex.controller.note;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.FileService;
import com.spring.ex.service.HistoryOrderService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.NoteService;

@Controller
public class NoteAriticlePostController {
	
	@Inject
	private NoteService noteService;
	
	@Inject
	private HistoryOrderService historyOrderService;
	
	@Inject
	private MemberService memberService;
	
	@Inject
	private FileService fileService;
	
	// 노트 글 출력 페이지
	@RequestMapping("/note_article/{na_no}")
	public String openNoteArticle(HttpServletRequest request, Model model, @PathVariable int na_no) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		NoteArticleDTO noteArticle = noteService.getNoteArticle(na_no);
		if(noteArticle == null) {
			System.out.println("노트 글을 찾을 수 없습니다.");
			return "error";
		}
		
		HistoryOrderNoteDTO historyOrderNote = historyOrderService.getHistoryOrderNoteByN_noM_no(noteArticle.getN_no(), member.getM_no());
		if(historyOrderNote == null) {
			System.out.println("노트를 구매하지 않은 사용자입니다.");
			return "error";
		}
		
		NoteDTO note = noteService.getNote(noteArticle.getN_no());
		MemberDTO author = memberService.getMemberByM_no(note.getM_no());
		
		model.addAttribute("author", author.getM_name());
		model.addAttribute("noteArticle", noteArticle);
		
		return "note_article";
	}
	
	// 노트 글 수정 페이지
	@RequestMapping("/rewriteNoteArticle")
	public String rewriteNoteArticle(HttpServletRequest request, Model model) {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("잘못된 URL 입니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		NoteArticleDTO noteArticle = noteService.getNoteArticle(pageNo);
		if(noteArticle == null) {
			System.out.println("노트 글을 찾을 수 없습니다.");
			return "error";
		}
		
		HistoryOrderNoteDTO historyOrderNote = historyOrderService.getHistoryOrderNoteByN_noM_no(noteArticle.getN_no(), member.getM_no());
		if(historyOrderNote == null) {
			System.out.println("노트를 구매하지 않은 사용자입니다.");
			return "error";
		}
		
		model.addAttribute("noteArticle", noteArticle);
		
		return "note_article_write";
	}
	
	// 노트 글 저장
	@RequestMapping("/saveNoteArticle")
	public void saveNoteArticle(HttpServletRequest request) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return;
		}
		
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		int olv_no = Integer.parseInt(request.getParameter("olv_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if(title == null || content == null) {
			System.out.println("빈 칸이 있습니다.");
			return;
		}
		
		NoteDTO note = noteService.getNoteByOli_noM_no(oli_no, member.getM_no());
		HistoryOrderNoteDTO historyOrderNote = historyOrderService.getHistoryOrderNoteByN_noM_no(note.getN_no(), member.getM_no());
		if(historyOrderNote == null) {
			System.out.println("노트를 구매하지 않은 사용자입니다.");
			return;
		}
		
		NoteArticleDTO noteArticle = noteService.getNoteArticleByN_noOlv_no(note.getN_no(), olv_no);
		
		if(noteArticle != null) {
			int na_no = Integer.parseInt(request.getParameter("na_no"));
			noteArticle.setNa_no(na_no);
			noteArticle.setTitle(title);
			noteArticle.setContent(content);
			
			noteService.updateNoteArticle(noteArticle);
		}
		else {
			noteArticle = new NoteArticleDTO();
			noteArticle.setN_no(note.getN_no());
			noteArticle.setOlv_no(olv_no);
			noteArticle.setTitle(title);
			noteArticle.setContent(content);
			
			noteService.insertNoteArticle(noteArticle);
		}
		
		fileService.manageFileAfterPostSubmission(content, noteArticle.getNa_no(), 3);
	}
	
	@RequestMapping("/deleteNoteArticle")
	public void deleteNoteArticle(HttpServletRequest request) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return;
		}
		
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		int olv_no = Integer.parseInt(request.getParameter("olv_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if(title == null || content == null) {
			System.out.println("빈 칸이 있습니다.");
			return;
		}
		
		NoteDTO note = noteService.getNoteByOli_noM_no(oli_no, member.getM_no());
		HistoryOrderNoteDTO historyOrderNote = historyOrderService.getHistoryOrderNoteByN_noM_no(note.getN_no(), member.getM_no());
		if(historyOrderNote == null) {
			System.out.println("노트를 구매하지 않은 사용자입니다.");
			return;
		}
		
		NoteArticleDTO noteArticle = noteService.getNoteArticleByN_noOlv_no(note.getN_no(), olv_no);
		noteService.deleteNoteArticle(noteArticle.getNa_no());
		fileService.deleteAllFileOfPost(noteArticle.getNa_no(), 3);
	}
	
}
