package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.dto.UploadedFileDTO;

@Service
public interface FileService {
	
	// 로컬과 서버에서 파일 삭제
	public void deleteFileToLocalAndServer(String path) throws Exception;
	
	// DB에서 파일 리스트 불러오기
	public List<UploadedFileDTO> getFileList(int content_no, int category) throws Exception;
	
	// DB에 파일 추가
	public int insertFileToDB(UploadedFileDTO dto) throws Exception;
	
	// DB에서 파일 삭제
	public int deleteFileToDB(String url) throws Exception;
	
	// 서버에 파일 추가
	public String insertFileToServer(MultipartFile file, String path) throws Exception;
	
	// 로컬과 서버에서 파일 추가, 반환 값 : 생성한 파일의 /resource부터 경로
	// path : /resources의 하위 폴더('/resource' 포함 x)
	public String insertFileToLocalAndServer(MultipartFile file, String path) throws Exception;
	
	// 메인의 url에 없는 것은 삭제
	public void deleteFileNotInMain(List<UploadedFileDTO> main, List<UploadedFileDTO> target) throws Exception;
	
	// html 태그의 src 값을 파일 리스트로 반환
	public List<UploadedFileDTO> convertHtmlToFileList(String html, int content_no, int category) throws Exception;
	
	// fileList를 로컬 저장소와 DB에 복사
	public void copyFileListToLocalAndDB(List<UploadedFileDTO> fileList) throws Exception;
	
	/** 게시글 등록 후 파일 저장 및 불필요한 파일 정리
	 *	@param content_no 게시물의 key 값
	 *	@param category
	 *		1 : 강의
	 *		2 : 노트 메인
	 *		3 : 노트 서브 글
	 *		4 : 커뮤니티
	 * 
	 */
	public void manageFileAfterPostSubmission(String html, int content_no, int category) throws Exception;
	
	// 게시글의 모든 파일 삭제
	public void deleteAllFileOfPost(int content_no, int category) throws Exception;
}
