package com.spring.ex.admin.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDashBoardDAOImpl implements AdminDashBoardDAO {

	@Inject SqlSession sqlSession;
	private static final String namespace = "com.spring.ex.AdminDashBoardMapper";
	
	//오늘의 방문자 수
	@Override
	public int getTodayVisitorsCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getTodayVisitorsCount");
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
		
}