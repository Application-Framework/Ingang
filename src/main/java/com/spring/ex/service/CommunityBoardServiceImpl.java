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
import com.spring.ex.dto.CommunityTagListDTO;
import com.spring.ex.dto.CommunityTagSerachDTO;
import com.spring.ex.dto.InquiryAnswerDTO;
import com.spring.ex.dto.InquiryDTO;
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
	
	//인기태그 출력
	@Override
	public List<CommunityTagListDTO> getPopularityTagCommunity(HashMap<String, Object> map) throws Exception {
		return dao.getPopularityTagCommunity(map);
	}
	
	//존재하는 태그 명인지 체크
	@Override
	public int isCheckTagSearchList(String ctl_name) throws Exception {
		return dao.isCheckTagSearchList(ctl_name);
	}
	
	//존재하지 않는 태그명이면 삽입후 값 반환
	@Override
	public int insertTagList(CommunityTagListDTO dto ) throws Exception {
		return dao.insertTagList(dto);
	}
	
	//커뮤니티 태그 검색 기록
	@Override
	public void serachTagRecord(CommunityTagSerachDTO dto) throws Exception {
		dao.serachTagRecord(dto);
	}
	
	//태그 여부 확인 및  검색 기록 저장
	@Override
	public void doIsCheckAndRecordSerachTag(String[] tagList, HashMap<String, Object> map) throws Exception {
		CommunityTagSerachDTO ctsDTO = new CommunityTagSerachDTO();
		CommunityTagListDTO ctlDTO = new CommunityTagListDTO();
		for(String t : tagList) {
			int totalCount = getCommunityBoardTotalCount(map);
			int isCheckTag = isCheckTagSearchList(t);
			System.out.println(isCheckTag);
			if(isCheckTag == 0 ) {
				ctlDTO.setCtl_name(t);
				int insertTagNum = insertTagList(ctlDTO);
				
				ctsDTO.setCtl_no(ctlDTO.getCtl_no());
				ctsDTO.setClassify(1);
				ctsDTO.setCts_found(0);
				
				serachTagRecord(ctsDTO);
				
			}else {
				if(totalCount > 0) {
					ctsDTO.setCtl_no(isCheckTag);
					ctsDTO.setClassify(1);
					ctsDTO.setCts_found(1);
					
					serachTagRecord(ctsDTO);
				}else {
					ctsDTO.setCtl_no(isCheckTag);
					ctsDTO.setClassify(1);
					ctsDTO.setCts_found(0);
					
					serachTagRecord(ctsDTO);
				}
			}
		}
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
	@Override
	public int updateCompletedCommunityBoard(HashMap<String, Object> map) throws Exception {
		return dao.updateCompletedCommunityBoard(map);
	}
	
	//1:1문의하기 게시판 출력
	@Override
	public List<InquiryDTO> getCommunityBoardInquiryPage(HashMap<String, Object> map) throws Exception {
		return dao.getCommunityBoardInquiryPage(map);
	}
	
	//1:1문의하기 게시판 총 갯수
	@Override
	public int getCommunityBoardInquiryPageTotalCount(HashMap<String, Object> map) throws Exception {
		return dao.getCommunityBoardInquiryPageTotalCount(map);
	}
	
	//1:1문의하기 작성
	@Override
	public int writeInquiry(InquiryDTO dto) throws Exception {
		return dao.writeInquiry(dto);
	}
	
	//1:1문의하기 상세페이지
	@Override
	public Map<String, Object> getInquiryViewPage(int inq_no) throws Exception {
		return dao.getInquiryViewPage(inq_no);
	}
	
	//1:1문의하기 삭제
	@Override
	public int deleteInquiry(int inq_no) throws Exception {
		return dao.deleteInquiry(inq_no);
	}
	
	//1:1문의 답변 작성
	@Override
	public int writeInquiryAnswer(InquiryAnswerDTO dto) throws Exception {
		return dao.writeInquiryAnswer(dto);
	}
	
	//1:1문의 답변 삭제
	@Override
	public int deleteInquiryAnswer(int ia_no) throws Exception {
		return dao.deleteInquiryAnswer(ia_no);
	}
	
	//1:1문의 답변완료 상태로 변경
	@Override
	public int updateStatementAnswerOk(int inq_no) throws Exception {
		return dao.updateStatementAnswerOk(inq_no);
	}
	
	//1:1문의 답변보류 상태로 변경
	@Override
	public int updateStatementAnswerDelay(int inq_no) throws Exception {
		return dao.updateStatementAnswerDelay(inq_no);
	}
	
	//1:1문의 답변대기 상태로 변경
	@Override
	public int updateStatementAnswerDelete(int inq_no) throws Exception {
		return dao.updateStatementAnswerDelete(inq_no);
	}
	
	//1:1문의 답변 수정
	public int updateInquiryAnswer(InquiryAnswerDTO dto) throws Exception {
		return dao.updateInquiryAnswer(dto);
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