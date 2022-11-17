package com.spring.ex.admin.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.admin.service.AdminStatisticsService;
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberConnectLogDTO;

@Controller 
@RequestMapping(value = "admin/")
public class AdminStatisticsController {
	
	@Inject AdminStatisticsService statisticsService;
	
	private String url = "admin/statistics/";
	
	//관리자 페이지 메인 대시보드
	@RequestMapping(value = "/visit", method = RequestMethod.GET)
	public String AdminStatisticsBoard(HttpSession session, Model model) throws Exception {
		
		model.addAttribute("todayVisitorsCount", statisticsService.getTodayVisitorsCount());
		model.addAttribute("weekVisitorsCount", statisticsService.getWeekVisitorsCount());
		model.addAttribute("monthVisitorsCount", statisticsService.getMonthVisitorsCount());
		model.addAttribute("yearVisitorsCount", statisticsService.getYearVisitorsCount());
		
		// 접속자 그래프 통계
		List<MemberConnectLogDTO> MemberConnectLogDTOWeek = statisticsService.getWeekVisitorsGraph(); // 주간 접속자 수
		List<MemberConnectLogDTO> MemberConnectLogDTOMonth = statisticsService.getMonthVisitorsGraph(); // 한 달 간 접속자 수
		List<MemberConnectLogDTO> MemberConnectLogDTOYear = statisticsService.getYearVisitorsGraph(); // 일 년 간 접속자 수
		
		// 접속자 통계
		model.addAttribute("listWeek", MemberConnectLogDTOWeek);
		model.addAttribute("listMonth", MemberConnectLogDTOMonth);
		model.addAttribute("listYear", MemberConnectLogDTOYear);
		
		
		
		return url + "visit";
	}
	
}