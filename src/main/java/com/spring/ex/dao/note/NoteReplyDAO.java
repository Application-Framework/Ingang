package com.spring.ex.dao.note;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.note.NoteReplyDTO;

@Repository
public interface NoteReplyDAO {
	// 리뷰 리스트 가져오기
	public List<NoteReplyDTO> getNoteReplyList(int n_no);
	
	// 리뷰 가져오기
	public NoteReplyDTO getNoteReply(int nr_no);

	// 리뷰 등록
	public int submitNoteReply(NoteReplyDTO dto);
	
	// 리뷰 수정
	public int updateNoteReply(NoteReplyDTO dto);
	
	// 리뷰 삭제
	public int deleteNoteReply(int nr_no);
}
