package com.spring.ex.admin.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminDashBoardDAO {
	
	//오늘의 가입 회원 수
	public int getTodayRegisterMemberTotalCount() throws Exception;
	
	//오늘의 게시글 작성 수
	public int getTodayBoardWriteTotalCount() throws Exception;
	
}