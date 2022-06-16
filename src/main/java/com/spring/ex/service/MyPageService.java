package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.CommunityBoardDTO;

@Service
public interface MyPageService {
	
	// 내가 쓴 게시글 목록
	public List<CommunityBoardDTO> myPostList(Integer m_no) throws Exception;
	
}
