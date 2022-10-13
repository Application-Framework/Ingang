package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.DropBoxDAO;
import com.spring.ex.dto.HistoryOrderLectureDTO;

@Service
public class DropBoxServiceImpl implements DropBoxService {
	
	@Inject
	private DropBoxDAO dao;
	
	// 드롭박스 관심 강의 내역
	@Override
	public List<HistoryOrderLectureDTO> myInterestedCourseDropBox(Integer m_no) throws Exception {
		return dao.myInterestedCourseDropBox(m_no);
	}
	
	// 드롭박스 관심 강의 갯수
	@Override
	public Integer myInterestedCourseCountDropBox(Integer m_no) throws Exception {
		return dao.myInterestedCourseCountDropBox(m_no);
	}
}
