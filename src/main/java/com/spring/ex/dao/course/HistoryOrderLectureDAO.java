package com.spring.ex.dao.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.HistoryOrderLectureDTO;

@Repository
public interface HistoryOrderLectureDAO {
	public int insertHistoryOrderLecture(HistoryOrderLectureDTO dto);
	public HistoryOrderLectureDTO getHistoryOrderLectureByOli_noM_no(int oli_no, int m_no);
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureList(int m_no);
}
