package com.spring.ex.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.course.CourseReplyDTO;

@Repository
public interface AdminCommunityDAO {
	
	// 자유게시판  게시물 가져오기
	public List<CommunityBoardDTO> getFreeBoardPage(HashMap<String, Object> map) throws Exception;

	// 자유게시판 게시글 수, 제목 검색
	public int getAdminCommunityBoardTotalCount(HashMap<String, Object> map) throws Exception;
	
	// 자유게시판 좋아요
	public int getFreeBoardLikeCount(int cb_no) throws Exception;
	
	// 질문 & 답변 게시판  게시물 가져오기
	public List<CommunityBoardDTO> getQnABoardPage(HashMap<String, Object> map) throws Exception;
	
	// 질문 & 답변 게시판 게시글 수, 제목 검색
	public int getAdminQnABoardTotalCount(HashMap<String, Object> map) throws Exception;
	
	// 질문 & 답변 좋아요
	public int getQnABoardLikeCount(int cb_no) throws Exception;

	// 수강후기 게시판  게시물 가져오기
	public List<CourseReplyDTO> getReviewBoardPage(HashMap<String, Object> map) throws Exception;
		
	// 수강후기 게시판 게시글 수, 제목 검색
	public int getAdminReviewBoardTotalCount(HashMap<String, Object> map) throws Exception;
	
	// 게시글 삭제
	public int deleteAdminReview(int olr_no) throws Exception;

	// 스터디 게시물 가져오기
	public List<CommunityBoardDTO> getStudyBoardPage(HashMap<String, Object> map) throws Exception;
	
	// 스터디 게시글 수, 제목 검색
	public int getAdminStudyBoardTotalCount(HashMap<String, Object> map) throws Exception;
		
	//게시물 작성
	public int insertAdminBoard(CommunityBoardDTO dto) throws Exception;
	
	// 게시글 상세 보기
	public Map<String, Object> getBoardView(HashMap<String, Object> map) throws Exception;
	
	// 게시글 수정
	public int updateAdminBoard(CommunityBoardDTO cDto) throws Exception;
	
	// 게시글 삭제
	public int deleteAdminBoard(int cb_no) throws Exception;

}
