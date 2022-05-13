package com.spring.ex.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	@Resource(name="uploadPath")
    String uploadPath;
    
    @RequestMapping(value="/uploadPage", method=RequestMethod.GET)
    public String fileupload() {
        return "fileuploadTest";
    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String uploadForm(@RequestParam MultipartFile file) throws Exception {
    	createFolder();
    	// ���� �� ����
    	String uuid = UUID.randomUUID().toString();
    	
    	// ������ �����ϱ� ���� ��ο� ���ϸ� ����
        File fileInfo = new File(uploadPath, uuid + file.getOriginalFilename());
        // ���� ����
        file.transferTo(fileInfo);
        
        return "redirect:uploadPage";
    }
    
    public void createFolder() {
    	File Folder = new File(uploadPath);

    	// �ش� ���丮�� ������� ���丮�� �����մϴ�.
    	if (!Folder.exists()) {
    		try{
    		    Folder.mkdir(); //���� �����մϴ�.
    		    System.out.println("������ �����Ǿ����ϴ�.");
    	        } 
    	        catch(Exception e){
    		    e.getStackTrace();
    		}        
             }else {
    		System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
    	}
    }
}
