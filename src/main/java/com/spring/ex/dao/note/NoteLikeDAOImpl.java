package com.spring.ex.dao.note;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class NoteLikeDAOImpl implements NoteLikeDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.NoteMapper";
	
	@Override
	public int getNoteLikeCount(int n_no) {
		return sqlSession.selectOne(namespace + ".getNoteLikeCount", n_no);
	}

	@Override
	public int insertNoteLike(int n_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("n_no", n_no);
		map.put("m_no", m_no);
		return sqlSession.insert(namespace + ".insertNoteLike", map);
	}

	@Override
	public int deleteNoteLike(int n_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("n_no", n_no);
		map.put("m_no", m_no);
		return sqlSession.delete(namespace + ".deleteNoteLike", map);
	}

	@Override
	public int existNoteLike(int n_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("n_no", n_no);
		map.put("m_no", m_no);
		return sqlSession.selectOne(namespace + ".existNoteLike", map);
	}

}
