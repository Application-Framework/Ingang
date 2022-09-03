package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.CommunityBoardDAO;
import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.CommunityBoardReplyDTO;
import com.spring.ex.dto.CommunityBoardTagDTO;
import com.spring.ex.dto.course.CourseReplyDTO;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService{
	
	@Inject
	private CommunityBoardDAO dao;
	
	//게시판 최신순 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardChatRegDateShowPage(HashMap<String, Object> map) throws Exception {
		return dao.getCommunityBoardChatRegDateShowPage(map);
	}
	
	//게시판 좋아요순 출력
	@Override
	public List<CommunityBoardDTO> getCommunityBoardChatGoodShowPage(HashMap<String, Object> map) throws Exception {
		return dao.getCommunityBoardChatGoodShowPage(map);
	}
	
	//게시판 목록 총 갯수 - 페이징
	@Override
	public int getCommunityBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return dao.getCommunityBoardTotalCount(map);
	}

	//게시글 상세페이지 출력 
	@Override
	public Map<String, Object> getReadCommunityBoard(HashMap<String, Object> map) throws Exception {
		return dao.getReadCommunityBoard(map);
	}
	
	//게시글 상세페이지 댓글 출력
	@Override
	public List<CommunityBoardReplyDTO> getReplyCommunityBoard(Integer cb_no) throws Exception {
		return dao.getReplyCommunityBoard(cb_no);
	}
	
	//게시물 조회수 증가
	@Override
	public void addReadCommunityBoardHit(int cb_no) throws Exception {
		dao.addReadCommunityBoardHit(cb_no);
	}
	
	//게시물 좋아요 유무 체크
	@Override
	public int getGoodCheckReadCommunityBoard(HashMap<String, Integer> map) throws Exception {
		return dao.getGoodCheckReadCommunityBoard(map);
	}
	
	//게시물 좋아요 추가
	@Override
	public int addGoodReadCommunityBoard(HashMap<String, Object> map) throws Exception {
		return dao.addGoodReadCommunityBoard(map);
	}
	
	//게시물 좋아요 삭제
	@Override
	public int subtractGoodReadCommunityBoard(HashMap<String, Object> map) throws Exception {
		return dao.subtractGoodReadCommunityBoard(map);
	}
	
	//게시물 댓글 작성
	@Override
	public int writeReplyCommunityBoard(CommunityBoardReplyDTO dto) throws Exception {
		return dao.writeReplyCommunityBoard(dto);
	}
	
	//게시물 댓글 수정
	@Override
	public int updateReplyCommunityBoard(int cbr_no) throws Exception {
		return dao.updateReplyCommunityBoard(cbr_no);
	}
	
	//게시물 댓글 삭제
	@Override
	public int deleteReplyCommunityBoard(int cbr_no) throws Exception{
		return dao.deleteReplyCommunityBoard(cbr_no);
	}
	
	//게시물 작성
	@Override
	public int writeCommunityBoard(CommunityBoardDTO dto) throws Exception {
		return dao.writeCommunityBoard(dto);
	}
	
	//게시물 수정
	@Override
	public int updateCommunityBoard(CommunityBoardDTO dto) throws Exception {
		return dao.updateCommunityBoard(dto);
	}
	
	//게시물 삭제
	@Override
	public int deleteCommunityBoard(int cb_no) throws Exception {
		return dao.deleteCommunityBoard(cb_no);
	}
	
	//태그 출력
	@Override
	public List<CommunityBoardTagDTO> getTagCommunityBoard(int cb_no) throws Exception {
		return dao.getTagCommunityBoard(cb_no);
	}
	
	//수강후기 게시판 출력
	@Override
	public List<CourseReplyDTO> getReviewCommunityBoard(HashMap<String, Object> map) throws Exception {
		return dao.getReviewCommunityBoard(map);
	}
	
	//수강후기 게시판 목록 총 갯수 - 페이징
	@Override
	public int getReviewCommunityBoardTotalCount(HashMap<String, Object> map) throws Exception {
		return dao.getReviewCommunityBoardTotalCount(map);
	}
	
	//게시판 해결됨, 모집종료로 변경
	public int updateCompletedCommunityBoard(HashMap<String, Object> map) throws Exception {
		return dao.updateCompletedCommunityBoard(map);
	}

	//2022-09-02 김홍일 / 강의 상세 페이지의 커뮤니티 탭에 표시될 내용
	@Override
	public List<CommunityBoardDTO> selectCommunityBoardByOli_no(HashMap<String, Object> map) throws Exception {
		return dao.selectCommunityBoardByOli_no(map);
	}

	//2022-09-02 김홍일 / 강의 상세 페이지의 커뮤니티 탭에 표시될 내용 개수
	@Override
	public int selectCommunityBoardTotalCountByOli_no(HashMap<String, Object> map) throws Exception {
		return dao.selectCommunityBoardTotalCountByOli_no(map);
	}

	@Override
	public int selectCommunityBoardReplyCount(int cb_no) throws Exception {
		return dao.selectCommunityBoardReplyCount(cb_no);
	}

	@Override
	public int selectCommunityBoardGoodCount(int cb_no) throws Exception {
		return dao.selectCommunityBoardGoodCount(cb_no);
	}
}