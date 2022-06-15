package com.spring.ex.dao.course;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.HistoryOrderLectureDTO;

@Repository
public class HistoryOrderLectureImpl implements HistoryOrderLectureDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public int insertHistoryOrderLecture(HistoryOrderLectureDTO dto) {
		return sqlSession.insert(namespace + ".insertHistoryOrderLecture", dto);
	}

	@Override
	public HistoryOrderLectureDTO getHistoryOrderLectureByOli_noM_no(int oli_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("oli_no", oli_no);
		map.put("m_no", m_no);
		return sqlSession.selectOne(namespace + ".getHistoryOrderLectureByOli_noM_no", map);
	}

	@Override
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureList(int m_no) {
		return sqlSession.selectList(namespace + ".getHistoryOrderLectureList", m_no);
	}

}
