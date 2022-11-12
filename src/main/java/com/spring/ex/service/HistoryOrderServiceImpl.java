package com.spring.ex.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.HistoryOrderDAO;
import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;

@Service
public class HistoryOrderServiceImpl implements HistoryOrderService {
	
	@Inject
	private HistoryOrderDAO dao;
	
	// 강의 구매 내역
	@Override
	public List<HistoryOrderLectureDTO> myPurchaseCourseList(Integer m_no) throws Exception {
		return dao.myPurchaseCourseList(m_no);
	}
	
	// 강의 관심 내역
	@Override
	public List<HistoryOrderLectureDTO> myInterestCourseList(Integer m_no) throws Exception {
		return dao.myInterestCourseList(m_no);
	}

	// 내 강의 목록
	@Override
	public List<HistoryOrderLectureDTO> searchMyPurcaseCourses(Integer m_no, String keyword) throws Exception {
		return dao.searchMyPurcaseCourses(m_no, keyword);
	}

	// 강의 구매 내역 추가
	@Override
	public int insertHistoryOrderLecture(HistoryOrderLectureDTO dto) {
		return dao.insertHistoryOrderLecture(dto);
	}

	// 강의 구매 내역 리스트 가져오기
	@Override
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureListByOli_no(int oli_no) {
		return dao.getHistoryOrderLectureListByOli_no(oli_no);
	}
	
	// 강의 구매 내역 가져오기
	@Override
	public HistoryOrderLectureDTO getHistoryOrderLectureByOli_noM_no(int oli_no, int m_no) {
		return dao.getHistoryOrderLectureByOli_noM_no(oli_no, m_no);
	}
	
	// 오늘부터 6일전까지의 강의 주문내역 합계 가져오기
	@Override
	public List<Map<String, Object>> getCourseOrderBy7Days() {
		return dao.getCourseOrderBy7Days();
	}
	
	// 노트 구매 내역
	@Override
	public List<HistoryOrderNoteDTO> myPurchaseNoteList(Integer m_no) throws Exception {
		return dao.myPurchaseNoteList(m_no);
	}
	
	// 노트 관심 내역
	@Override
	public List<HistoryOrderNoteDTO> myInterestNoteList(Integer m_no) throws Exception {
		return dao.myInterestNoteList(m_no);
	}

	// 노트 구매 내역 추가
	@Override
	public int insertHistoryOrderNote(HistoryOrderNoteDTO dto) {
		return dao.insertHistoryOrderNote(dto);
	}
	
	// 노트 구매 내역 리스트 가져오기
	@Override
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteByN_no(int n_no) {
		return dao.getHistoryOrderNoteByN_no(n_no);
	}

	// 노트 구매 내역 가져오기
	@Override
	public HistoryOrderNoteDTO getHistoryOrderNoteByN_noM_no(int n_no, int m_no) {
		return dao.getHistoryOrderNoteByN_noM_no(n_no, m_no);
	}

	// 오늘부터 6일전까지의 노트 주문내역 합계 가져오기
	@Override
	public List<Map<String, Object>> getNoteOrderBy7Days() {
		return dao.getNoteOrderBy7Days();
	}
}
