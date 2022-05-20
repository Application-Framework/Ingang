package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.CourseDAO;

@Service
public class CourceServiceImpl implements CourseService {

	@Inject
	private CourseDAO dao;
	
	@Override
	public List<HashMap<String, Object>> getCoursePage(HashMap<String, Object> map) {
		return dao.getCoursePage(map);
	}

	@Override
	public int getCourseTotalCount(HashMap<String, Object> map) {
		return dao.getCourseTotalCount(map);
	}

}
