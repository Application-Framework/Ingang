package com.spring.ex.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.dao.UploadedFileDAO;
import com.spring.ex.dto.UploadedFileDTO;

@Service
public class FileServiceImpl implements FileService {
	
	@Inject
	private UploadedFileDAO uploadedFileDAO;
	
	@Resource(name="localResourcePath")
	String localResourcePath;
	
	@Autowired
	ServletContext servletContext;
	String serverPath = servletContext.getRealPath("resources");
	
	// /resources 문자열만 제거
	String localPath = localResourcePath.substring(0, localResourcePath.length()-10);
	
	// 로컬과 서버에서 파일 추가, 반환 값 : 생성한 파일의 /resource부터 경로
	// path : /resources의 하위 폴더('/resource' 포함 x)
	@Override
	public String insertFileToLocalAndServer(MultipartFile file, String path) throws Exception {
		createFolder(localResourcePath + path);
		createFolder(serverPath + path);
		
		String originalFileName = file.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		// 로컬에 저장
		File localFile = new File(localResourcePath + path, savedFileName);
		file.transferTo(localFile);
		
		// refresh 없이 바로 적용되게 서버에 저장
		File serverFile = new File(serverPath + path, savedFileName);
		Files.copy(localFile.toPath(), serverFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		return "/resources" + path + "/" + savedFileName;
	}
	
	// 로컬과 서버에서 파일 삭제
	@Override
	public void deleteFileToLocalAndServer(String path) {
		// /resources 삭제
		//String localPath = localResourcePath.substring(0, localResourcePath.length()-10);
		File localFile = new File(localResourcePath + path);
		localFile.delete();
		System.out.println("delete localFile : " + localFile.getPath());
		
		File serverFile = new File(serverPath + path);
		serverFile.delete();
		System.out.println("delete serverFile : " + serverFile.getPath());
	}

	@Override
	public int insertFileToDB(UploadedFileDTO dto) throws Exception {
		return uploadedFileDAO.insertFile(dto);
	}

	@Override
	public int deleteFileToDB(String url) throws Exception {
		return uploadedFileDAO.deleteFile(url);
	}

	// DB에서 모든 파일 리스트 불러오기
	@Override
	public List<UploadedFileDTO> getFileList(int content_no, int category) throws Exception {
		return uploadedFileDAO.selectFileList(content_no, category);
	}
	
	// target에는 있고 main에 없는 파일 삭제
	@Override
	public void deleteFileNotInMain(List<UploadedFileDTO> main, List<UploadedFileDTO> target) throws Exception {
		for(UploadedFileDTO fileDTO : target) {
			if(!main.contains(fileDTO)) {
				deleteFileToLocalAndServer(localPath + fileDTO.getUrl());
				deleteFileToDB(fileDTO.getUrl());
			}
		}
	}

	// html 태그의 src 값을 파일 리스트로 반환
	@Override
	public List<UploadedFileDTO> convertHtmlToFileList(String html, int content_no, int category) throws Exception {
		List<UploadedFileDTO> fileList = new ArrayList<UploadedFileDTO>();
		Document doc = Jsoup.parseBodyFragment(html);
		
		// 현재 img만 추출. 추후 추가할 수도 있음
		Elements imgs = doc.getElementsByTag("img");
		for(int i = 0; i < imgs.size(); i++) {
			UploadedFileDTO fileDTO = new UploadedFileDTO();
			fileDTO.setContent_no(content_no);
			fileDTO.setCategory(category);
			fileDTO.setUrl(imgs.get(i).attr("src"));
			fileList.add(fileDTO);
		}
		
		return fileList;
	}

	// fileList를 로컬 저장소와 DB에 복사
	@Override
	public void copyFileListToLocalAndDB(List<UploadedFileDTO> fileList) throws Exception {
		for(UploadedFileDTO file : fileList) {
			// http://localhost:8080/resources와 같은 형식일 때는 복사하지 않기
			if(file.getUrl().indexOf("/resources") != 0) {
				continue;
			}
			File serverFile = new File(servletContext.getRealPath("") + file.getUrl());
			File localFile = new File(localPath + file.getUrl());
			
			if(!localFile.exists()) {
				// 서버에 임시 저장되어있던 파일을 로컬로 복사
				Files.copy(serverFile.toPath(), localFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				// DB에 추가
				insertFileToDB(file);
			}
		}
	}

	@Override
	public void manageFileAfterPostSubmission(String html, int content_no, int category) throws Exception {
		List<UploadedFileDTO> fileListByHTML = convertHtmlToFileList(html, content_no, category);
		// 게시물에 업로드된 파일 로컬과 DB에 저장
		copyFileListToLocalAndDB(fileListByHTML);
		
		List<UploadedFileDTO> allFileList = getFileList(content_no, category);
		// 현재 게시물에 포함되지 않는 파일은 전부 삭제
		deleteFileNotInMain(fileListByHTML, allFileList);
		
	}
	
	// 게시글의 모든 파일 삭제
	@Override
	public void deleteAllFileOfPost(int content_no, int category) throws Exception {
		List<UploadedFileDTO> fileList = getFileList(content_no, category);
		
		for(UploadedFileDTO fileDTO : fileList) {
			deleteFileToLocalAndServer(localPath + fileDTO.getUrl());
			deleteFileToDB(fileDTO.getUrl());
		}
	}
	
	public void createFolder(String path) {
		File folder = new File(path);
		
		if (!folder.exists()) {
			try{
			    folder.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
	        } 
	        catch(Exception e){
			    e.getStackTrace();
			}        
        } 
		else {
        	System.out.println("이미 폴더가 생성되어 있습니다.");
		}
	}
}
