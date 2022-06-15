package com.spring.ex.dao.note;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.HistoryOrderNoteDTO;

@Repository
public interface HistoryOrderNoteDAO {
	public int insertHistoryOrderNote(HistoryOrderNoteDTO dto);
	public HistoryOrderNoteDTO getHistoryOrderNoteByN_noM_no(int n_no, int m_no);
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteList(int m_no);
}
