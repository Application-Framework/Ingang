package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.CommunityBoardReplyDTO;
import com.spring.ex.dto.CommunityBoardTagDTO;
import com.spring.ex.dto.CommunityTagListDTO;
import com.spring.ex.dto.CommunityTagSerachDTO;
import com.spring.ex.dto.InquiryAnswerDTO;
import com.spring.ex.dto.InquiryDTO;
import com.spring.ex.dto.course.CourseReplyDTO;


@Repository
public interface CommunityBoardDAO {
	
	//게시판 최신순 출력
	public List<CommunityBoardDTO> getCommunityBoardChatRegDateShowPage(HashMap<String, Object> map) throws Exception;
	
	//게시판 좋아요순 출력
	public List<CommunityBoardDTO> getCommunityBoardChatGoodShowPage(HashMap<String, Object> map) throws Exception;
	
	//게시판 목록 총 갯수 - 페이징
	public int getCommunityBoardTotalCount(HashMap<String, Object> map) throws Exception;
	
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
	
	//태그 출력
	public List<CommunityBoardTagDTO> getTagCommunityBoard(int cb_no) throws Exception;
	
	//인기태그 출력
	public List<CommunityTagListDTO> getPopularityTagCommunity(HashMap<String, Object> map) throws Exception;
	
	//존재하는 태그 명인지 체크
	public int isCheckTagSearchList(String ctl_name) throws Exception;
	
	//존재하지 않는 태그명이면 삽입후 값 반환
	public int insertTagList(CommunityTagListDTO dto) throws Exception;
	
	//커뮤니티 태그 검색 기록
	public void serachTagRecord(CommunityTagSerachDTO dto) throws Exception;
	
	//수강후기 게시판 출력
	public List<CourseReplyDTO> getReviewCommunityBoard(HashMap<String, Object> map) throws Exception;
	
	//수강후기 게시판 목록 총 갯수 - 페이징
	public int getReviewCommunityBoardTotalCount(HashMap<String, Object> map) throws Exception;
	
	//게시판 해결됨, 모집종료로 변경
	public int updateCompletedCommunityBoard(HashMap<String, Object> map) throws Exception;
	
	//1:1문의하기 게시판 출력
	public List<InquiryDTO> getCommunityBoardInquiryPage(HashMap<String, Object> map) throws Exception;
	
	//1:1문의하기 게시판 총 갯수
	public int getCommunityBoardInquiryPageTotalCount(HashMap<String, Object> map) throws Exception;
	
	//1:1문의하기 작성
	public int writeInquiry(InquiryDTO dto) throws Exception;
	
	//1:1문의하기 상세페이지
	public Map<String, Object> getInquiryViewPage(int inq_no) throws Exception;
	
	//1:1문의하기 삭제
	public int deleteInquiry(int inq_no) throws Exception;
	
	//1:1문의 답변 작성
	public int writeInquiryAnswer(InquiryAnswerDTO dto) throws Exception;
	
	//1:1문의 답변 삭제
	public int deleteInquiryAnswer(int ia_no) throws Exception;
	
	//1:1문의 답변완료 상태로 변경
	public int updateStatementAnswerOk(int inq_no) throws Exception;
	
	//1:1문의 답변보류 상태로 변경
	public int updateStatementAnswerDelay(int inq_no) throws Exception;
	
	//1:1문의 답변대기 상태로 변경
	public int updateStatementAnswerDelete(int inq_no) throws Exception;
	
	//1:1문의 답변 수정
	public int updateInquiryAnswer(InquiryAnswerDTO dto) throws Exception;
	
	//2022-09-02 김홍일 / 강의 상세 페이지의 커뮤니티 탭에 표시될 내용
	public List<CommunityBoardDTO> selectCommunityBoardByOli_no(HashMap<String, Object> map) throws Exception;
	
	//2022-09-02 김홍일 / 강의 상세 페이지의 커뮤니티 탭에 표시될 내용 개수
	public int selectCommunityBoardTotalCountByOli_no(HashMap<String, Object> map) throws Exception;
	
	//2022-09-03 김홍일 / 게시물의 댓글 개수 가져오기
	public int selectCommunityBoardReplyCount(int cb_no) throws Exception;
	
	//2022-09-03 김홍일 / 게시물의 좋아요 개수 가져오기
	public int selectCommunityBoardGoodCount(int cb_no) throws Exception;
}