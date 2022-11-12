package com.spring.ex.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.admin.dao.AdminStatisticsDAO;
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberConnectLogDTO;

@Service
public class AdminStatisticsServiceImpl implements AdminStatisticsService {

	@Inject AdminStatisticsDAO adminStatisticsDAO;
	
	//주간 방문자 수
	@Override
	public int getTodayVisitorsCount() throws Exception {
		return adminStatisticsDAO.getTodayVisitorsCount();
	}
	
	//주간 방문자 수
	@Override
	public int getWeekVisitorsCount() throws Exception {
		return adminStatisticsDAO.getWeekVisitorsCount();
	}

	//한 달 간 방문자 수
	@Override
	public int getMonthVisitorsCount() throws Exception {
		return adminStatisticsDAO.getMonthVisitorsCount();
	}

	//일 년간 방문자 수
	@Override
	public int getYearVisitorsCount() throws Exception {
		return adminStatisticsDAO.getYearVisitorsCount();
	}

	//주간 방문자 수 그래프
	@Override
	public List<MemberConnectLogDTO> getWeekVisitorsGraph() throws Exception {
		return adminStatisticsDAO.getWeekVisitorsGraph();
	}
	
	//한 달간 방문자 수 그래프
	@Override
	public List<MemberConnectLogDTO> getMonthVisitorsGraph() throws Exception {
		return adminStatisticsDAO.getMonthVisitorsGraph();
	}

	//한 달간 방문자 수 그래프
	@Override
	public List<MemberConnectLogDTO> getYearVisitorsGraph() throws Exception {
		return adminStatisticsDAO.getYearVisitorsGraph();
	}
	
	//전체 기간 강의 판매 비율
	@Override
	public List<HistoryOrderLectureDTO> getCourseTotalSell() throws Exception {
		return adminStatisticsDAO.getCourseTotalSell();
	}
		
	//전체 기간 노트 판매 비율
	@Override
	public List<HistoryOrderNoteDTO> getNoteTotalSell() throws Exception {
		return adminStatisticsDAO.getNoteTotalSell();
	}
	
	//판매된 노트 종류 수
	@Override
	public int getNoteTotalSellCount() throws Exception {
		return adminStatisticsDAO.getNoteTotalSellCount();
	}
}
