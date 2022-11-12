package com.spring.ex.admin.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.admin.service.AdminDashBoardService;
import com.spring.ex.admin.service.AdminStatisticsService;
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;

@Controller 
public class AdminDashBoardController {
	
	@Inject AdminDashBoardService service;
	
	@Inject AdminStatisticsService statisticsService;
	
	//관리자 페이지 메인 대시보드
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public String AdminDashBoard(HttpSession session, Model model) throws Exception {
		
		model.addAttribute("todayVisitorsCount", service.getTodayVisitorsCount());
		model.addAttribute("todayRegisterMemberTotalCount", service.getTodayRegisterMemberTotalCount());
		model.addAttribute("todayBoardWriteTotalCount", service.getTodayBoardWriteTotalCount());
		model.addAttribute("TodayCourseTotalSales", service.getTodayCourseTotalSales());
		model.addAttribute("WeekCourseTotalSales", service.getWeekCourseTotalSales());
		
		List<HistoryOrderLectureDTO> historyOrderLectureDTO = service.getHistoryOrderLectureWeek();
		List<HistoryOrderNoteDTO> historyOrderNoteDTO = service.getHistoryOrderNoteWeek();
		
		// 판매 비율 통계
		List<HistoryOrderLectureDTO> HistoryOrderLectureDTOTotal = statisticsService.getCourseTotalSell(); // 전체 강의 판매 비율
		List<HistoryOrderNoteDTO> HistoryOrderNoteDTOTotal = statisticsService.getNoteTotalSell(); // 전체 노트 판매 비율
		model.addAttribute("NoteTotalSellCount", statisticsService.getNoteTotalSellCount());
				
		System.out.println("노트 판매 비율 : " + HistoryOrderNoteDTOTotal);
				
		// 판매 비율 통계
		model.addAttribute("listCourseTotal", HistoryOrderLectureDTOTotal);
		model.addAttribute("listNoteTotal", HistoryOrderNoteDTOTotal);
		
		model.addAttribute("list", historyOrderLectureDTO);
		model.addAttribute("listNote", historyOrderNoteDTO);
		
		return "admin/index";
	}
	
}