package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.CommunityBoardReplyDTO;


@Repository
public interface CommunityBoardDAO {
	
	//게시판 최신순 출력
	public List<CommunityBoardDTO> getCommunityBoardChatRegDateShowPage(HashMap<String, Integer> map) throws Exception;
	
	//게시판 최신순 출력
	public List<CommunityBoardDTO> getCommunityBoardChatGoodShowPage(HashMap<String, Integer> map) throws Exception;
	
	//게시판 목록 총 갯수 - 페이징
	public int getCommunityBoardTotalCount() throws Exception;
	
	//게시글 상세페이지 출력 
	public Map<String, Object> getReadCommunityBoard(HashMap<String, Object> map) throws Exception;
	
	//게시글 상세페이지 댓글 출력
	public List<CommunityBoardReplyDTO> getReplyCommunityBoard(Integer cb_no) throws Exception;

}