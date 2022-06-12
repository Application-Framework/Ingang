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

	@Override
	public int submitNoteReply(NoteReplyDTO dto) {
		return sqlSession.insert(namespace + ".submitNoteReply", dto);
	}

}
