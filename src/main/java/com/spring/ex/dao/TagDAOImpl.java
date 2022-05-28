package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.TagDTO;


@Repository
public class TagDAOImpl implements TagDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.tagMapper";
	
	@Override
	public List<TagDTO> tagRanking(TagDTO dto) throws Exception {
		return sqlSession.selectList(namespace + ".tagRanking", dto);
	}
}