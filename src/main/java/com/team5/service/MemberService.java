package com.team5.service;

import com.team5.domain.MemberVO;

public interface MemberService {
	public void signUp(MemberVO memberVO);
	public int idChk(MemberVO memberVO);
}
