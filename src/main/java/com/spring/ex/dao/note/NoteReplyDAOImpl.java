package com.spring.ex.dao.note;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.NoteReplyDTO;

@Repository
public class NoteReplyDAOImpl implements NoteReplyDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.NoteMapper";
	
	@Override
	public List<NoteReplyDTO> getNoteReplyList(int n_no) {
		return sqlSession.selectList(namespace + ".getNoteReplyList", n_no);
	}
	
	// 리뷰 가져오기
	@Override
	public NoteReplyDTO getNoteReply(int nr_no) {
		return sqlSession.selectOne(namespace + ".getNoteReply", nr_no);
	}

	@Override
	public int submitNoteReply(NoteReplyDTO dto) {
		return sqlSession.insert(namespace + ".submitNoteReply", dto);
	}

	@Override
	public int updateNoteReply(NoteReplyDTO dto) {
		return sqlSession.update(namespace + ".updateNoteReply", dto);
	}

	@Override
	public int deleteNoteReply(int nr_no) {
		return sqlSession.delete(namespace + ".deleteNoteReply", nr_no);
	}
}
