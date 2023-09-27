package com.team5.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDAO MemberDAO;
	
	public int createMember(MemberVO MemberVO) {
		boolean isMember = MemberDAO.isMember(MemberVO.getM_id());
		
		if(!isMember) {
			int result = MemberDAO.insertMember(MemberVO);
			if(result > 0)
				return 1; //생성 성공
			else
				return -1; //생성 실패
		}else {
			return 0; //이미 존재하는 계정
		}
	}
	
	public MemberVO login(MemberVO MemberVO) {
		MemberVO loginedMemberVO = MemberDAO.selectMember(MemberVO);
		
		if(loginedMemberVO != null)
			System.out.println("로그인 성공");
		else
			System.out.println("로그인 실패");
		return loginedMemberVO;
	}
	
	public MemberVO getMemberByNum(Long m_num) {
		return MemberDAO.selectMemberByNum(m_num);
	}
		
	public int setAccount(Long m_num, String m_lev) {
		return MemberDAO.updateAccount(m_num, m_lev);
	}
	
	public int modifyMember(MemberVO memberVO) {
		return MemberDAO.updateMember(memberVO);
	}
	
	public int updateAccount(String m_lev, Long m_num) {
		return MemberDAO.updateAccount(m_num, m_lev);
	}
	
	public int updatePassword(String m_id, String newpw) {
		return MemberDAO.updatePassword(m_id, newpw);
	}
}
