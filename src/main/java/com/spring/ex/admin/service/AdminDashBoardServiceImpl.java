package com.spring.ex.admin.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.admin.dao.AdminDashBoardDAO;

@Service
public class AdminDashBoardServiceImpl implements AdminDashBoardService {

	@Inject AdminDashBoardDAO dao;
	
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
	
	
}