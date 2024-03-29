package com.spring.ex.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;

@Service
public interface HistoryOrderService {
	
	// 강의 구매 내역
	public List<HistoryOrderLectureDTO> myPurchaseCourseList(Integer m_no) throws Exception;
	
	// 강의 관심 내역
	public List<HistoryOrderLectureDTO> myInterestCourseList(Integer m_no) throws Exception;
	
	// 내 강의 목록
	public List<HistoryOrderLectureDTO> searchMyPurcaseCourses(Integer m_no, String keyword) throws Exception;
	
	// 강의 구매 내역 추가
	public int insertHistoryOrderLecture(HistoryOrderLectureDTO dto);
	
	// 강의 구매 내역 리스트 가져오기
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureListByOli_no(int oli_no);
	
	// 강의 구매 내역 가져오기
	public HistoryOrderLectureDTO getHistoryOrderLectureByOli_noM_no(int oli_no, int m_no);
	
	// 오늘부터 6일전까지의 강의 주문내역 합계 가져오기
	public List<Map<String, Object>> getCourseOrderBy7Days();
	
	
	// 노트 구매 내역
	public List<HistoryOrderNoteDTO> myPurchaseNoteList(Integer m_no) throws Exception;
			
	// 노트 관심 내역
	public List<HistoryOrderNoteDTO> myInterestNoteList(Integer m_no) throws Exception;
	
	// 노트 구매 내역 추가
	public int insertHistoryOrderNote(HistoryOrderNoteDTO dto);
	
	// 노트 구매 내역 리스트 가져오기
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteByN_no(int n_no);
	
	// 노트 구매 내역 가져오기
	public HistoryOrderNoteDTO getHistoryOrderNoteByN_noM_no(int n_no, int m_no);
	
	// 오늘부터 6일전까지의 노트 주문내역 합계 가져오기
	public List<Map<String, Object>> getNoteOrderBy7Days();
	
	// 오늘의 노트 등록 개수
	public int getTodaySubmittedNoteCount();
}
