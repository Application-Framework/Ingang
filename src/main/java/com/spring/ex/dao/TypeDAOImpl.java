package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.MainTypeDTO;
import com.spring.ex.dto.SubTypeDTO;

@Repository
public class TypeDAOImpl implements TypeDAO {

	@Inject
	private SqlSession sql;
	
	private String namespace = "com.spring.ex.TypeMapper";
	
	//--------------------------
	// MainType
	//--------------------------
	@Override
	public List<MainTypeDTO> getMainTypeList() {
		return sql.selectList(namespace + ".getMainTypeList");
	}

	@Override
	public int insertMainType(MainTypeDTO dto) {
		return sql.insert(namespace + ".insertMainType", dto);
	}

	@Override
	public int updateMainType(MainTypeDTO dto) {
		return sql.update(namespace + ".updateMainType", dto);
	}

	@Override
	public int deleteMainType(int main_type_no) {
		return sql.delete(namespace + ".deleteMainType", main_type_no);
	}
	
	@Override
	public MainTypeDTO getMainTypeByMainTypeNo(int main_type_no) {
		return sql.selectOne(namespace + ".getMainTypeByMainTypeNo", main_type_no);
	}
	
	@Override
	public MainTypeDTO getMainTypeByMainTypeAbbr(String main_type_abbr) {
		return sql.selectOne(namespace + ".getMainTypeByMainTypeAbbr", main_type_abbr);
	}
	
	//--------------------------
	// SubType
	//--------------------------
	
	@Override
	public List<SubTypeDTO> getSubTypeList() {
		return sql.selectList(namespace + ".getSubTypeList");
	}

	@Override
	public List<SubTypeDTO> getSubTypeListOfMainType(int main_type_no) {
		return sql.selectList(namespace + ".getSubTypeListOfMainType", main_type_no);
	}
	
	@Override
	public int insertSubType(SubTypeDTO dto) {
		return sql.insert(namespace + ".insertSubType", dto);
	}

	@Override
	public int updateSubType(SubTypeDTO dto) {
		return sql.update(namespace + ".insertSubType", dto);
	}

	@Override
	public int deleteSubType(int sub_type_no) {
		return sql.delete(namespace + ".deleteSubType", sub_type_no);
	}
	
	@Override
	public SubTypeDTO getSubTypeBySubTypeNo(int sub_type_no) {
		return sql.selectOne(namespace + ".getSubTypeBySubTypeNo", sub_type_no);
	}
	
	@Override
	public SubTypeDTO getSubTypeBySubTypeAbbr(String sub_type_abbr) {
		return sql.selectOne(namespace + ".getSubTypeBySubTypeAbbr", sub_type_abbr);
	}
}