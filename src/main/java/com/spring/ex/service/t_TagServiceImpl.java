package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.t_TagDAO;
import com.spring.ex.dto.t_TagDTO;

@Service
public class t_TagServiceImpl implements t_TagService {

	@Inject
	private t_TagDAO t_TagDAO;
	
	@Override
	public List<t_TagDTO> getTagList() {
		return t_TagDAO.getTagList();
	}

	@Override
	public int insertTag(t_TagDTO dto) {
		return t_TagDAO.insertTag(dto);
	}

	@Override
	public int updateTag(t_TagDTO dto) {
		return t_TagDAO.updateTag(dto);
	}

	@Override
	public int deleteTag(int tag_no) {
		return t_TagDAO.deleteTag(tag_no);
	}

	@Override
	public t_TagDTO getTagByTag_no(int tag_no) {
		return t_TagDAO.getTagByTag_no(tag_no);
	}

	@Override
	public t_TagDTO getTagByTag_abbr(String tag_abbr) {
		return t_TagDAO.getTagByTag_abbr(tag_abbr);
	}

}
