package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;


@Repository
public class CommunityBoardDAOImpl implements CommunityBoardDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.ex.CommunityBoardMapper";
	
	//게시판 최신순 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardChatRegDateShowPage(HashMap<String, Integer> map) throws Exception {
		return sqlSession.selectList(namespace + ".getCommunityBoardChatRegDateShowPage", map) ;
	}

	//게시판 좋아요순 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardChatGoodShowPage(HashMap<String, Integer> map) throws Exception {
		return sqlSession.selectList(namespace + ".getCommunityBoardChatGoodShowPage", map);
	}
	
	//게시판 총 갯수
	@Override
	public int getCommunityBoardTotalCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getCommunityBoardTotalCount") ;
	}





}