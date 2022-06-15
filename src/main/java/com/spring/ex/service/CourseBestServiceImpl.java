package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.CourseBestDAO;
import com.spring.ex.dto.course.CourseDTO;

@Service
public class CourseBestServiceImpl implements CourseBestService {

	@Inject
	private CourseBestDAO courseBestDAO;

	// 이 주의 강의 목록
	@Override
	public List<CourseDTO> thisweek_bestCourseList() throws Exception {
		return courseBestDAO.thisweek_bestCourseList();
	}

	// 신규 강의
	@Override
	public List<CourseDTO> thisweek_newCourseList() throws Exception {
		return courseBestDAO.thisweek_newCourseList();
	}
}
