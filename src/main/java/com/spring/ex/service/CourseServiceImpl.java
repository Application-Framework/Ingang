package com.spring.ex.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.spring.ex.dao.TeacherDAO;
import com.spring.ex.dao.course.CourseDAO;
import com.spring.ex.dao.course.CourseLikeDAO;
import com.spring.ex.dao.course.CourseReplyDAO;
import com.spring.ex.dao.course.CourseTagDAO;
import com.spring.ex.dao.course.CourseVideoDAO;
import com.spring.ex.dao.course.HistoryOrderLectureDAO;
import com.spring.ex.dto.UploadedFileDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
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
	private TeacherDAO teacherDAO;
	
	@Inject
	private CourseLikeDAO courseLikeDAO;
	
	@Inject
	private CourseVideoDAO courseVideoDAO;
	
	@Inject
	private HistoryOrderLectureDAO historyOrderLectureDAO;
	
	@Override
	public List<HashMap<String, Object>> getCoursePage(HashMap<String, Object> map) {
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
		return starAvg;
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
	public TeacherDTO getTeacherInfo(int olt_no) {
		return teacherDAO.getTeacherInfo(olt_no);
	}
	
	@Override
	public TeacherDTO getTeacherInfoByM_no(int m_no) {
		return teacherDAO.getTeacherInfoByM_no(m_no);
	}
	
	@Override
	public int insertCourseTeacher(TeacherDTO dto) {
		return teacherDAO.insertCourseTeacher(dto);
	}

	@Override
	public int deleteCourseTeacher(int olt_no) {
		return teacherDAO.deleteCourseTeacher(olt_no);
	}

	@Override
	public int checkTeacherByM_no(int m_no) {
		return teacherDAO.checkTeacherByM_no(m_no);
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
	public boolean containsInTagList(List<CourseTagDTO> tagList, String tagName) {
		if(tagList == null) return false;
		for(CourseTagDTO tag : tagList) {
			if(tag.getTag().equals(tagName))
				return true;
		}
		return false;
	}

	
}
