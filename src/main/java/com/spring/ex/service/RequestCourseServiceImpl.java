package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.course.request.RequestCourseDAO;
import com.spring.ex.dao.course.request.RequestCourseSubTypeDAO;
import com.spring.ex.dao.course.request.RequestCourseTagDAO;
import com.spring.ex.dao.course.request.RequestCourseVideoDAO;
import com.spring.ex.dto.MainTypeDTO;
import com.spring.ex.dto.course.request.RequestCourseDTO;
import com.spring.ex.dto.course.request.RequestCourseSubTypeDTO;
import com.spring.ex.dto.course.request.RequestCourseTagDTO;
import com.spring.ex.dto.course.request.RequestCourseVideoDTO;

@Service
public class RequestCourseServiceImpl implements RequestCourseService {

	@Inject
	private RequestCourseDAO requestCourseDAO;

	@Inject
	private RequestCourseSubTypeDAO requestCourseSubTypeDAO;
	
	@Inject
	private RequestCourseTagDAO requestCourseTagDAO;
	
	@Inject
	private RequestCourseVideoDAO requestCourseVideoDAO;
	
	@Inject
	private t_TagService t_TagService;
	
	@Inject
	private TypeService typeService;
	
	
	//-----------------------------
	// 강의 부분
	//-----------------------------
	
	// 강의 등록/수정 요청 추가
	@Override
	public int insertRequestCourse(RequestCourseDTO dto) {
		return requestCourseDAO.insertRequestCourse(dto);
	}

	// 강의 등록/수정 요청 수정
	@Override
	public int updateRequestCourse(RequestCourseDTO dto) {
		return requestCourseDAO.updateRequestCourse(dto);
	}

	// 강의 등록/수정 요청 승인
	@Override
	public int approveRequestCourse(int olr_no) {
		RequestCourseDTO dto = requestCourseDAO.selectOneRequestCourse(olr_no);
		dto.setApproval(1);
		dto.setRejection_message(null);
		return requestCourseDAO.updateRequestCourse(dto);
	}

	// 강의 등록/수정 요청 거절
	@Override
	public int rejectRequestCourse(int olr_no, String rejectMessage) {
		RequestCourseDTO dto = requestCourseDAO.selectOneRequestCourse(olr_no);
		dto.setApproval(-1);
		dto.setRejection_message(rejectMessage);
		return requestCourseDAO.updateRequestCourse(dto);
	}
	
	// 승인 상태에 따라 강의 요청 튜플들 가져오기
	@Override
	public List<RequestCourseDTO> getRequestCourseList(int approval) {
		return requestCourseDAO.selectListRequestCourse(approval);
	}

	
	//-----------------------------
	// 강의 서브 카테고리 부분
	//-----------------------------
	
	// 강의의 서브 카테고리 리스트 가져오기
	@Override
	public List<RequestCourseSubTypeDTO> getRequestCourseSubTypeList(int roli_no) {
		return requestCourseSubTypeDAO.getRequestCourseSubTypeList(roli_no);
	}

	// 강의에 서브 카테고리 등록
	@Override
	public int submitRequestCourseSubType(RequestCourseSubTypeDTO dto) {
		return requestCourseSubTypeDAO.insertRequestCourseSubType(dto);
	}

	// 강의의 서브 카테고리 삭제
	@Override
	public int deleteRequestCourseSubType(int roli_no) {
		return requestCourseSubTypeDAO.deleteRequestCourseSubType(roli_no);
	}

	// 강좌의 메인 카테고리 가져오기
	@Override
	public MainTypeDTO getMainTypeOfCourse(int roli_no) {
		MainTypeDTO dto = typeService.getMainTypeByMainTypeNo(typeService.getSubTypeBySubTypeNo(requestCourseSubTypeDAO.getRequestCourseSubTypeList(roli_no).get(0).getSub_type_no()).getMain_type_no());
		return dto;
	}

	
	//-----------------------------
	// 강의 태그 부분
	//-----------------------------
	
	// 강의에 태그 등록
	@Override
	public int submitRequestCourseTag(RequestCourseTagDTO dto) {
		return requestCourseTagDAO.submitRequestCourseTag(dto);
	}

	// 강의 태그 삭제
	@Override
	public int deleteRequestCourseTag(int roli_no) {
		return requestCourseTagDAO.deleteRequestCourseTag(roli_no);
	}

	// 태그 리스트에 태그가 있는지 확인
	@Override
	public boolean containsInTagList(List<RequestCourseTagDTO> tagList, String tag_abbr) {
		if(tagList == null) return false;
		for(RequestCourseTagDTO tag : tagList) {
			if(tag.getTag_no() == t_TagService.getTagByTag_abbr(tag_abbr).getTag_no())
				return true;
		}
		return false;
	}

	// 카테고리 리스트에 카테고리가 있는지 확인
	@Override
	public boolean containsInCategoryList(List<RequestCourseSubTypeDTO> categoryList, String tag_abbr) {
		if(categoryList == null) return false;
		for(RequestCourseSubTypeDTO category : categoryList) {
			if(category.getSub_type_no() == typeService.getSubTypeBySubTypeAbbr(tag_abbr).getSub_type_no())
				return true;
		}
		return false;
	}

	
	//-----------------------------
	// 강의 비디오 부분
	//-----------------------------
	
	// 강의 비디오 등록
	@Override
	public int submitRequestCourseVideo(RequestCourseVideoDTO dto) {
		return requestCourseVideoDAO.submitRequestCourseVideo(dto);
	}

	// 강의 비디오 삭제
	@Override
	public int deleteRequestCourseVideo(int roli_no) {
		return requestCourseVideoDAO.deleteRequestCourseVideo(roli_no);
	}

	// 강의 비디오 리스트 가져오기
	@Override
	public List<RequestCourseVideoDTO> getRequestCourseVideoList(int roli_no) {
		return requestCourseVideoDAO.getRequestCourseVideoList(roli_no);
	}

	// 강의 비디오 가져오기
	@Override
	public RequestCourseVideoDTO getRequestCourseVideo(int rolv_no) {
		return requestCourseVideoDAO.getRequestCourseVideo(rolv_no);
	}
}
