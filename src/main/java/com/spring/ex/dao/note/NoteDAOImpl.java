package com.spring.ex.dao.note;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.NoteDTO;

@Repository
public class NoteDAOImpl implements NoteDAO {

	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.NoteMapper";
	
	@Override
	public int insertNote(NoteDTO dto) {
		return sqlSession.insert(namespace + ".insertNote", dto);
	}

	@Override
	public int deleteNote(int n_no) {
		return sqlSession.delete(namespace + ".deleteNote", n_no);
	}

	@Override
	public int updateNote(NoteDTO dto) {
		return sqlSession.update(namespace + ".updateNote", dto);
	}

	@Override
	public List<NoteDTO> getNoteList(HashMap<String, Object> pageMap) {
		return sqlSession.selectList(namespace + ".getNoteList", pageMap);
	}

	@Override
	public NoteDTO getNote(int n_no) {
		return sqlSession.selectOne(namespace + ".getNote", n_no);
	}

	@Override
	public NoteDTO getNoteByOli_noM_no(int oli_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("oli_no", oli_no);
		map.put("m_no", m_no);
		return sqlSession.selectOne(namespace + ".getNoteByOli_noM_no", map);
	}

	@Override
	public int getNoteTotalCount(HashMap<String, Object> countMap) {
		return sqlSession.selectOne(namespace + ".getNoteTotalCount", countMap);
	}

	@Override
	public List<NoteDTO> getNoteListByOli_no(int oli_no) {
		return sqlSession.selectList(namespace + ".getNoteListByOli_no", oli_no);
	}

}
