package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.TypeDAO;
import com.spring.ex.dto.MainTypeDTO;
import com.spring.ex.dto.SubTypeDTO;

@Service
public class TypeServiceImpl implements TypeService {

	@Inject
	private TypeDAO typeDAO;
	
	@Override
	public List<MainTypeDTO> getMainTypeList() {
		return typeDAO.getMainTypeList();
	}

	@Override
	public int insertMainType(MainTypeDTO dto) {
		return typeDAO.insertMainType(dto);
	}

	@Override
	public int updateMainType(MainTypeDTO dto) {
		return typeDAO.updateMainType(dto);
	}

	@Override
	public int deleteMainType(int main_type_no) {
		return typeDAO.deleteMainType(main_type_no);
	}

	@Override
	public MainTypeDTO getMainTypeByMainTypeNo(int main_type_no) {
		return typeDAO.getMainTypeByMainTypeNo(main_type_no);
	}
	
	@Override
	public MainTypeDTO getMainTypeByMainTypeAbbr(String main_type_abbr) {
		return typeDAO.getMainTypeByMainTypeAbbr(main_type_abbr);
	}

	//--------------------------
	// SubType
	//--------------------------
	
	@Override
	public List<SubTypeDTO> getSubTypeList() {
		return typeDAO.getSubTypeList();
	}

	@Override
	public List<SubTypeDTO> getSubTypeListOfMainType(int main_type_no) {
		return typeDAO.getSubTypeListOfMainType(main_type_no);
	}
	
	@Override
	public int insertSubType(SubTypeDTO dto) {
		return typeDAO.insertSubType(dto);
	}

	@Override
	public int updateSubType(SubTypeDTO dto) {
		return typeDAO.updateSubType(dto);
	}

	@Override
	public int deleteSubType(int sub_type_no) {
		return typeDAO.deleteSubType(sub_type_no);
	}
	
	@Override
	public SubTypeDTO getSubTypeBySubTypeNo(int sub_type_no) {
		return typeDAO.getSubTypeBySubTypeNo(sub_type_no);
	}

	@Override
	public SubTypeDTO getSubTypeBySubTypeAbbr(String sub_type_abbr) {
		return typeDAO.getSubTypeBySubTypeAbbr(sub_type_abbr);
	}
}
