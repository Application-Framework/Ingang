package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.HistoryOrderLectureDTO;

@Repository
public interface DropBoxDAO {
	
	// 드롭박스 관심 강의 내역
	public List<HistoryOrderLectureDTO> myInterestedCourseDropBox(Integer m_no) throws Exception;
	
	// 드롭박스 관심 강의 갯수
	public Integer myInterestedCourseCountDropBox(Integer m_no) throws Exception;
}
