package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.t_TagDTO;

@Repository
public interface t_TagDAO {
	public List<t_TagDTO> getTagList();
	public int insertTag(t_TagDTO dto);
	public int updateTag(t_TagDTO dto);
	public int deleteTag(int tag_no);
	public t_TagDTO getTagByTag_no(int tag_no);
	public t_TagDTO getTagByTag_abbr(String tag_abbr);
}
