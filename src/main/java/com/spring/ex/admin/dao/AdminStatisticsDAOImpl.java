package com.spring.ex.admin.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;
import com.spring.ex.dto.MemberConnectLogDTO;

@Repository
public class AdminStatisticsDAOImpl implements AdminStatisticsDAO {

	@Inject SqlSession sqlSession;
	
	private static final String namespace = "com.spring.ex.AdminStatisticsMapper";
	
	//오늘 방문자 수
	@Override
	public int getTodayVisitorsCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getTodayVisitorsCount");
	}
	
	//주간 방문자 수
	@Override
	public int getWeekVisitorsCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getWeekVisitorsCount");
	}

	//한 달 간 방문자 수
	@Override
	public int getMonthVisitorsCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getMonthVisitorsCount");
	}

	//일 년간 방문자 수
	@Override
	public int getYearVisitorsCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getYearVisitorsCount");
	}

	//주간 방문자수 그래프
	@Override
	public List<MemberConnectLogDTO> getWeekVisitorsGraph() throws Exception {
		return sqlSession.selectList(namespace + ".getWeekVisitorsGraph");
	}
	
	//한 달간 방문자수 그래프
	@Override
	public List<MemberConnectLogDTO> getMonthVisitorsGraph() throws Exception {
		return sqlSession.selectList(namespace + ".getMonthVisitorsGraph");
	}
	
	//일 년간 방문자수 그래프
	@Override
	public List<MemberConnectLogDTO> getYearVisitorsGraph() throws Exception {
		return sqlSession.selectList(namespace + ".getYearVisitorsGraph");
	}
	
	//전체 기간 강의 판매 비율
	@Override
	public List<HistoryOrderLectureDTO> getCourseTotalSell() throws Exception {
		return sqlSession.selectList(namespace + ".getCourseTotalSell");
	}
	
	//전체 노트  강의 판매 비율
	@Override
	public List<HistoryOrderNoteDTO> getNoteTotalSell() throws Exception {
		return sqlSession.selectList(namespace + ".getNoteTotalSell");
	}
	
	//한 달 간 방문자 수
	@Override
	public int getNoteTotalSellCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getNoteTotalSellCount");
	}
		
}
