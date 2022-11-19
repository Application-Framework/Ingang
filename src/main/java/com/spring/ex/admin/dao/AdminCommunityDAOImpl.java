package com.spring.ex.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.course.CourseReplyDTO;

@Repository
public class AdminCommunityDAOImpl implements  AdminCommunityDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.adminCommunityMapper";
	
	// 페이지의 게시물 가져오기
	@Override
	public List<CommunityBoardDTO> getFreeBoardPage(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getFreeBoardPage", map);
	}
	
	// 자유게시판 게시글 수, 제목 검색
	@Override
	public int getAdminCommunityBoardTotalCount(HashMap<String, Object> map) {
		return sqlSession.selectOne(namespace + ".getAdminCommunityBoardTotalCount", map);
	}
	
	// 자유게시판 좋아요
	public int getFreeBoardLikeCount(int cb_no) throws Exception {
		return sqlSession.selectOne(namespace + ".getFreeBoardLikeCount", cb_no);
	}
	
	// 질문 & 답변 게시판  게시물 가져오기
	public List<CommunityBoardDTO> getQnABoardPage(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getQnABoardPage", map);
	}
			
	// 질문 & 답변 게시판 게시글 수, 제목 검색
	public int getAdminQnABoardTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getAdminQnABoardTotalCount", map);
	}
		
	// 질문 & 답변 좋아요
	public int getQnABoardLikeCount(int cb_no) throws Exception {
		return sqlSession.selectOne(namespace + ".getQnABoardLikeCount", cb_no);
	}
	
	// 수강후기 게시판  게시물 가져오기
	public List<CourseReplyDTO> getReviewBoardPage(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getReviewBoardPage", map);
	}
			
	// 수강후기 게시판 게시글 수, 제목 검색
	public int getAdminReviewBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getAdminReviewBoardTotalCount", map);
	}
	
	// 게시글 삭제
	public int deleteAdminReview(int olr_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteAdminReview", olr_no);
	}
	
	// 스터디 게시물 가져오기
	public List<CommunityBoardDTO> getStudyBoardPage(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".getStudyBoardPage", map);
	}
		
	// 스터디 게시글 수, 제목 검색
	public int getAdminStudyBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getAdminStudyBoardTotalCount", map);
	}
	
	//게시물 작성
	public int insertAdminBoard(CommunityBoardDTO dto) throws Exception {
		return sqlSession.insert(namespace + ".insertAdminBoard", dto);
	}

	// 게시글 보기
	public Map<String, Object> getBoardView(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace + ".getBoardView", map);
	}
	
	// 게시글 수정
	public int updateAdminBoard(CommunityBoardDTO cDto) throws Exception {
		return sqlSession.update(namespace + ".updateAdminBoard", cDto);
	}
	
	// 게시글 삭제
	public int deleteAdminBoard(int cb_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteAdminBoard", cb_no);
	}

}
