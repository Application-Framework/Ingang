package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.note.HistoryOrderNoteDAO;
import com.spring.ex.dao.note.NoteArticleDAO;
import com.spring.ex.dao.note.NoteDAO;
import com.spring.ex.dao.note.NoteLikeDAO;
import com.spring.ex.dao.note.NoteReplyDAO;
import com.spring.ex.dto.note.HistoryOrderNoteDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
import com.spring.ex.dto.note.NoteDTO;
import com.spring.ex.dto.note.NoteReplyDTO;

@Service
public class NoteServiceImpl implements NoteService{

	@Inject
	private NoteDAO noteDAO;
	
	@Inject
	private NoteArticleDAO noteArticleDAO;
	
	@Inject
	private NoteReplyDAO noteReplyDAO;
	
	@Inject
	private NoteLikeDAO noteLikeDAO;
	
	@Inject 
	private HistoryOrderNoteDAO historyOrderNoteDAO;
	
	@Override
	public int insertNote(NoteDTO dto) {
		return noteDAO.insertNote(dto);
	}

	@Override
	public int deleteNote(int n_no) {
		return noteDAO.deleteNote(n_no);
	}

	@Override
	public int updateNote(NoteDTO dto) {
		return noteDAO.updateNote(dto);
	}

	@Override
	public List<NoteDTO> getNoteList(String keyword, String tag, String tagParam, String order, int page, int pageSize) {
		return noteDAO.getNoteList(keyword, tag, tagParam, order, page, pageSize);
	}

	@Override
	public List<NoteDTO> getNoteListByOli_no(int oli_no) {
		return noteDAO.getNoteListByOli_no(oli_no);
	}
	
	@Override
	public NoteDTO getNote(int n_no) {
		return noteDAO.getNote(n_no);
	}

	@Override
	public NoteDTO getNoteByOli_noM_no(int oli_no, int m_no) {
		return noteDAO.getNoteByOli_noM_no(oli_no, m_no);
	}
	
	@Override
	public int getNoteTotalCount(String keyword, String tag, String tagParam) {
		return noteDAO.getNoteTotalCount(keyword, tag, tagParam);
	}

	@Override
	public int insertNoteArticle(NoteArticleDTO dto) {
		return noteArticleDAO.insertNoteArticle(dto);
	}

	@Override
	public int updateNoteArticle(NoteArticleDTO dto) {
		return noteArticleDAO.updateNoteArticle(dto);
	}

	@Override
	public int deleteNoteArticle(int na_no) {
		return noteArticleDAO.deleteNoteArticle(na_no);
	}

	@Override
	public NoteArticleDTO getNoteArticleByN_noOlv_no(int n_no, int olv_no) {
		return noteArticleDAO.getNoteArticleByN_noOlv_no(n_no, olv_no);
	}

	@Override
	public List<NoteArticleDTO> getNoteArticleList(int n_no) {
		return noteArticleDAO.getNoteArticleList(n_no);
	}

	@Override
	public NoteArticleDTO getNoteArticle(int na_no) {
		return noteArticleDAO.getNoteArticle(na_no);
	}

	@Override
	public List<NoteReplyDTO> getNoteReplyList(int n_no) {
		return noteReplyDAO.getNoteReplyList(n_no);
	}

	@Override
	public int submitNoteReply(NoteReplyDTO dto) {
		return noteReplyDAO.submitNoteReply(dto);
	}

	@Override
	public int getNoteLikeCount(int oli_no) {
		return noteLikeDAO.getNoteLikeCount(oli_no);
	}

	@Override
	public int insertNoteLike(int oli_no, int m_no) {
		return noteLikeDAO.insertNoteLike(oli_no, m_no);
	}

	@Override
	public int deleteNoteLike(int oli_no, int m_no) {
		return noteLikeDAO.deleteNoteLike(oli_no, m_no);
	}

	@Override
	public int existNoteLike(int oli_no, int m_no) {
		return noteLikeDAO.existNoteLike(oli_no, m_no);
	}

	@Override
	public int insertHistoryOrderNote(HistoryOrderNoteDTO dto) {
		return historyOrderNoteDAO.insertHistoryOrderNote(dto);
	}

	@Override
	public HistoryOrderNoteDTO getHistoryOrderNoteByN_noM_no(int n_no, int m_no) {
		return historyOrderNoteDAO.getHistoryOrderNoteByN_noM_no(n_no, m_no);
	}

	@Override
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteList(int m_no) {
		return historyOrderNoteDAO.getHistoryOrderNoteList(m_no);
	}
}
