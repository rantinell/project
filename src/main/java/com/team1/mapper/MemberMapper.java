package com.team1.mapper;

import java.util.List;

import com.team1.dto.MemberVO;

public interface MemberMapper {
	public MemberVO read(String m_id);
	
	public MemberVO selectMember(String m_id);

//	public int isMember(String m_id);

//	public int insertMember(MemberVO memberVO);
	
	//테스트
	public MemberVO read_test(String m_id);
	public void signUp(MemberVO vo);
	public int idChk(String m_id);
	public MemberVO get(String m_id);

	public List<MemberVO> getMember();

	public MemberVO memberModify(MemberVO memberVO);
}