package com.spring.ex.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	@Resource(name="uploadPath")
	String uploadPath;
	
	@Autowired
	ServletContext servletContext;
	
	public String uploadFile(MultipartFile file, String path) throws Exception {
		String databasePath; 
		String serverPath = servletContext.getRealPath("resources" + path); // 서버 경로
		createFolder(uploadPath + path);
		createFolder(serverPath);
		
		String uuid = UUID.randomUUID().toString();
		
		// 데이터베이스에 저장될 경로
		databasePath = "/resources" + path + "/" + uuid + file.getOriginalFilename();
		
		// 로컬에 저장
		File localFile = new File(uploadPath + path, uuid + file.getOriginalFilename());
		file.transferTo(localFile);
		
		// refresh 없이 바로 적용되게 서버에 저장
		File serverFile = new File(serverPath, uuid + file.getOriginalFilename());
		Files.copy(localFile.toPath(), serverFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		return databasePath;
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
	
	public void deleteFile(String path) {
		// /resources 삭제
		String localPath = uploadPath.substring(0, uploadPath.length()-10);
		System.out.println("uploadPath : " + uploadPath);
		System.out.println("localPath : " + localPath);
		File localFile = new File(localPath + path);
		localFile.delete();
		
		System.out.println("localFile : " + localFile.getPath());
	}
}
