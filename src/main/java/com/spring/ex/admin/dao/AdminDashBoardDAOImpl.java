package com.spring.ex.admin.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;

@Repository
public class AdminDashBoardDAOImpl implements AdminDashBoardDAO {

	@Inject SqlSession sqlSession;
	private static final String namespace = "com.spring.ex.AdminDashBoardMapper";
	private static final String statisticsNamespace = "com.spring.ex.AdminStatisticsMapper";
	
	//오늘의 방문자 수
	@Override
	public int getTodayVisitorsCount() throws Exception {
		return sqlSession.selectOne(statisticsNamespace + ".getTodayVisitorsCount");
	}
	
	//오늘의 게시글 작성 수
	@Override
	public int getTodayBoardWriteTotalCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getTodayBoardWriteTotalCount");
	}
	
	//오늘의 가입 회원 수
	@Override
	public int getTodayRegisterMemberTotalCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getTodayRegisterMemberTotalCount");
	}

	//오늘의 강의 총 매출
	@Override
	public int getTodayCourseTotalSales() throws Exception {
		return sqlSession.selectOne(namespace + ".getTodayCourseTotalSales");
	}
	
	//주간 강의 총 매출
	@Override
	public int getWeekCourseTotalSales() throws Exception {
		return sqlSession.selectOne(namespace + ".getWeekCourseTotalSales");
	}

	// 주간 강의 매출 그래프
	@Override
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureWeek() throws Exception {
		return sqlSession.selectList(namespace + ".getHistoryOrderLectureWeek");
	}
	
	// 주간 노트 매출 그래프
	@Override
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteWeek() throws Exception {
		return sqlSession.selectList(namespace + ".getHistoryOrderNoteWeek");
	}

}