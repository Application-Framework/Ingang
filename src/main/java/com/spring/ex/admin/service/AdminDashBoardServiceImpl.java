package com.spring.ex.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.admin.dao.AdminDashBoardDAO;
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;

@Service
public class AdminDashBoardServiceImpl implements AdminDashBoardService {

	@Inject AdminDashBoardDAO dao;
	
	//오늘의 방문자 수
	@Override
	public int getTodayVisitorsCount() throws Exception {
		return dao.getTodayVisitorsCount();
	}
	
	//오늘의 가입 회원 수
	@Override
	public int getTodayRegisterMemberTotalCount() throws Exception {
		return dao.getTodayRegisterMemberTotalCount();
	}
	
	//오늘의 게시글 작성 수
	@Override
	public int getTodayBoardWriteTotalCount() throws Exception {
		return dao.getTodayBoardWriteTotalCount();
	}
	
	//오늘의 강의 총 매출
	public int getTodayCourseTotalSales() throws Exception {
		return dao.getTodayCourseTotalSales();
	}
	
	//주간 강의 총 매출
	public int getWeekCourseTotalSales() throws Exception {
		return dao.getWeekCourseTotalSales();
	}

	//주간 강의 매출 그래프
	@Override
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureWeek() throws Exception {
		return dao.getHistoryOrderLectureWeek();
	}
	
	//주간 강의 매출 그래프
	@Override
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteWeek() throws Exception {
		return dao.getHistoryOrderNoteWeek();
	}

}