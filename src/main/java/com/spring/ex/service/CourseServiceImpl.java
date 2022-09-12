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
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseFileUploadDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.dto.course.HistoryOrderLectureDTO;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Resource(name="localResourcePath")
	String localResourcePath;
	
	@Resource(name="imagePath")
	String imagePath;
	
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

	// 파일 검색
	@Override
	public List<CourseFileUploadDTO> selectFileListByOli_no(int oli_no) throws Exception {
		return courseDAO.selectFileListByOli_no(oli_no);
	}
	
	// 반환값이 String인 파일 검색 
	@Override
	public List<String> selectUrlListByOli_no(int oli_no) throws Exception {
		List<CourseFileUploadDTO> fileUploadDTOList = courseDAO.selectFileListByOli_no(oli_no);
		List<String> stringList = new ArrayList<String>();
		for(int i = 0; i < fileUploadDTOList.size(); i++) {
			stringList.add(fileUploadDTOList.get(i).getUrl());
		}
		return stringList;
	}

	// 파일 추가
	@Override
	public int insertFile(CourseFileUploadDTO dto) throws Exception {
		return courseDAO.insertFile(dto);
	}

	// 파일 삭제
	@Override
	public int deleteFileByUrl(String url) throws Exception {
		return courseDAO.deleteFileByUrl(url);
	}

	// 서버, 로컬, 데이터베이스에서 파일 삭제
	@Override
	public int deleteFileEveryWhere(String url, String contextRoot) throws Exception {
		try {
			File serverFile = new File(contextRoot + url);
			File localFile = new File(localResourcePath + imagePath + serverFile.getName());
			serverFile.delete();
			localFile.delete();
			
			System.out.println("사용되지 않는 이미지 삭제");
			System.out.println("서버 삭제 : " + serverFile.getPath().toString());
			System.out.println("로컬 삭제 : " + localFile.getPath().toString());
			deleteFileByUrl(url); 					  // database
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	// 메인의 url에 없는 것은 삭제
	@Override
	public void deleteFileNotInMain(List<String> main, List<String> target, String contextRoot) throws Exception {
		for(String url : target) {
			if(!main.contains(url)) {
				deleteFileEveryWhere(url, contextRoot);
			}
		}
	}
	
	// html 태그의 img src 값을 리스트로 반환
	@Override
	public List<String> convertHtmlToSrcList(String html) throws Exception {
		List<String> srcList = new ArrayList<String>();
		Document doc = Jsoup.parseBodyFragment(html);
		Elements imgs = doc.getElementsByTag("img");
		for(int i = 0; i < imgs.size(); i++)
			srcList.add(imgs.get(i).attr("src"));
		return srcList;
	}

	// srcList를 로컬 저장소에 복사
	@Override
	public void copySrcListToLocalAndDB(List<String> srcList, int oli_no, String contextRoot) throws Exception {
		for(int i = 0; i < srcList.size(); i++) {
			// http://localhost:8080/resources와 같은 형식일 때는 복사하지 않기
			if(srcList.get(i).indexOf("/resources") != 0) {
				continue;
			}
			File file = new File(contextRoot + srcList.get(i));
			
			// 기존에 있는 파일이면 복사 x
			File newFile = new File(localResourcePath + imagePath + file.getName());
			if(!newFile.isFile()) {
				Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("로컬에 이미지 복사 : " + newFile.toPath().toString());
				
				CourseFileUploadDTO cfuDTO = new CourseFileUploadDTO();
				cfuDTO.setOli_no(oli_no);
				cfuDTO.setUrl(srcList.get(i));
				insertFile(cfuDTO);
			}
		}
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
