package com.team5.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDAO MemberDAO;
	
	public int createMember(MemberVO memberVO) {
		boolean isMember = MemberDAO.isMember(memberVO.getM_id());
		
		if(!isMember) {
			int result = MemberDAO.insertMember(memberVO);
			if(result > 0) {
				MemberDAO.setAccount(memberVO.getM_id());
				return 1; //생성 성공
			}
			else
				return -1; //생성 실패
		}else {
			return 0; //이미 존재하는 계정
		}
	}
	
	public MemberVO login(MemberVO memberVO) {
		MemberVO loginedMemberVO = MemberDAO.selectMember(memberVO);
		
		if(loginedMemberVO != null)
			System.out.println("로그인 성공");
		else
			System.out.println("로그인 실패");
		return loginedMemberVO;
	}
	
	public MemberVO getMemberByNum(Long m_num) {
		return MemberDAO.selectMemberByNum(m_num);
	}
		
	public int setAccount(String m_id, String m_lev) {
		return MemberDAO.updateAccount(m_id, m_lev);
	}
	
	public int modifyMember(MemberVO memberVO) {
		return MemberDAO.updateMember(memberVO);
	}
	
	public int updateAccount(String m_id, String m_lev) {
		return MemberDAO.updateAccount(m_id, m_lev);
	}
	
	public int updatePassword(String m_id, String newpw) {
		return MemberDAO.updatePassword(m_id, newpw);
	}
}
