package com.spring.ex.controller.note;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
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
	
	@Resource(name = "noteArticleImagePath")
	private String noteArticleImagePath;
	
	@Inject
	private NoteService noteService;
	
	@Inject
	private HistoryOrderService historyOrderService;
	
	@Inject
	private MemberService memberService;
	
	@Inject
	private FileService fileService;
	
	//---------------------------------
	// 강의 영상에서 노트 글 관리
	//---------------------------------
	
	
	
	//---------------------------------
	// 노트 메인 페이지에서 노트 글 관리
	//---------------------------------
	
	// 노트 글 출력 페이지
	@RequestMapping("/note_article/{na_no}")
	public String openNoteArticle(HttpServletRequest request, Model model, @PathVariable int na_no) throws Exception {
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.out.println("로그인이 필요합니다.");
			return "errorPage";
		}
		
		NoteArticleDTO noteArticle = noteService.getNoteArticle(na_no);
		if(noteArticle == null) {
			System.out.println("노트 글을 찾을 수 없습니다.");
			return "errorPage";
		}
		
		HistoryOrderNoteDTO historyOrderNote = historyOrderService.getHistoryOrderNoteByN_noM_no(noteArticle.getN_no(), member.getM_no());
		if(historyOrderNote == null) {
			System.out.println("노트를 구매하지 않은 사용자입니다.");
			return "errorPage";
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
	@ResponseBody
	@RequestMapping(value="/saveNoteArticle", produces="application/json; charset=utf8")
	public String saveNoteArticle(HttpServletRequest request) throws Exception {
		JsonObject jsonObject = new JsonObject();
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.err.println("로그인이 필요합니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "로그인이 필요합니다.");
			return jsonObject.toString();
		}
		
		int oli_no;
		try {
			oli_no = Integer.parseInt(request.getParameter("oli_no"));
		}
		catch(Exception e) {
			System.err.println("강의번호가 올바르지 않습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "강의번호가 올바르지 않습니다.");
			return jsonObject.toString();
		}
		
		int order;
		try {
			order = Integer.parseInt(request.getParameter("order"));
		}
		catch(Exception e) {
			System.err.println("노트 order번호가 올바르지 않습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "노트 order번호가 올바르지 않습니다.");
			return jsonObject.toString();
		}
		 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if(title == null || content == null) {
			System.err.println("빈 칸이 있습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "빈 칸이 있습니다.");
			return jsonObject.toString();
		}
		
		NoteDTO note = noteService.getNoteByOli_noM_no(oli_no, member.getM_no());
		if(note == null) {
			System.err.println("노트 정보를 가져올 수 없습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "노트 정보를 가져올 수 없습니다.");
			return jsonObject.toString();
		}
		
		NoteArticleDTO noteArticle = noteService.getNoteArticleByN_noOrder(note.getN_no(), order);
		if(noteArticle == null) {
			noteArticle = new NoteArticleDTO();
			noteArticle.setN_no(note.getN_no());
			noteArticle.setOrder(order);
			noteArticle.setTitle(title);
			noteArticle.setContent(content);
			
			noteService.insertNoteArticle(noteArticle);
			System.out.println("노트 글 추가 : " + noteArticle);
		}
		else {
			noteArticle.setN_no(note.getN_no());
			noteArticle.setOrder(order);
			noteArticle.setTitle(title);
			noteArticle.setContent(content);
			
			noteService.updateNoteArticle(noteArticle);
			System.out.println("노트 글 수정 : " + noteArticle);
		}
		
		fileService.manageFileAfterPostSubmission(content, noteArticle.getNa_no(), 3);
		
		jsonObject.addProperty("responseCode", "success");
		return jsonObject.toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteNoteArticle", produces="application/json; charset=utf8")
	public String deleteNoteArticle(HttpServletRequest request) throws Exception {
		JsonObject jsonObject = new JsonObject();
		MemberDTO member = (MemberDTO)request.getSession().getAttribute("member");
		if(member == null) {
			System.err.println("로그인이 필요합니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "로그인이 필요합니다.");
			return jsonObject.toString();
		}
		
		int oli_no;
		try {
			oli_no = Integer.parseInt(request.getParameter("oli_no"));
		}
		catch(Exception e) {
			System.err.println("강의번호가 올바르지 않습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "강의번호가 올바르지 않습니다.");
			return jsonObject.toString();
		}
		
		int order;
		try {
			order = Integer.parseInt(request.getParameter("order"));
		}
		catch(Exception e) {
			System.err.println("노트 order번호가 올바르지 않습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "노트 order번호가 올바르지 않습니다.");
			return jsonObject.toString();
		}
		
		NoteDTO note = noteService.getNoteByOli_noM_no(oli_no, member.getM_no());
		if(note == null) {
			System.err.println("노트 정보를 가져올 수 없습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "노트 정보를 가져올 수 없습니다.");
			return jsonObject.toString();
		}
		
		NoteArticleDTO noteArticle = noteService.getNoteArticleByN_noOrder(note.getN_no(), order);
		if(note.getM_no() != member.getM_no() && member.getM_authority() != 1) {
			System.err.println("노트의 작성자가 아닙니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "노트의 작성자가 아닙니다.");
			return jsonObject.toString();
		}
		noteService.deleteNoteArticle(noteArticle.getNa_no());
		fileService.deleteAllFileOfPost(noteArticle.getNa_no(), 3);
		
		jsonObject.addProperty("responseCode", "success");
		return jsonObject.toString();
	}
	
	// 서버에만 이미지 임시로 저장
	@ResponseBody
	@RequestMapping(value="/uploadSummernoteImageFileOfNoteArticle", produces = "application/json; charset=utf8")
	public String noteUploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request ) throws Exception {
		JsonObject jsonObject = new JsonObject();
		String url = null;
		try {
			url = fileService.insertFileToServer(multipartFile, noteArticleImagePath);
			System.out.println("url : " + url);
			jsonObject.addProperty("url", url); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
		} catch (IOException e) {
			fileService.deleteFileToLocalAndServer(url);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		} 
		String a = jsonObject.toString();
		return a;
	}
}
