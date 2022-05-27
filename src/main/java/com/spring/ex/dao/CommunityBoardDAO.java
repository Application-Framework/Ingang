package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;


@Repository
public interface CommunityBoardDAO {
	
	//게시판 출력
	public List<CommunityBoardDTO> getCommunityBoardPage(HashMap<String, Integer> map) throws Exception;
	
	//게시판 목록 총 갯수 - 페이징
	public int getCommunityBoardTotalCount() throws Exception;
	
	//내가 쓴 게시글
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception;
}