package com.spring.ex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.dto.HistoryOrderNoteDTO;

@Repository
public class HistoryOrderDAOImpl implements HistoryOrderDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.HistoryOrderMapper";
	
	// 강의 구매 내역
	@Override
	public List<HistoryOrderLectureDTO> myPurchaseCourseList(Integer m_no) throws Exception {
		
		return sqlSession.selectList(namespace + ".myPurcaseCourses", m_no);
	}

	// 강의 관심 내역
	@Override
	public List<HistoryOrderLectureDTO> myInterestCourseList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myInterestCourses", m_no);
	}
	
	// 내 강의 목록
	@Override
	public List<HistoryOrderLectureDTO> searchMyPurcaseCourses(Integer m_no, String keyword) throws Exception {
			
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("m_no", m_no);
		data.put("keyword", keyword);
		
		return sqlSession.selectList(namespace + ".searchMyPurcaseCourses", data);
	}

	// 강의 구매 내역 추가
	@Override
	public int insertHistoryOrderLecture(HistoryOrderLectureDTO dto) {
		return sqlSession.insert(namespace + ".insertHistoryOrderLecture", dto);
	}

	// 강의 구매 내역 리스트 가져오기
	@Override
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureListByOli_no(int oli_no) {
		return sqlSession.selectList(namespace + ".getHistoryOrderLectureListByOli_no", oli_no);
	}
	
	// 강의 구매 내역 가져오기
	@Override
	public HistoryOrderLectureDTO getHistoryOrderLectureByOli_noM_no(int oli_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("oli_no", oli_no);
		map.put("m_no", m_no);
		return sqlSession.selectOne(namespace + ".getHistoryOrderLectureByOli_noM_no", map);
	}
	
	// 오늘부터 6일전까지의 강의 주문내역 합계 가져오기
	@Override
	public List<Map<String, Object>> getCourseOrderBy7Days() {
		return sqlSession.selectList(namespace + ".getCourseOrderBy7Days");
	}
	
	// 노트 구매 내역
	@Override
	public List<HistoryOrderNoteDTO> myPurchaseNoteList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myPurcaseNotes", m_no);
	}
	
	// 노트 관심 내역
	@Override
	public List<HistoryOrderNoteDTO> myInterestNoteList(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myInterestNotes", m_no);
	}

	// 노트 구매 내역 추가
	@Override
	public int insertHistoryOrderNote(HistoryOrderNoteDTO dto) {
		return sqlSession.insert(namespace + ".insertHistoryOrderNote", dto);
	}

	@Override
	public List<HistoryOrderNoteDTO> getHistoryOrderNoteByN_no(int n_no) {
		return sqlSession.selectList(namespace + ".getHistoryOrderNoteByN_no", n_no);
	}
	
	// 노트 구매 내역 가져오기
	@Override
	public HistoryOrderNoteDTO getHistoryOrderNoteByN_noM_no(int n_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("n_no", n_no);
		map.put("m_no", m_no);
		return sqlSession.selectOne(namespace + ".getHistoryOrderNoteByN_noM_no", map);
	}

	// 오늘부터 6일전까지의 노트 주문내역 합계 가져오기
	@Override
	public List<Map<String, Object>> getNoteOrderBy7Days() {
		return sqlSession.selectList(namespace + ".getNoteOrderBy7Days");
	}

	// 오늘의 노트 등록 개수
	@Override
	public int getTodaySubmittedNoteCount() {
		return sqlSession.selectOne(namespace + ".getTodaySubmittedNoteCount");
	}
}
