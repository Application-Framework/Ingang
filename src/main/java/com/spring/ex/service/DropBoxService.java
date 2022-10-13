package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.HistoryOrderLectureDTO;

@Service
public interface DropBoxService {
	
	// 드롭박스 관심 강의 내역
	public List<HistoryOrderLectureDTO> myInterestedCourseDropBox(Integer m_no) throws Exception;
	
	// 드롭박스 관심 강의 갯수
	public Integer myInterestedCourseCountDropBox(Integer m_no) throws Exception;
}
