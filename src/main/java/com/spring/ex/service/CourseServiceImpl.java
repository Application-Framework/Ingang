package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.TypeDAO;
import com.spring.ex.dao.course.CourseDAO;
import com.spring.ex.dao.course.CourseLikeDAO;
import com.spring.ex.dao.course.CourseReplyDAO;
import com.spring.ex.dao.course.CourseSubTypeDAO;
import com.spring.ex.dao.course.CourseTagDAO;
import com.spring.ex.dao.course.CourseVideoDAO;
import com.spring.ex.dao.course.HistoryOrderLectureDAO;
import com.spring.ex.dto.MainTypeDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseSubTypeDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.dto.course.HistoryOrderLectureDTO;

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
	private HistoryOrderLectureDAO historyOrderLectureDAO;
	
	@Inject
	private t_TagService t_TagService;
	
	@Inject
	private TypeService typeService;
	
	@Inject
	private CourseSubTypeDAO courseSubTypeDAO;
	
	@Inject
	private TypeDAO typeDAO;
	
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
	public int existCourseLike(int oli_no, int m_no) {
		return courseLikeDAO.existCourseLike(oli_no, m_no);
	}

	@Override
	public int submitCourse(CourseDTO courseDTO) {
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
	public CourseVideoDTO getCourseVideo(int olv_no) {
		return courseVideoDAO.getCourseVideo(olv_no);
	}

	@Override
	public int insertHistoryOrderLecture(HistoryOrderLectureDTO dto) {
		return historyOrderLectureDAO.insertHistoryOrderLecture(dto);
	}
	
	@Override
	public HistoryOrderLectureDTO getHistoryOrderLectureByOli_noM_no(int oli_no, int m_no) {
		return historyOrderLectureDAO.getHistoryOrderLectureByOli_noM_no(oli_no, m_no);
	}
	
	@Override
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureList(int m_no) {
		return historyOrderLectureDAO.getHistoryOrderLectureList(m_no);
	}

	@Override
	public boolean containsInTagList(List<CourseTagDTO> tagList, String tag_abbr) {
		if(tagList == null) return false;
		for(CourseTagDTO tag : tagList) {
			if(tag.getTag_no() == t_TagService.getTagByTag_abbr(tag_abbr).getTag_no())
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
		MainTypeDTO dto = typeDAO.getMainTypeByMainTypeNo(typeDAO.getSubTypeBySubTypeNo(courseSubTypeDAO.getCourseSubTypeList(oli_no).get(0).getSub_type_no()).getMain_type_no());
		return dto;
	}
}
