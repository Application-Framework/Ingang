package com.spring.ex.dao.course;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseLikeDAO {
	// 좋아요 개수 가져오기
	public int getCourseLikeCount(int oli_no);
	
	// 좋아요 추가
	public int insertCourseLike(int oli_no, int m_no);
	
	// 좋아요 삭제
	public int deleteCourseLike(int oli_no, int m_no);
	
	// 좋아요가 존재하는지
	public int existCourseLike(int oli_no, int m_no);
}
