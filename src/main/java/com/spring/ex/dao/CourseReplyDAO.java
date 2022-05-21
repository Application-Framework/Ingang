package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CourseReplyDTO;

@Repository
public interface CourseReplyDAO {
	
	// 리뷰 가져오기
	public List<CourseReplyDTO> getCourseReplys(int oli_no);
}
