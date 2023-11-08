package com.team1.service;

import java.util.List;

import com.team1.dto.MemberVO;

public interface MemberService {

	public int createMember(MemberVO memberVO);

//	public MemberVO login(MemberVO memberVO);
	public MemberVO login(String m_id);

	public MemberVO getMemberByNum(Long m_num);

	public int setAccount(String m_id, String m_lev);

	public int modifyMember(MemberVO memberVO);

	public int updateAccount(String m_id, String m_lev);

	public int updatePassword(String m_id, String newpw);
	

	//테스트
	public void signUp(MemberVO vo);
	public int idChk(String m_id);
	public MemberVO get(String m_id);

	//추가
	public List<MemberVO> getMember();

	public MemberVO memberModify(MemberVO memberVO);
}
