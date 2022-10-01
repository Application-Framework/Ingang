package com.spring.ex.controller.note;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.FileService;
import com.spring.ex.service.NoteService;

@Controller
public class NotePostController {
	//-----------------------
	// 노트 소개 게시물 관리 컨트롤러
	//-----------------------
	
	@Inject
	private NoteService noteService;
	
	@Inject
	private FileService fileService;
	
	@Resource(name="noteImagePath")
	String noteImagePath;
	
	// 노트 생성 페이지는 강의 영상 재생 페이지에서 modal로만 진행됨
	
	// 노트 등록
	@RequestMapping("/submitNote")
	public String submitNote(HttpServletRequest request, Model model) throws Exception {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("잘못된 URL 입니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		System.out.println("등록 시작");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String enable = request.getParameter("enable");
		
		if(memberDTO == null || title == null || content == null || price == null || enable == null) {
			System.out.println("빈 칸이 있습니다.");
			return "error";
		}
		
		
		NoteDTO note = noteService.getNote(pageNo);
		// 노트가 없다면 생성
		if(note == null) {
			note = new NoteDTO();
			note.setOli_no(pageNo);
			note.setM_no(memberDTO.getM_no());
			note.setTitle(title);
			note.setContent(content);
			note.setPrice(Integer.parseInt(price));
			note.setReg_date(new Date(System.currentTimeMillis()));
			note.setClassify(0);
			note.setEnable(Integer.parseInt(enable));
			
			noteService.insertNote(note);
			System.out.println("노트 등록 성공");
			System.out.println("등록 내용 : " + note);
		}
		// 노트가 있다면 수정
		else {
			note.setTitle(title);
			note.setContent(content);
			note.setPrice(Integer.parseInt(price));
			note.setReg_date(new Date(System.currentTimeMillis()));
			note.setClassify(0);
			note.setEnable(Integer.parseInt(enable));
			
			noteService.updateNote(note);
			System.out.println("노트 수정 성공");
			System.out.println("수정 내용 : " + note);
		}
		
		// 게시글의 파일 관리
		fileService.manageFileAfterPostSubmission(content, note.getOli_no(), 2);
		
		return "redirect:/" + request.getParameter("successURL");
	}
	
	// rewriteNote
	@RequestMapping("/rewriteNote")
	public String rewriteNote(HttpServletRequest request, Model model) {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("잘못된 URL 입니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		NoteDTO noteDTO = noteService.getNote(pageNo);
		if(noteDTO == null) {
			System.out.println("존재하지 않는 노트입니다.");
			return "error";
		}
		
		if(noteDTO.getM_no() != memberDTO.getM_no() && memberDTO.getM_authority() != 1) {
			System.out.println("노트 수정 권한이 없습니다.");
			return "error";
		}
		
		model.addAttribute("note", noteDTO);
		model.addAttribute("actionURL", "/updateNote");
		return "note/note_write";
	}
	
	// updateNote
	@RequestMapping("/updateNote")
	public String updateNote(HttpServletRequest request, Model model) throws Exception {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("잘못된 URL 입니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		NoteDTO noteDTO = noteService.getNote(pageNo);
		if(noteDTO == null) {
			System.out.println("존재하지 않는 노트입니다.");
			return "error";
		}
		
		if(noteDTO.getM_no() != memberDTO.getM_no() && memberDTO.getM_authority() != 1) {
			System.out.println("노트 수정 권한이 없습니다.");
			return "error";
		}
		
		System.out.println("수정 시작");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String enable = request.getParameter("enable");
		
		if(memberDTO == null || title == null || content == null || price == null || enable == null) {
			System.out.println("빈 칸이 있습니다.");
			return "error";
		}
		
		;
		
		// 게시글의 파일 관리
		fileService.manageFileAfterPostSubmission(content, noteDTO.getOli_no(), 2);
		
		return "redirect:/course/"+pageNo;
	}
	
	// cancelNote
	// 필요 없을 것 같음
	
	// 게시물 삭제
	@RequestMapping("/deleteNote")
	public String deleteNote(HttpServletRequest request) throws Exception {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("잘못된 URL 입니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		NoteDTO noteDTO = noteService.getNote(pageNo);
		if(noteDTO == null) {
			System.out.println("존재하지 않는 노트입니다.");
			return "error";
		}
		
		if(noteDTO.getM_no() != memberDTO.getM_no() && memberDTO.getM_authority() != 1) {
			System.out.println("노트 수정 권한이 없습니다.");
			return "error";
		}
		
		fileService.deleteAllFileOfPost(pageNo, 2);
		noteService.deleteNote(pageNo);
		System.out.println("노트 삭제 성공");
		
		return "redirect:/notes";
	}
	
	// 서버에만 이미지 임시로 저장
	@ResponseBody
	@RequestMapping(value="/noteUploadSummernoteImageFile", produces = "application/json; charset=utf8")
	public String noteUploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request ) throws Exception {
		JsonObject jsonObject = new JsonObject();
		// 내부경로로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/resources");
		String fileRoot = contextRoot + noteImagePath;
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		File serverFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, serverFile);	// 서버에 파일 저장
			jsonObject.addProperty("url", "/resources" + noteImagePath + savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
		} catch (IOException e) {
			FileUtils.deleteQuietly(serverFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		} 
		String a = jsonObject.toString();
		return a;
	}
}
