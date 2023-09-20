package com.team5.mapper;

import com.team5.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String m_id);
	public void signUp(MemberVO memberVO);
	public int idChk(MemberVO memberVO);
}
