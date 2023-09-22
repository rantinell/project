package com.team5.service;

import com.team5.domain.MemberVO;

public interface MemberService {
	public void signUp(MemberVO vo);
	public int idChk(MemberVO vo);
	public MemberVO get(String m_id);
}
