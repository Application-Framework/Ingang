package com.spring.ex.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.admin.dao.AdminCommunityDAO;
import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.course.CourseReplyDTO;

@Service
public class AdminCommunityServiceImpl implements AdminCommunityService {
	
	@Inject AdminCommunityDAO dao;
	
	// 페이지의 게시물 가져오기
	public List<CommunityBoardDTO> getFreeBoardPage(HashMap<String, Object> map) throws Exception {
		return dao.getFreeBoardPage(map);
	}
	
	// 자유게시판 게시글 수, 제목 검색
	@Override
	public int getAdminCommunityBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return dao.getAdminCommunityBoardTotalCount(map);
	}
	
	// 자유게시판 좋아요
	public int getFreeBoardLikeCount(int cb_no) throws Exception {
		return dao.getFreeBoardLikeCount(cb_no);
	}
	
	// 질문 & 답변 게시판  게시물 가져오기
	public List<CommunityBoardDTO> getQnABoardPage(HashMap<String, Object> map) throws Exception {
		return dao.getQnABoardPage(map);
	}
		
	// 질문 & 답변 게시판 게시글 수, 제목 검색
	public int getAdminQnABoardTotalCount(HashMap<String, Object> map) throws Exception {
		return dao.getAdminQnABoardTotalCount(map);
	}
	
	// 질문 & 답변 좋아요
	public int getQnABoardLikeCount(int cb_no) throws Exception {
		return dao.getQnABoardLikeCount(cb_no);
	}
	
	// 수강후기 게시판  게시물 가져오기
	public List<CourseReplyDTO> getReviewBoardPage(HashMap<String, Object> map) throws Exception {
		return dao.getReviewBoardPage(map);
	}
			
	// 수강후기 게시판 게시글 수, 제목 검색
	public int getAdminReviewBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return dao.getAdminReviewBoardTotalCount(map);
	}
	
	// 수강후기 삭제
	public int deleteAdminReview(int olr_no) throws Exception {
		return dao.deleteAdminReview(olr_no);
	}
	
	// 스터디 게시물 가져오기
	public List<CommunityBoardDTO> getStudyBoardPage(HashMap<String, Object> map) throws Exception {
		return dao.getStudyBoardPage(map);
	}
		
	// 스터디 게시글 수, 제목 검색
	public int getAdminStudyBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return dao.getAdminStudyBoardTotalCount(map);
	}
			
	//게시물 작성
	public int insertAdminBoard(CommunityBoardDTO dto) throws Exception {
		return dao.insertAdminBoard(dto);
	}
	
	// 게시글 보기
	public Map<String, Object> getBoardView(HashMap<String, Object> map) throws Exception {
		return dao.getBoardView(map);
	}
	
	// 게시글 수정
	public int updateAdminBoard(CommunityBoardDTO cDto) throws Exception {
		return dao.updateAdminBoard(cDto);
	}
	
	// 게시글 삭제
	public int deleteAdminBoard(int cb_no) throws Exception {
		return dao.deleteAdminBoard(cb_no);
	}

}
