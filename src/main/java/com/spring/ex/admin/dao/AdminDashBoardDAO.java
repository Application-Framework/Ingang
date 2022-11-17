package com.spring.ex.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;

@Repository
public interface AdminDashBoardDAO {
	
	//오늘의 방문자 수
	public int getTodayVisitorsCount() throws Exception;

	//오늘의 가입 회원 수
	public int getTodayRegisterMemberTotalCount() throws Exception;
	
	//오늘의 게시글 작성 수
	public int getTodayBoardWriteTotalCount() throws Exception;
	
	//오늘의 강의 총 매출
	public int getTodayCourseTotalSales() throws Exception;
	
	//주간 강의 매출
	public int getWeekCourseTotalSales() throws Exception;
	
	// 주간 강의 매출 그래프
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureWeek() throws Exception;
	
	// 주간 노트 매출 그래프
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteWeek() throws Exception;
	
}