package com.spring.ex.service;

import java.util.List;

import com.spring.ex.dto.course.CourseReplyDTO;

public interface LiveCourseReplyService {
	
	// 실시간 댓글
	public List<CourseReplyDTO> live_replyList() throws Exception;
	
}
