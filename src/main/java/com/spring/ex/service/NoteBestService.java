package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.note.NoteDTO;

@Service
public interface NoteBestService {
	
	// 이 주의 노트 목록
	public List<NoteDTO> thisweek_bestNoteList() throws Exception;
	
}
