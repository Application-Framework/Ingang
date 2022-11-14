package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.dto.note.NoteReplyDTO;

@Service
public interface NoteService {
	// 노트
	public int insertNote(NoteDTO dto);
	public int deleteNote(int n_no);
	public int updateNote(NoteDTO dto);
	public List<NoteDTO> getNoteList(HashMap<String, Object> pageMap);
	public List<NoteDTO> getNoteListByOli_no(int oli_no);
	public NoteDTO getNote(int n_no);
	public NoteDTO getNoteByOli_noM_no(int oli_no, int m_no);
	public int getNoteTotalCount(HashMap<String, Object> countMap);
	
	// 노트 글
	public int insertNoteArticle(NoteArticleDTO dto);
	public int updateNoteArticle(NoteArticleDTO dto);
	public int deleteNoteArticle(int na_no);
	public int deleteNoteArticleByN_noAndOlv_no(int n_no, int olv_no);
	public List<NoteArticleDTO> getNoteArticleList(int n_no);
	public NoteArticleDTO getNoteArticle(int na_no);
	public NoteArticleDTO getNoteArticleByN_noOlv_no(int n_no, int olv_no);
	
	// 리뷰
	public List<NoteReplyDTO> getNoteReplyList(int n_no);
	public int submitNoteReply(NoteReplyDTO dto);
	
	// 좋아요
	public int getNoteLikeCount(int n_no);
	public int insertNoteLike(int oli_no, int m_no);
	public int deleteNoteLike(int oli_no, int m_no);
	public int existNoteLike(int oli_no, int m_no);
}
