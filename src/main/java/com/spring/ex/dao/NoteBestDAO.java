package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.NoteDTO;

@Repository
public interface NoteBestDAO {

	// 이 주의 노트 목록
	public List<NoteDTO> thisweek_bestNoteList() throws Exception;
	
}
