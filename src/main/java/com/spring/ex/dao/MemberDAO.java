package src.main.java.com.spring.ex.dao;

import com.spring.ex.dto.MemberDTO;

public interface MemberDAO {
	
	//?öå?õê Í∞??ûÖ
	public void signUp(MemberDTO dto) throws Exception;
	
	//Î°úÍ∑∏?ù∏
	public MemberDTO login(MemberDTO dto) throws Exception;
}
