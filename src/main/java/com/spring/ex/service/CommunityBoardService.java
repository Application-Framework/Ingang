package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.CommunityBoardReplyDTO;

@Service
public interface CommunityBoardService {
	
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