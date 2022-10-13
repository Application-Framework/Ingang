package com.spring.ex.dao.note;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.NoteArticleDTO;

@Repository
public interface NoteArticleDAO {
	public int insertNoteArticle(NoteArticleDTO dto);
	public int updateNoteArticle(NoteArticleDTO dto);
	public int deleteNoteArticle(int na_no);
	public int deleteNoteArticleByN_noAndOlv_no(int n_no, int olv_no);
	public List<NoteArticleDTO> getNoteArticleList(int n_no);
	public NoteArticleDTO getNoteArticle(int na_no);
	public NoteArticleDTO getNoteArticleByN_noOlv_no(int n_no, int olv_no);
}
