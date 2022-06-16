package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CommunityBoardDTO;

@Repository
public interface MyPageDAO {

	// 내가 쓴 게시글 목록
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception;
	
}
