package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;

@Repository
public interface HistoryOrderDAO {
	
	// 강의 구매 내역
	public List<HistoryOrderLectureDTO> myPurchaseCourseList(Integer m_no) throws Exception;
	
	// 강의 관심 내역
	public List<HistoryOrderLectureDTO> myInterestCourseList(Integer m_no) throws Exception;
	
	// 내 강의 목록
	public List<HistoryOrderLectureDTO> searchMyPurcaseCourses(Integer m_no, String keyword) throws Exception;
	
	// 강의 구매 내역 추가
	public int insertHistoryOrderLecture(HistoryOrderLectureDTO dto);
	
	// 강의 구매 내역 가져오기
	public HistoryOrderLectureDTO getHistoryOrderLectureByOli_noM_no(int oli_no, int m_no);
	
	
	// 노트 구매 내역
	public List<HistoryOrderNoteDTO> myPurchaseNoteList(Integer m_no) throws Exception;
		
	// 노트 관심 내역
	public List<HistoryOrderNoteDTO> myInterestNoteList(Integer m_no) throws Exception;
	
	// 노트 구매 내역 추가
	public int insertHistoryOrderNote(HistoryOrderNoteDTO dto);
	
	// 노트 구매 내역 가져오기
	public HistoryOrderNoteDTO getHistoryOrderNoteByN_noM_no(int n_no, int m_no);
}
