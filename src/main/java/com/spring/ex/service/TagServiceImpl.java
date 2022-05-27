package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.TagDAO;
import com.spring.ex.dto.TagDTO;

@Service
public class TagServiceImpl implements TagService{
	
	@Inject
	private TagDAO dao;

	@Override
	public List<TagDTO> tagRanking(TagDTO dto) throws Exception {
		return dao.tagRanking(dto);
	}
}