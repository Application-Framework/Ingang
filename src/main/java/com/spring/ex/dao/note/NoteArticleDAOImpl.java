package com.spring.ex.dao.note;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.NoteArticleDTO;

@Repository
public class NoteArticleDAOImpl implements NoteArticleDAO {

	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.NoteMapper";
	
	@Override
	public int insertNoteArticle(NoteArticleDTO dto) {
		return sqlSession.insert(namespace + ".insertNoteArticle", dto);
	}

	@Override
	public int updateNoteArticle(NoteArticleDTO dto) {
		return sqlSession.update(namespace + ".updateNoteArticle", dto);
	}

	@Override
	public int deleteNoteArticle(int na_no) {
		return sqlSession.delete(namespace + ".deleteNoteArticle", na_no);
	}

	@Override
	public List<NoteArticleDTO> getNoteArticleList(int n_no) {
		return sqlSession.selectList(namespace + ".getNoteArticleList", n_no);
	}
	
	@Override
	public NoteArticleDTO getNoteArticle(int na_no) {
		return sqlSession.selectOne(namespace + ".getNoteArticle", na_no);
	}
	
	@Override
	public NoteArticleDTO getNoteArticleByN_noOlv_no(int n_no, int olv_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("n_no", n_no);
		map.put("olv_no", olv_no);
		return sqlSession.selectOne(namespace + ".getNoteArticleByN_noOlv_no", map);
	}
}
