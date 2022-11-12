package com.spring.ex.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberConnectLogDTO;

@Service
public interface AdminStatisticsService {
	
	//오늘의 방문자 수
	public int getTodayVisitorsCount() throws Exception;

	//주간 방문자 수
	public int getWeekVisitorsCount() throws Exception;
	
	//한 달 간 방문자 수
	public int getMonthVisitorsCount() throws Exception;
		
	//일 년간 방문자 수
	public int getYearVisitorsCount() throws Exception;
		
	//주간 방문자 수 그래프
	public List<MemberConnectLogDTO> getWeekVisitorsGraph() throws Exception;
	
	//한 달간 방문자 수 그래프
	public List<MemberConnectLogDTO> getMonthVisitorsGraph() throws Exception;
	
	//일 년간 방문자 수 그래프
	public List<MemberConnectLogDTO> getYearVisitorsGraph() throws Exception;
	
	//전체 기간 강의 판매 비율
	public List<HistoryOrderLectureDTO> getCourseTotalSell() throws Exception;
		
	//전체 기간 노트 판매 비율
	public List<HistoryOrderNoteDTO> getNoteTotalSell() throws Exception;
	
	//판매된 노트 종류 수
	public int getNoteTotalSellCount() throws Exception;
}