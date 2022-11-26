package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.TypeDAO;
import com.spring.ex.dao.course.CourseDAO;
import com.spring.ex.dao.course.CourseLikeDAO;
import com.spring.ex.dao.course.CourseReplyDAO;
import com.spring.ex.dao.course.CourseRequestDAO;
import com.spring.ex.dao.course.CourseSubTypeDAO;
import com.spring.ex.dao.course.CourseTagDAO;
import com.spring.ex.dao.course.CourseVideoDAO;
import com.spring.ex.dto.MainTypeDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseRequestDTO;
import com.spring.ex.dto.course.CourseSubTypeDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Inject
	private CourseDAO courseDAO;
	
	@Inject
	private CourseReplyDAO courseReplyDAO;
	
	@Inject
	private CourseTagDAO courseTagDAO;
	
	@Inject
	private CourseLikeDAO courseLikeDAO;
	
	@Inject
	private CourseVideoDAO courseVideoDAO;
	
	@Inject
	private t_TagService tagService;
	
	@Inject
	private TypeService typeService;
	
	@Inject
	private CourseSubTypeDAO courseSubTypeDAO;
	
	@Inject
	private TypeDAO typeDAO;
	
	@Inject
	private CourseRequestDAO courseRequestDAO;
	
	@Inject
	private FileService fileService;
	
	@Override
	public List<CourseDTO> getCoursePage(HashMap<String, Object> map) {
		return courseDAO.getCoursePage(map);
	}

	@Override
	public int getCourseTotalCount(HashMap<String, Object> map) {
		return courseDAO.getCourseTotalCount(map);
	}

	@Override
	public CourseDTO getCourseDetail(int pageNo) {
		return courseDAO.getCourseDetail(pageNo);
	}

	@Override
	public List<CourseReplyDTO> getCourseReplys(int oli_no) {
		return courseReplyDAO.getCourseReplys(oli_no);
	}

	@Override
	public float getCourseStarAvg(int oli_no) {
		List<CourseReplyDTO> replys = getCourseReplys(oli_no);
		float starAvg = 0;
		if(replys.size() != 0) {
			for(CourseReplyDTO reply : replys)
				 starAvg += reply.getStar_rating();
			starAvg /= replys.size();
		}
		// 소수점 첫째자리까지 반올림
		starAvg = Math.round(starAvg * 10);
		return starAvg / 10;
	}
	
	@Override
	public List<CourseTagDTO> getCourseTags(int oli_no) {
		return courseTagDAO.getCourseTags(oli_no);
	}

	@Override
	public int getCourseLikeCount(int oli_no) {
		return courseLikeDAO.getCourseLikeCount(oli_no);
	}

	@Override
	public int insertCourseLike(int oli_no, int m_no) {
		return courseLikeDAO.insertCourseLike(oli_no, m_no);
	}

	@Override
	public int deleteCourseLike(int oli_no, int m_no) {
		return courseLikeDAO.deleteCourseLike(oli_no, m_no);
	}

	@Override
	public boolean existCourseLike(int oli_no, int m_no) {
		if(courseLikeDAO.existCourseLike(oli_no, m_no) == 1)
			return true;
		else return false;
	}

	@Override
	public int insertCourse(CourseDTO courseDTO) {
		return courseDAO.submitCourse(courseDTO);
	}

	@Override
	public int updateCourse(CourseDTO courseDTO) {
		return courseDAO.updateCourse(courseDTO);
	}
	
	@Override
	public int deleteCourse(int oli_no) throws Exception {
		return courseDAO.deleteCourse(oli_no);
	}
	
	@Override
	public int submitReply(CourseReplyDTO dto) {
		return courseReplyDAO.submitReply(dto);
	}

	@Override
	public int submitCourseTag(CourseTagDTO dto) {
		return courseTagDAO.submitCourseTag(dto);
	}

	@Override
	public int deleteCourseTag(int oli_no) {
		return courseTagDAO.deleteCourseTag(oli_no);
	}
	
	@Override
	public int submitCourseVideo(CourseVideoDTO dto) {
		return courseVideoDAO.submitCourseVideo(dto);
	}
	
	@Override
	public int deleteCourseVideo(int oli_no) {
		return courseVideoDAO.deleteCourseVideo(oli_no);
	}
	
	@Override
	public List<CourseVideoDTO> getCourseVideoList(int oli_no) {
		return courseVideoDAO.getCourseVideoList(oli_no);
	}
	
	@Override
	public CourseVideoDTO getCourseVideoByOli_noAndOrder(int oli_no, int order) {
		return courseVideoDAO.getCourseVideoByOli_noAndOrder(oli_no, order);
	}

	@Override
	public CourseVideoDTO getCourseVideo(int olv_no) {
		return courseVideoDAO.getCourseVideo(olv_no);
	}

	@Override
	public int updateCourseVideo(CourseVideoDTO dto) {
		return courseVideoDAO.updateCourseVideo(dto);
	}

	@Override
	public int deleteNotContainedCourseVideo(int oli_no, int[] olv_noList) {
		return courseVideoDAO.deleteNotContainedCourseVideo(oli_no, olv_noList);
	}
	
	@Override
	public boolean containsInTagList(List<CourseTagDTO> tagList, String tag_abbr) {
		if(tagList == null) return false;
		for(CourseTagDTO tag : tagList) {
			if(tag.getTag_no() == tagService.getTagByTag_abbr(tag_abbr).getTag_no())
				return true;
		}
		return false;
	}

	@Override
	public boolean containsInCategoryList(List<CourseSubTypeDTO> categoryList, String tag_abbr) {
		if(categoryList == null) return false;
		for(CourseSubTypeDTO category : categoryList) {
			if(category.getSub_type_no() == typeService.getSubTypeBySubTypeAbbr(tag_abbr).getSub_type_no())
				return true;
		}
		return false;
	}

	@Override
	public List<CourseSubTypeDTO> getCourseSubTypeList(int oli_no) {
		return courseSubTypeDAO.getCourseSubTypeList(oli_no);
	}

	@Override
	public int submitCourseSubType(CourseSubTypeDTO dto) {
		return courseSubTypeDAO.insertCourseSubType(dto);
	}

	@Override
	public int deleteCourseSubType(int oli_no) {
		return courseSubTypeDAO.deleteCourseSubType(oli_no);
	}

	@Override
	public MainTypeDTO getMainTypeOfCourse(int oli_no) {
		try {
			MainTypeDTO dto = typeDAO.getMainTypeByMainTypeNo(typeDAO.getSubTypeBySubTypeNo(courseSubTypeDAO.getCourseSubTypeList(oli_no).get(0).getSub_type_no()).getMain_type_no());
			return dto;
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int saveCourse(CourseDTO dto, String[] categorys, String[] tags, String[] olv_noList, String[] videoTitles, String[] videoPaths) {
		// 강의 수정일 때
		System.out.println("저장 시작");
		int origin_oli_no = dto.getOli_no();
		insertCourse(dto);
		if(origin_oli_no == 0) origin_oli_no = dto.getOli_no();
		
		for(String c : categorys) {
			CourseSubTypeDTO courseSubTypeDTO = new CourseSubTypeDTO();
			courseSubTypeDTO.setOli_no(dto.getOli_no());
			courseSubTypeDTO.setSub_type_no(Integer.parseInt(c));
			submitCourseSubType(courseSubTypeDTO);
		}
		
		for(String t : tags) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(dto.getOli_no());
			courseTagDTO.setTag_no(Integer.parseInt(t));
			submitCourseTag(courseTagDTO);
		}
		
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO courseVideoDTO = new CourseVideoDTO();
			courseVideoDTO.setOli_no(dto.getOli_no());
			courseVideoDTO.setTitle(videoTitles[i]);
			courseVideoDTO.setS_file_name(videoPaths[i]);
			courseVideoDTO.setOrder(i+1);
			submitCourseVideo(courseVideoDTO);
		}
		
		System.out.println("파일 저장 시작");
		
		// 게시글의 파일 관리
		try {
			fileService.manageFileAfterPostSubmission(dto.getContent(), dto.getOli_no(), 1); // category 강의 : 1
		}
		catch(Exception e) {
			System.err.println("강의 파일 저장 실패 : " + e.getMessage());
		}
		
		System.out.println("파일 저장 끝");
		
		// 강의 요청 대기열에 등록
		CourseRequestDTO cr = new CourseRequestDTO();
		cr.setOli_no(dto.getOli_no());
		cr.setOrigin_oli_no(origin_oli_no);
		cr.setApproval_status(0);
		courseRequestDAO.insertCourseRequest(cr);
		System.out.println("cr : " + cr);
		
		return 1;
	}
}
