package com.spring.ex.dao.note;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.HistoryOrderNoteDTO;

@Repository
public class HistoryOrderNoteDAOImpl implements HistoryOrderNoteDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.NoteMapper";
	
	@Override
	public int insertHistoryOrderNote(HistoryOrderNoteDTO dto) {
		return sqlSession.insert(namespace + ".insertHistoryOrderNote", dto);
	}

	@Override
	public HistoryOrderNoteDTO getHistoryOrderNoteByN_noM_no(int n_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("n_no", n_no);
		map.put("m_no", m_no);
		return sqlSession.selectOne(namespace + ".getHistoryOrderNoteByN_noM_no", map);
	}

	@Override
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteList(int m_no) {
		return sqlSession.selectList(namespace + ".getHistoryOrderNoteList", m_no);
	}
}
