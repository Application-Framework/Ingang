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
	
	//게시판 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardPage(HashMap<String, Integer> map) throws Exception {
		return sqlSession.selectList(namespace + ".getCommunityBoardPage", map) ;
	}

	//게시판 총 갯수
	@Override
	public int getCommunityBoardTotalCount() throws Exception {
		return sqlSession.selectOne(namespace + ".getCommunityBoardTotalCount") ;
	}
	
	//내가 쓴 게시글
	@Override
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myPostList", m_no);
	}
}