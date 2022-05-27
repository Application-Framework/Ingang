package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.CommunityBoardDAO;
import com.spring.ex.dto.CommunityBoardDTO;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService{
	
	@Inject
	private CommunityBoardDAO dao;
	
	//게시판 출력
	public List<CommunityBoardDTO> getCommunityBoardPage(HashMap<String, Integer> map) throws Exception {
		return dao.getCommunityBoardPage(map);
	}
	
	//게시판 목록 총 갯수 - 페이징
	public int getCommunityBoardTotalCount() throws Exception {
		return dao.getCommunityBoardTotalCount();
	}
	
	//내가 쓴 게시글
	@Override
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception {
		return dao.myPostList(m_no);
	}
}