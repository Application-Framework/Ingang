package com.spring.ex.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.HistoryOrderService;
import com.spring.ex.service.NoteService;

@Controller
public class PurchaseController {
	
	@Inject
	HistoryOrderService historyOrderService;
	
	@Inject
	private CourseService courseService;
	
	@Inject
	private NoteService noteService;
	
	@ResponseBody
	@RequestMapping(value="/purchaseCourse", method=RequestMethod.POST, produces="application/json; charset=utf8")
	public String purchaseCourse(HttpServletRequest request, Model model) throws Exception {
		JsonObject jsonObject = new JsonObject();
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.err.println("로그인이 필요합니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "로그인이 필요합니다.");
			return jsonObject.toString();
		}
		
		String _oli_no = request.getParameter("oli_no");
		if(_oli_no == null) {
			System.err.println("강의번호값이 null입니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "강의번호값이 null입니다.");
			return jsonObject.toString();
		}
		int oli_no = Integer.parseInt(_oli_no);
		
		CourseDTO courseDTO = courseService.getCourseDetail(oli_no);
		if(courseDTO == null) {
			System.err.println("강의를 찾을 수 없습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "강의를 찾을 수 없습니다.");
			return jsonObject.toString();
		}
		
		HistoryOrderLectureDTO historyOrderLectureDTO = new HistoryOrderLectureDTO();
		historyOrderLectureDTO.setOli_no(oli_no);
		historyOrderLectureDTO.setM_no(memberDTO.getM_no());
		historyOrderLectureDTO.setPayment(courseDTO.getPrice());
		historyOrderLectureDTO.setPayment_status(1);
		
		try {
			historyOrderService.insertHistoryOrderLecture(historyOrderLectureDTO);
			System.out.println("강의 구매 성공 : " + historyOrderLectureDTO);
			jsonObject.addProperty("responseCode", "success");
			return jsonObject.toString();
		}
		catch(Exception e) {
			System.err.println("강의 구매 실패");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "강의 구매 실패");
			return jsonObject.toString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/purchaseNote", method=RequestMethod.POST, produces="application/json; charset=utf8")
	public String purchaseNote(HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.err.println("로그인이 필요합니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "로그인이 필요합니다.");
			return jsonObject.toString();
		}
		String _n_no = request.getParameter("n_no");
		if(_n_no == null) {
			System.err.println("노트번호값이 null입니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "노트번호값이 null입니다.");
			return jsonObject.toString();
		}
		int n_no = Integer.parseInt(_n_no);
		
		NoteDTO noteDTO = noteService.getNote(n_no);
		if(noteDTO == null) {
			System.err.println("노트를 찾을 수 없습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "노트를 찾을 수 없습니다.");
			return jsonObject.toString();
		}
		
		HistoryOrderNoteDTO historyOrderNoteDTO = new HistoryOrderNoteDTO();
		historyOrderNoteDTO.setN_no(n_no);
		historyOrderNoteDTO.setM_no(memberDTO.getM_no());
		historyOrderNoteDTO.setPayment(noteDTO.getPrice());
		historyOrderNoteDTO.setPayment_status(1);
		
		try {
			historyOrderService.insertHistoryOrderNote(historyOrderNoteDTO);
			System.out.println("노트 구매 성공 : " + historyOrderNoteDTO);
			jsonObject.addProperty("responseCode", "success");
			return jsonObject.toString();
		}
		catch(Exception e) {
			System.err.println("노트 구매 실패");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "노트 구매 실패");
			return jsonObject.toString();
		}
	}
}
