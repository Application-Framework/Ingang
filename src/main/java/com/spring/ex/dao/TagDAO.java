package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TagDTO;

@Repository
public interface TagDAO {
	
	//태그 검색 순위
	public List<TagDTO> tagRanking(TagDTO dto) throws Exception;
	
}