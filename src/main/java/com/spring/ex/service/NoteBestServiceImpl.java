package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.NoteBestDAO;
import com.spring.ex.dto.note.NoteDTO;

@Service
public class NoteBestServiceImpl implements NoteBestService {

	@Inject
	NoteBestDAO dao;
	
	// 이 주의 노트 목록
	@Override
	public List<NoteDTO> thisweek_bestNoteList() throws Exception {
		return dao.thisweek_bestNoteList();
	}

}
