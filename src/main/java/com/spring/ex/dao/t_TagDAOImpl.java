package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.t_TagDTO;

@Repository
public class t_TagDAOImpl implements t_TagDAO {

	@Inject
	private SqlSession sql;
	
	private String namespace = "com.spring.ex.t_TagMapper";
	
	@Override
	public List<t_TagDTO> getTagList() {
		return sql.selectList(namespace + ".getTagList");
	}

	@Override
	public int insertTag(t_TagDTO dto) {
		return sql.insert(namespace + ".insertTag", dto);
	}

	@Override
	public int updateTag(t_TagDTO dto) {
		return sql.update(namespace + ".updateTag", dto);
	}

	@Override
	public int deleteTag(int tag_no) {
		return sql.delete(namespace + ".deleteTag", tag_no);
	}

	@Override
	public t_TagDTO getTagByTag_no(int tag_no) {
		return sql.selectOne(namespace + ".getTagByTag_no", tag_no);
	}

	@Override
	public t_TagDTO getTagByTag_abbr(String tag_abbr) {
		return sql.selectOne(namespace + ".getTagByTag_abbr", tag_abbr);
	}
}
