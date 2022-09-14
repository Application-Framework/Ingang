package com.spring.ex.controller.note;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.NoteService;

@Controller
public class NotePostController {
	//-----------------------
	// 노트 소개 게시물 관리 컨트롤러
	//-----------------------
	
	@Inject
	private NoteService noteService;
	
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
