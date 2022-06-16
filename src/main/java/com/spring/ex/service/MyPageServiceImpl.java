package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.MyPageDAO;
import com.spring.ex.dto.CommunityBoardDTO;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Inject
	private MyPageDAO dao;
	
	// 내가 쓴 게시글 목록
	@Override
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception {
		return dao.myPostList(m_no);
	}

}
