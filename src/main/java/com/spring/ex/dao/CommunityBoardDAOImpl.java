package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
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
public class CommunityBoardDAOImpl implements CommunityBoardDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.ex.CommunityBoardMapper";
	
	//게시판 최신순 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardChatRegDateShowPage(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getCommunityBoardChatRegDateShowPage", map) ;
	}

	//게시판 좋아요순 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardChatGoodShowPage(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getCommunityBoardChatGoodShowPage", map);
	}
	
	//게시판 총 갯수
	@Override
	public int getCommunityBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getCommunityBoardTotalCount", map) ;
	}

	//게시글 상세페이지 출력 
	@Override
	public Map<String, Object> getReadCommunityBoard(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getReadCommunityBoard", map);
	}

	//게시글 상세페이지 댓글 출력
	@Override
	public List<CommunityBoardReplyDTO> getReplyCommunityBoard(Integer cb_no) throws Exception {
		return sqlSession.selectList(namespace + ".getReplyCommunityBoard", cb_no);
	}
	
	//게시물 조회수 증가
	@Override
	public void addReadCommunityBoardHit(int cb_no) throws Exception {
		sqlSession.update(namespace + ".addReadCommunityBoardHit", cb_no);
	}
	
	//게시물 좋아요 유무 체크
	@Override
	public int getGoodCheckReadCommunityBoard(HashMap<String, Integer> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getGoodCheckReadCommunityBoard", map);
	}
	
	//게시물 좋아요 추가
	@Override
	public int addGoodReadCommunityBoard(HashMap<String, Object> map) throws Exception {
		return sqlSession.insert(namespace + ".addGoodReadCommunityBoard", map);
	}
	
	//게시물 좋아요 삭제
	@Override
	public int subtractGoodReadCommunityBoard(HashMap<String, Object> map) throws Exception {
		return sqlSession.delete(namespace + ".subtractGoodReadCommunityBoard", map);
	}
	
	//게시물 댓글 작성
	@Override
	public int writeReplyCommunityBoard(CommunityBoardReplyDTO dto) throws Exception {
		return sqlSession.insert(namespace + ".writeReplyCommunityBoard", dto);
	}
	
	//게시물 댓글 수정
	@Override
	public int updateReplyCommunityBoard(CommunityBoardReplyDTO dto) throws Exception {
		return sqlSession.update(namespace + ".updateReplyCommunityBoard", dto);
	}
	
	//게시물 댓글 삭제
	@Override
	public int deleteReplyCommunityBoard(int cbr_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteReplyCommunityBoard", cbr_no);
	}
	
	//게시물 작성
	@Override
	public int writeCommunityBoard(CommunityBoardDTO dto) throws Exception {
		return sqlSession.insert(namespace + ".writeCommunityBoard", dto);
	}
	
	//게시물 수정
	@Override
	public int updateCommunityBoard(CommunityBoardDTO dto) throws Exception {
		return sqlSession.update(namespace + ".updateCommunityBoard", dto);
	}
	
	//게시물 삭제
	@Override
	public int deleteCommunityBoard(int cb_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteCommunityBoard", cb_no);
	}
	
	//태그 출력
	@Override
	public List<CommunityBoardTagDTO> getTagCommunityBoard(int cb_no) throws Exception {
		return sqlSession.selectList(namespace + ".getTagCommunityBoard", cb_no);
	}
	
	//인기태그 출력
	@Override
	public List<CommunityTagListDTO> getPopularityTagCommunity(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getPopularityTagCommunity", map);
	}
	
	//존재하는 태그 명인지 체크
	@Override
	public int isCheckTagSearchList(String ctl_name) throws Exception {
		return sqlSession.selectOne(namespace + ".isCheckTagSearchList", ctl_name);
	}
	
	//존재하지 않는 태그명이면 삽입후 값 반환
	@Override
	public int insertTagList(CommunityTagListDTO dto) throws Exception {
		return sqlSession.insert(namespace + ".insertTagList", dto);
	}
	
	//커뮤니티 태그 검색 기록
	@Override
	public void serachTagRecord(CommunityTagSerachDTO dto) throws Exception {
		sqlSession.insert(namespace + ".serachTagRecord", dto);
	}
	
	//해당 게시글 태그 추가
	@Override
	public int insertCommunityBoardTag(CommunityBoardTagDTO dto) throws Exception {
		return sqlSession.insert(namespace + ".insertCommunityBoardTag", dto);
	}
	//해당 게시글 태그 삭제 
	@Override
	public int deleteCommunityBoardTag(int cb_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteCommunityBoardTag", cb_no);
	}
	//수강후기 게시판 출력
	@Override
	public List<CourseReplyDTO> getReviewCommunityBoard(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getReviewCommunityBoard", map);
	}
	
	//수강후기 게시판 목록 총 갯수 - 페이징
	@Override
	public int getReviewCommunityBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getReviewCommunityBoardTotalCount", map);
	}
	
	//게시판 해결됨, 모집종료로 변경
	public int updateCompletedCommunityBoard(HashMap<String, Object> map) throws Exception {
		return sqlSession.update(namespace + ".updateCompletedCommunityBoard", map);
	}
	
	//1:1문의하기 게시판 출력
	public List<InquiryDTO> getCommunityBoardInquiryPage(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getCommunityBoardInquiryPage", map);
	}
	
	//1:1문의하기 게시판 총 갯수
	public int getCommunityBoardInquiryPageTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getCommunityBoardInquiryPageTotalCount", map);
	}
	
	//1:1문의하기 작성
	public int writeInquiry(InquiryDTO dto) throws Exception {
		return sqlSession.insert(namespace + ".writeInquiry", dto);
	}
	
	//1:1문의하기 상세페이지
	public Map<String, Object> getInquiryViewPage(int inq_no) throws Exception {
		return sqlSession.selectOne(namespace + ".getInquiryViewPage", inq_no);
	}
	
	//1:1문의하기 삭제
	public int deleteInquiry(int inq_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteInquiry", inq_no);
	}

	//1:1문의 답변 작성
	public int writeInquiryAnswer(InquiryAnswerDTO dto) throws Exception {
		return sqlSession.insert(namespace + ".writeInquiryAnswer", dto);
	}
	
	//1:1문의 답변 삭제
	public int deleteInquiryAnswer(int ia_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteInquiryAnswer", ia_no);
	}
	
	//1:1문의 답변완료 상태로 변경
	public int updateStatementAnswerOk(int inq_no) throws Exception {
		return sqlSession.update(namespace + ".updateStatementAnswerOk", inq_no);
	}
	
	//1:1문의 답변보류 상태로 변경
	public int updateStatementAnswerDelay(int inq_no) throws Exception {
		return sqlSession.update(namespace + ".updateStatementAnswerDelay", inq_no);
	}
	
	//1:1문의 답변대기 상태로 변경
	public int updateStatementAnswerDelete(int inq_no) throws Exception {
		return sqlSession.update(namespace + ".updateStatementAnswerDelete", inq_no);
	}
	
	//1:1문의 답변 수정
	public int updateInquiryAnswer(InquiryAnswerDTO dto) throws Exception {
		return sqlSession.update(namespace + ".updateInquiryAnswer", dto);
	}
	
	//2022-09-02 김홍일 / 강의 상세 페이지의 커뮤니티 탭에 표시될 내용
	@Override
	public List<CommunityBoardDTO> selectCommunityBoardByOli_no(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".selectCommunityBoardByOli_no", map);
	}

	//2022-09-02 김홍일 / 강의 상세 페이지의 커뮤니티 탭에 표시될 내용 개수
	@Override
	public int selectCommunityBoardTotalCountByOli_no(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".selectCommunityBoardTotalCountByOli_no", map);
	}

	//2022-09-03 김홍일 / 게시물의 댓글 개수 가져오기
	@Override
	public int selectCommunityBoardReplyCount(int cb_no) throws Exception {
		return sqlSession.selectOne(namespace + ".selectCommunityBoardReplyCount", cb_no);
	}

	//2022-09-03 김홍일 / 게시물의 좋아요 개수 가져오기
	@Override
	public int selectCommunityBoardGoodCount(int cb_no) throws Exception {
		return sqlSession.selectOne(namespace + ".selectCommunityBoardGoodCount", cb_no);
	}
}