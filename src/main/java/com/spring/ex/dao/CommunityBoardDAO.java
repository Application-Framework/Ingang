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
	public List<CommunityBoardDTO> getCommunityBoardChatRegDateShowPage(HashMap<String, Object> map) throws Exception;
	
	//게시판 최신순 출력
	public List<CommunityBoardDTO> getCommunityBoardChatGoodShowPage(HashMap<String, Object> map) throws Exception;
	
	//게시판 목록 총 갯수 - 페이징
	public int getCommunityBoardTotalCount() throws Exception;
	
	//게시글 상세페이지 출력 
	public Map<String, Object> getReadCommunityBoard(HashMap<String, Object> map) throws Exception;
	
	//게시글 상세페이지 댓글 출력
	public List<CommunityBoardReplyDTO> getReplyCommunityBoard(Integer cb_no) throws Exception;
	
	//게시물 조회수 증가
	public void addReadCommunityBoardHit(int cb_no) throws Exception;
	
	//게시물 좋아요 유무 체크
	public int getGoodCheckReadCommunityBoard(HashMap<String, Integer> map) throws Exception;
	
	//게시물 좋아요 추가
	public int addGoodReadCommunityBoard(HashMap<String, Object> map) throws Exception;
	
	//게시물 좋아요 삭제
	public int subtractGoodReadCommunityBoard(HashMap<String, Object> map) throws Exception;
	
	//게시물 댓글 작성
	public int writeReplyCommunityBoard(CommunityBoardReplyDTO dto) throws Exception;
	
	//게시물 댓글 수정
	public int updateReplyCommunityBoard(int cbr_no) throws Exception;
	
	//게시물 댓글 삭제
	public int deleteReplyCommunityBoard(int cbr_no) throws Exception;
	
	//게시물 작성
	public int writeCommunityBoard(CommunityBoardDTO dto) throws Exception;
	
	//게시물 수정
	public int updateCommunityBoard(CommunityBoardDTO dto) throws Exception;
	
	//게시물 삭제
	public int deleteCommunityBoard(int cb_no) throws Exception;

}