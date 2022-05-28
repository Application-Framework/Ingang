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
	
	//게시판 최신순 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardChatRegDateShowPage(HashMap<String, Integer> map) throws Exception {
		return dao.getCommunityBoardChatRegDateShowPage(map);
	}
	
	//게시판 좋아요순 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardChatGoodShowPage(HashMap<String, Integer> map) throws Exception {
		return dao.getCommunityBoardChatGoodShowPage(map);
	}
	
	//게시판 목록 총 갯수 - 페이징
	@Override
	public int getCommunityBoardTotalCount() throws Exception {
		return dao.getCommunityBoardTotalCount();
	}
	
	//내가 쓴 게시글
	@Override
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception {
		return dao.myPostList(m_no);
	}
}