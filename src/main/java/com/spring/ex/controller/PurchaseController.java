package com.spring.ex.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value="/purchaseCourse", method=RequestMethod.POST)
	public String purchaseCourse(HttpServletRequest request) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int oli_no = Integer.parseInt(request.getParameter("oli_no"));
		CourseDTO courseDTO = courseService.getCourseDetail(oli_no);
		
		HistoryOrderLectureDTO historyOrderLectureDTO = new HistoryOrderLectureDTO();
		historyOrderLectureDTO.setOli_no(oli_no);
		historyOrderLectureDTO.setM_no(memberDTO.getM_no());
		historyOrderLectureDTO.setPayment(courseDTO.getPrice());
		historyOrderLectureDTO.setPayment_status(1);
		try {
			historyOrderService.insertHistoryOrderLecture(historyOrderLectureDTO);
			System.out.println("강의 구매 성공 : " + historyOrderLectureDTO);
			return "success";
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/purchaseNote", method=RequestMethod.POST)
	public String purchaseNote(HttpServletRequest request) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		int n_no = Integer.parseInt(request.getParameter("n_no"));
		NoteDTO noteDTO = noteService.getNote(n_no);
		
		HistoryOrderNoteDTO historyOrderNoteDTO = new HistoryOrderNoteDTO();
		historyOrderNoteDTO.setN_no(n_no);
		historyOrderNoteDTO.setM_no(memberDTO.getM_no());
		historyOrderNoteDTO.setPayment(noteDTO.getPrice());
		historyOrderNoteDTO.setPayment_status(1);
		
		try {
			historyOrderService.insertHistoryOrderNote(historyOrderNoteDTO);
			System.out.println("노트 구매 성공 : " + historyOrderNoteDTO);
			return "success";
		}
		catch(Exception e) {
			return null;
		}
	}
}
