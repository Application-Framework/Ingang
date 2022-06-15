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
	public int updateReplyCommunityBoard(int cbr_no) throws Exception {
		return sqlSession.update(namespace + ".updateReplyCommunityBoard", cbr_no);
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

}