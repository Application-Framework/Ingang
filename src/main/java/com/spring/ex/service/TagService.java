package com.spring.ex.service;

import java.util.List;

import com.spring.ex.dto.TagDTO;

public interface TagService {
	
	//태그 검색 순위
	public List<TagDTO> tagRanking(TagDTO dto) throws Exception;
	
}
