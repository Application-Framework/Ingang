package com.spring.ex.controller.note;

import java.sql.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.note.NoteReplyDTO;
import com.spring.ex.service.NoteService;

@Controller
public class NoteReplyController {
	
	@Inject
	private NoteService noteService;

	// 노트에 수강평 등록
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
	
	// 노트 수강평 수정
	@ResponseBody
	@RequestMapping("/updateNoteReply")
	public void updateNoteReply(HttpServletRequest request) {
		int nr_no = Integer.parseInt(request.getParameter("nr_no"));
		int star_rating = Integer.parseInt(request.getParameter("star_rating"));
		String content = request.getParameter("content");
		
		NoteReplyDTO reply = noteService.getNoteReply(nr_no);
		reply.setStar_rating(star_rating);
		reply.setContent(content);
		
		noteService.updateNoteReply(reply);
	}
	
	// 노트 수강평 삭제
	@ResponseBody
	@RequestMapping("/deleteNoteReply")
	public void deleteNoteReply(HttpServletRequest request) {
		int nr_no = Integer.parseInt(request.getParameter("nr_no"));
		
		noteService.deleteNoteReply(nr_no);
	}
	
}
