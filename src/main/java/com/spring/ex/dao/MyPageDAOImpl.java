package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;

@Repository
public class MyPageDAOImpl implements MyPageDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.myPageMapper";
	
	// 내가 쓴 게시글 목록
	@Override
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myPostList", m_no);
	}

}
