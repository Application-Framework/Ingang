package com.spring.ex.dao.note;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.NoteDTO;

@Repository
public interface NoteDAO {
	public int insertNote(NoteDTO dto);
	public int deleteNote(int n_no);
	public int updateNote(NoteDTO dto);
	public List<NoteDTO> getNoteList(String keyword, String tag, String order, int page, int pageSize);
	public List<NoteDTO> getNoteListByOli_no(int oli_no);
	public NoteDTO getNote(int n_no);
	public NoteDTO getNoteByOli_noM_no(int oli_no, int m_no);
	public int getNoteTotalCount(String keyword, String tag);
}
