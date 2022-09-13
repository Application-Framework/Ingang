package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.UploadedFileDTO;

@Repository
public class UploadedFileDAOImpl implements UploadedFileDAO {

	@Inject
	private SqlSession sqlSession;
	
	private String namespace = "mappers.UploadedFileMapper";
	
	@Override
	public int insertFile(UploadedFileDTO dto) {
		return sqlSession.insert(namespace + ".insertFile", dto);
	}

	@Override
	public int deleteFile(String url) {
		return sqlSession.delete(namespace + ".deleteFile", url);
	}

	@Override
	public List<UploadedFileDTO> selectFileList(int content_no, int category) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("content_no", content_no);
		map.put("category", category);
		return sqlSession.selectList(namespace + ".selectFileList", map);
	}
}
