package com.spring.ex.dao.note;

import org.springframework.stereotype.Repository;

@Repository
public interface NoteLikeDAO {
	// 좋아요 개수 가져오기
	public int getNoteLikeCount(int n_no);
	
	// 좋아요 추가
	public int insertNoteLike(int n_no, int m_no);
	
	// 좋아요 삭제
	public int deleteNoteLike(int n_no, int m_no);
	
	// 좋아요가 존재하는지
	public int existNoteLike(int n_no, int m_no);
}
