package com.spring.ex.admin.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminDashBoardService {
	
	//오늘의 가입 회원 수
	public int getTodayRegisterMemberTotalCount() throws Exception;
	
	//오늘의 게시글 작성 수
	public int getTodayBoardWriteTotalCount() throws Exception;
	
}