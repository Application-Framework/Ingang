package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.t_TagDTO;

@Service
public interface t_TagService {
	public List<t_TagDTO> getTagList();
	public int insertTag(t_TagDTO dto);
	public int updateTag(t_TagDTO dto);
	public int deleteTag(int tag_no);
	public t_TagDTO getTagByTag_no(int tag_no);
	public t_TagDTO getTagByTag_abbr(String tag_abbr);
}
