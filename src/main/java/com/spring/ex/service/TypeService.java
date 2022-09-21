package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.MainTypeDTO;
import com.spring.ex.dto.SubTypeDTO;

@Service
public interface TypeService {
	public List<MainTypeDTO> getMainTypeList();
	public int insertMainType(MainTypeDTO dto);
	public int updateMainType(MainTypeDTO dto);
	public int deleteMainType(int main_type_no);
	public MainTypeDTO getMainTypeByMainTypeNo(int main_type_no);
	public MainTypeDTO getMainTypeByMainTypeAbbr(String main_type_abbr);
	
	public List<SubTypeDTO> getSubTypeList();
	public List<SubTypeDTO> getSubTypeListOfMainType(int main_type_no);
	public int insertSubType(SubTypeDTO dto);
	public int updateSubType(SubTypeDTO dto);
	public int deleteSubType(int sub_type_no);
	public SubTypeDTO getSubTypeBySubTypeNo(int sub_type_no);
	public SubTypeDTO getSubTypeBySubTypeAbbr(String sub_type_abbr);
}
