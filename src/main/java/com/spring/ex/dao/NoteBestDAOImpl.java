package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.NoteDTO;

@Repository
public class NoteBestDAOImpl implements NoteBestDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.indexCourseMapper";
	
	// 이 주의 노트 목록
	@Override
	public List<NoteDTO> thisweek_bestNoteList() throws Exception {
		return sqlSession.selectList(namespace + ".thisweek_bestNoteList");
	}

}
