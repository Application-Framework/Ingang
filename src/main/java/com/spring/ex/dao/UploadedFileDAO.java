package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.UploadedFileDTO;

@Repository
public interface UploadedFileDAO {
	public int insertFile(UploadedFileDTO dto);
	public int deleteFile(String url);
	public List<UploadedFileDTO> selectFileList(int content_no, int category);
	public int changeContent_no(int beforeContent_no, int afterContent_no);
}
